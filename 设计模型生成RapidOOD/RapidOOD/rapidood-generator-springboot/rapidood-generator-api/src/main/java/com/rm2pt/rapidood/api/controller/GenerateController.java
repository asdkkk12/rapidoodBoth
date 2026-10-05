package com.rm2pt.rapidood.api.controller;

import java.nio.file.Path;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rm2pt.rapidood.api.config.RapidoodPathProperties;
import com.rm2pt.rapidood.api.dto.GenerateRequest;
import com.rm2pt.rapidood.api.dto.GenerateResponse;
import com.rm2pt.rapidood.transform.dto.GenerateResultDto;
import com.rm2pt.rapidood.transform.service.DesignGenerateService;

/**
 * REST endpoint for ClassDiagram generation.
 */
@RestController
@RequestMapping("/api/v1/generate")
@Tag(name = "ClassDiagram Generation",
     description = "Generate ClassDiagram from a local .remodel file")
public class GenerateController {

    private final DesignGenerateService designGenerateService;
    private final RapidoodPathProperties pathProperties;

    public GenerateController(DesignGenerateService designGenerateService,
                              RapidoodPathProperties pathProperties) {
        this.designGenerateService = designGenerateService;
        this.pathProperties        = pathProperties;
    }

    /**
     * 使用 application.yml 中配置的固定目录直接生成，无需传任何参数。
     * <ul>
     *   <li>输入：{@code rapidood.paths.input-dir} 目录下唯一的 .remodel 文件</li>
     *   <li>输出：{@code rapidood.paths.output-dir} 目录，
     *       文件名 = {@code {原文件名}_{yyyyMMdd_HHmmss}.cd}</li>
     * </ul>
     */
    @PostMapping("/class-diagram/default")
    @Operation(summary = "使用固定目录生成 ClassDiagram",
               description = "读取 input-dir 目录中唯一的 .remodel 文件，"
                       + "输出到 output-dir，文件名自动加时间戳，无需任何参数。")
    public ResponseEntity<GenerateResponse> generateWithDefaultPaths() {
        String inputDirStr  = pathProperties.getInputDir();
        String outputDirStr = pathProperties.getOutputDir();

        if (inputDirStr == null || inputDirStr.isBlank()) {
            throw new IllegalArgumentException(
                    "未配置 rapidood.paths.input-dir，请在 application.yml 中设置");
        }
        if (outputDirStr == null || outputDirStr.isBlank()) {
            throw new IllegalArgumentException(
                    "未配置 rapidood.paths.output-dir，请在 application.yml 中设置");
        }

        GenerateResultDto result = designGenerateService.generateFromDirectory(
                Path.of(inputDirStr), Path.of(outputDirStr));

        GenerateResponse response = new GenerateResponse(
                result.getInputPath().toString(),
                result.getOutputPath().toString(),
                result.getOutputPath().getFileName().toString(),
                result.getCdContent());

        return ResponseEntity.ok(response);
    }

    /**
     * Generate a ClassDiagram (.cd) from a .remodel file on the server's local filesystem.
     *
     * <p>All request fields are optional. When omitted the values configured in
     * {@code application.yml} under {@code rapidood.paths.*} are used.</p>
     */
    @PostMapping("/class-diagram")
    @Operation(summary = "Generate ClassDiagram（可自定义路径）",
               description = "可选传 inputPath / outputDir；不传则使用 application.yml 中的固定路径。")
    public ResponseEntity<GenerateResponse> generateClassDiagram(
            @RequestBody(required = false) GenerateRequest request) {

        // Resolve input path
        String inputPathStr = (request != null && request.getInputPath() != null)
                ? request.getInputPath()
                : pathProperties.getInputFile();

        if (inputPathStr == null || inputPathStr.isBlank()) {
            throw new IllegalArgumentException(
                    "inputPath is required (or configure rapidood.paths.input-file)");
        }

        Path inputPath = Path.of(inputPathStr);

        // Resolve output path
        GenerateResultDto result;
        if (request != null && request.getOutputDir() != null
                && !request.getOutputDir().isBlank()) {
            result = designGenerateService.generate(inputPath,
                    resolveOutputCdPath(inputPath, Path.of(request.getOutputDir())));
        } else if (!pathProperties.isOutputFollowInput()
                && pathProperties.getOutputDir() != null
                && !pathProperties.getOutputDir().isBlank()) {
            result = designGenerateService.generate(inputPath,
                    resolveOutputCdPath(inputPath, Path.of(pathProperties.getOutputDir())));
        } else {
            result = designGenerateService.generate(inputPath);
        }

        GenerateResponse response = new GenerateResponse(
                result.getInputPath().toString(),
                result.getOutputPath().toString(),
                result.getOutputPath().getFileName().toString(),
                result.getCdContent());

        return ResponseEntity.ok(response);
    }

    private static Path resolveOutputCdPath(Path inputPath, Path outputDir) {
        String fn   = inputPath.getFileName().toString();
        String stem = fn.contains(".") ? fn.substring(0, fn.lastIndexOf('.')) : fn;
        return outputDir.resolve(stem + ".cd");
    }
}
