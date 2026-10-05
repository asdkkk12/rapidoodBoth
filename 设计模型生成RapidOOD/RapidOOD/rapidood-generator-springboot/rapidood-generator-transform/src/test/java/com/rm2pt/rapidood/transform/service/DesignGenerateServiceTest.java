package com.rm2pt.rapidood.transform.service;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.core.io.ClassPathResource;

import com.rm2pt.rapidood.transform.dto.GenerateResultDto;

class DesignGenerateServiceTest {

    private final DesignGenerateService service = new DesignGenerateService();

    @Test
    void generateWithExplicitOutputPath(@TempDir Path tempDir) throws IOException {
        Path input  = new ClassPathResource("testdata/atm.remodel").getFile().toPath();
        Path output = tempDir.resolve("DesignModel").resolve("atm.cd");

        GenerateResultDto result = service.generate(input, output);

        assertNotNull(result);
        assertEquals(input.toAbsolutePath(),  result.getInputPath());
        assertEquals(output.toAbsolutePath(), result.getOutputPath());

        assertTrue(Files.isRegularFile(result.getOutputPath()),
                "Output .cd file should exist");

        String content = result.getCdContent();
        assertNotNull(content);
        assertTrue(content.contains("@startuml"),
                "Generated .cd should contain @startuml");
        assertTrue(content.contains("@enduml"),
                "Generated .cd should contain @enduml");
        assertFalse(content.isBlank(),
                "Generated .cd content should not be blank");
    }

    @Test
    void generateWithDefaultOutputPath(@TempDir Path tempDir) throws IOException {
        Path inputDir = tempDir.resolve("input");
        Files.createDirectories(inputDir);
        Path src   = new ClassPathResource("testdata/atm.remodel").getFile().toPath();
        Path input = inputDir.resolve("atm.remodel");
        Files.copy(src, input);

        GenerateResultDto result = service.generate(input);

        Path expectedOutput = inputDir.resolve("DesignModel").resolve("atm.cd");
        assertEquals(expectedOutput.toAbsolutePath(), result.getOutputPath());
        assertTrue(Files.isRegularFile(result.getOutputPath()));
    }

    @Test
    void resolveDefaultOutputPath_correctConvention() {
        Path input    = Path.of("/some/dir/CoCoME.remodel");
        Path expected = Path.of("/some/dir/DesignModel/CoCoME.cd");
        assertEquals(expected, DesignGenerateService.resolveDefaultOutputPath(input));
    }

    @Test
    void generateThrowsForMissingInput(@TempDir Path tempDir) {
        Path missing = tempDir.resolve("nonexistent.remodel");
        Path output  = tempDir.resolve("DesignModel").resolve("nonexistent.cd");

        assertThrows(IllegalArgumentException.class,
                () -> service.generate(missing, output));
    }
}
