package com.rm2pt.rapidood.api;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.*;
import org.springframework.test.context.TestPropertySource;

import com.rm2pt.rapidood.api.dto.GenerateRequest;
import com.rm2pt.rapidood.api.dto.GenerateResponse;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {
        "rapidood.paths.input-file=",
        "rapidood.paths.output-follow-input=true"
})
class GenerateControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @TempDir
    static Path tempDir;

    static Path remodelFile;

    @BeforeAll
    static void setUp() throws IOException {
        // Copy testdata into tempDir so we can pass an absolute file path
        remodelFile = tempDir.resolve("atm.remodel");
        Path src = new ClassPathResource("testdata/atm.remodel").getFile().toPath();
        Files.copy(src, remodelFile);
    }

    @Test
    void generateClassDiagram_returnsOkWithContent() {
        GenerateRequest request = new GenerateRequest();
        request.setInputPath(remodelFile.toAbsolutePath().toString());

        ResponseEntity<GenerateResponse> response = restTemplate.postForEntity(
                "http://localhost:" + port + "/api/v1/generate/class-diagram",
                request,
                GenerateResponse.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());

        GenerateResponse body = response.getBody();
        assertEquals(remodelFile.toAbsolutePath().toString(), body.getInputPath());
        assertNotNull(body.getOutputPath());
        assertTrue(body.getOutputPath().endsWith("atm.cd"),
                "Output path should end with atm.cd but was: " + body.getOutputPath());

        String content = body.getCdContent();
        assertNotNull(content);
        assertTrue(content.contains("@startuml"),
                "Generated .cd should contain @startuml");
        assertTrue(content.contains("@enduml"),
                "Generated .cd should contain @enduml");
    }

    @Test
    void generateClassDiagram_missingInput_returns400() {
        GenerateRequest request = new GenerateRequest();
        request.setInputPath("/nonexistent/path/missing.remodel");

        ResponseEntity<String> response = restTemplate.postForEntity(
                "http://localhost:" + port + "/api/v1/generate/class-diagram",
                request,
                String.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void healthCheck_returns200() {
        ResponseEntity<String> response = restTemplate.getForEntity(
                "http://localhost:" + port + "/actuator/health",
                String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
