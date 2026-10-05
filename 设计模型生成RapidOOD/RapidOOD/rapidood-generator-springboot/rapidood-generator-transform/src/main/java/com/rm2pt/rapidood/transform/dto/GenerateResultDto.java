package com.rm2pt.rapidood.transform.dto;

import java.nio.file.Path;

/**
 * Result of a single REMODEL → ClassDiagram generation.
 */
public class GenerateResultDto {

    private final Path inputPath;
    private final Path outputPath;
    private final String cdContent;

    public GenerateResultDto(Path inputPath, Path outputPath, String cdContent) {
        this.inputPath  = inputPath;
        this.outputPath = outputPath;
        this.cdContent  = cdContent;
    }

    /** Absolute path of the source .remodel file. */
    public Path getInputPath()  { return inputPath; }

    /** Absolute path of the generated .cd file. */
    public Path getOutputPath() { return outputPath; }

    /** Full text content of the generated .cd file (Xtext serialized). */
    public String getCdContent() { return cdContent; }

    @Override
    public String toString() {
        return "GenerateResultDto{inputPath=" + inputPath
                + ", outputPath=" + outputPath
                + ", cdContent.length=" + (cdContent == null ? 0 : cdContent.length()) + "}";
    }
}
