package com.rm2pt.rapidood.transform.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.rm2pt.rapidood.transform.AtlTransformService;
import com.rm2pt.rapidood.transform.TransformException;
import com.rm2pt.rapidood.transform.dto.GenerateResultDto;

/**
 * Orchestrates the full REMODEL → ClassDiagram pipeline:
 * <ol>
 *   <li>Validate input path</li>
 *   <li>Resolve output path (default: {@code {inputDir}/DesignModel/{stem}.cd})</li>
 *   <li>Delegate to {@link AtlTransformService}</li>
 *   <li>Read the generated .cd text and return {@link GenerateResultDto}</li>
 * </ol>
 *
 * <p>No Eclipse workspace required – can be used from tests directly.</p>
 */
public class DesignGenerateService {

    private final AtlTransformService atlTransformService;

    /** Default constructor – creates its own {@link AtlTransformService}. */
    public DesignGenerateService() {
        this(new AtlTransformService());
    }

    /** Injection-friendly constructor for tests / Spring. */
    public DesignGenerateService(AtlTransformService atlTransformService) {
        this.atlTransformService = Objects.requireNonNull(atlTransformService);
    }

    /**
     * Generate a ClassDiagram from a .remodel file, writing output to the
     * default location: {@code {inputDir}/DesignModel/{stem}.cd}.
     */
    public GenerateResultDto generate(Path remodelPath) {
        Objects.requireNonNull(remodelPath, "remodelPath");
        return generate(remodelPath, resolveDefaultOutputPath(remodelPath));
    }

    /**
     * Generate a ClassDiagram from a .remodel file, writing output to the
     * specified path.
     */
    public GenerateResultDto generate(Path remodelPath, Path outputCdPath) {
        Objects.requireNonNull(remodelPath, "remodelPath");
        Objects.requireNonNull(outputCdPath, "outputCdPath");

        if (!Files.isRegularFile(remodelPath)) {
            throw new IllegalArgumentException(
                    "Input .remodel file does not exist: " + remodelPath.toAbsolutePath());
        }

        Path result;
        try {
            result = atlTransformService.transform(remodelPath, outputCdPath);
        } catch (TransformException e) {
            throw e;
        }

        String cdContent;
        try {
            cdContent = Files.readString(result, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new TransformException("Generated .cd file cannot be read: " + result, e);
        }

        return new GenerateResultDto(
                remodelPath.toAbsolutePath(),
                result.toAbsolutePath(),
                cdContent);
    }

    /**
     * 扫描 {@code inputDir} 中唯一的 .remodel 文件，生成 ClassDiagram 并将结果
     * 写入 {@code outputDir}，文件名格式为 {@code {stem}_{yyyyMMdd_HHmmss}.cd}。
     *
     * @param inputDir  只含一个 .remodel 文件的输入目录
     * @param outputDir 输出目录（不存在时自动创建）
     * @return 生成结果 DTO
     * @throws IllegalArgumentException 目录不存在、或找不到 / 找到多个 .remodel 文件
     */
    public GenerateResultDto generateFromDirectory(Path inputDir, Path outputDir) {
        Objects.requireNonNull(inputDir,  "inputDir");
        Objects.requireNonNull(outputDir, "outputDir");

        if (!Files.isDirectory(inputDir)) {
            throw new IllegalArgumentException(
                    "输入目录不存在或不是目录: " + inputDir.toAbsolutePath());
        }

        // 扫描 .remodel 文件
        List<Path> remodelFiles;
        try (Stream<Path> stream = Files.list(inputDir)) {
            remodelFiles = stream
                    .filter(p -> Files.isRegularFile(p)
                            && p.getFileName().toString().endsWith(".remodel"))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new TransformException("无法读取输入目录: " + inputDir, e);
        }

        if (remodelFiles.isEmpty()) {
            throw new IllegalArgumentException(
                    "输入目录中没有找到 .remodel 文件: " + inputDir.toAbsolutePath());
        }
        if (remodelFiles.size() > 1) {
            throw new IllegalArgumentException(
                    "输入目录中存在多个 .remodel 文件，请保留唯一一个: "
                            + remodelFiles.stream()
                                    .map(p -> p.getFileName().toString())
                                    .collect(Collectors.joining(", ")));
        }

        Path remodelPath = remodelFiles.get(0);

        // 构造带时间戳的输出文件名：{stem}_{yyyyMMdd_HHmmss}.cd
        String fn   = remodelPath.getFileName().toString();
        String stem = fn.contains(".")
                ? fn.substring(0, fn.lastIndexOf('.'))
                : fn;
        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        Path outputCdPath = outputDir.resolve(stem + "_" + timestamp + ".cd");

        return generate(remodelPath, outputCdPath);
    }

    /**
     * Computes the default output path: {@code {inputDir}/DesignModel/{stem}.cd}.
     * Mirrors the Eclipse plugin convention.
     */
    public static Path resolveDefaultOutputPath(Path remodelPath) {
        Path dir  = remodelPath.toAbsolutePath().getParent();
        String fn = remodelPath.getFileName().toString();
        String stem = fn.contains(".")
                ? fn.substring(0, fn.lastIndexOf('.'))
                : fn;
        return dir.resolve("DesignModel").resolve(stem + ".cd");
    }
}
