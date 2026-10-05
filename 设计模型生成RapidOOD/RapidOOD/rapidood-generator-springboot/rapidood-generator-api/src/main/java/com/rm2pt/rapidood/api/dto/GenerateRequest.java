package com.rm2pt.rapidood.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Request body for POST /api/v1/generate/class-diagram.
 * All fields are optional; defaults are resolved from application.yml.
 */
@Schema(description = "Request to generate a ClassDiagram from a local .remodel file")
public class GenerateRequest {

    @Schema(description = "Absolute path of the source .remodel file on the server",
            example = "/data/rapidood/input/CoCoME.remodel")
    private String inputPath;

    @Schema(description = "Absolute path of the output directory on the server (optional). "
            + "If omitted the output is placed in {inputDir}/DesignModel/",
            example = "/data/rapidood/output/DesignModel")
    private String outputDir;

    public String getInputPath() { return inputPath; }
    public void setInputPath(String inputPath) { this.inputPath = inputPath; }

    public String getOutputDir() { return outputDir; }
    public void setOutputDir(String outputDir) { this.outputDir = outputDir; }
}
