package com.rm2pt.rapidood.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Response body for POST /api/v1/generate/class-diagram.
 */
@Schema(description = "Result of ClassDiagram generation")
public class GenerateResponse {

    @Schema(description = "Absolute path of the source .remodel file",
            example = "/data/rapidood/input/CoCoME.remodel")
    private String inputPath;

    @Schema(description = "Absolute path of the generated .cd file",
            example = "/data/rapidood/input/DesignModel/CoCoME.cd")
    private String outputPath;

    @Schema(description = "File name of the generated .cd",
            example = "CoCoME.cd")
    private String fileName;

    @Schema(description = "Full text content of the generated .cd file (Xtext / PlantUML)")
    private String cdContent;

    public GenerateResponse() {}

    public GenerateResponse(String inputPath, String outputPath,
                            String fileName, String cdContent) {
        this.inputPath  = inputPath;
        this.outputPath = outputPath;
        this.fileName   = fileName;
        this.cdContent  = cdContent;
    }

    public String getInputPath()  { return inputPath; }
    public void setInputPath(String inputPath) { this.inputPath = inputPath; }

    public String getOutputPath() { return outputPath; }
    public void setOutputPath(String outputPath) { this.outputPath = outputPath; }

    public String getFileName()   { return fileName; }
    public void setFileName(String fileName) { this.fileName = fileName; }

    public String getCdContent()  { return cdContent; }
    public void setCdContent(String cdContent) { this.cdContent = cdContent; }
}
