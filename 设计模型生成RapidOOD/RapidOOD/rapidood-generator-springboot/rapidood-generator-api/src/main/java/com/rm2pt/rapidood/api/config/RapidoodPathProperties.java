package com.rm2pt.rapidood.api.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Binds {@code rapidood.paths.*} configuration from application.yml.
 * Registered via {@code @EnableConfigurationProperties} in {@link AppConfig}.
 */
@ConfigurationProperties(prefix = "rapidood.paths")
public class RapidoodPathProperties {

    /**
     * 输入目录：该目录下应有且仅有一个 .remodel 文件。
     * Example: /Users/yuanyaowu/Desktop/实验室材料/rapidOOD/input
     */
    private String inputDir;

    /**
     * 输出目录：生成的 .cd 文件写入此目录，
     * 文件名格式为 {原文件名}_{yyyyMMdd_HHmmss}.cd
     * Example: /Users/yuanyaowu/Desktop/实验室材料/rapidOOD/output
     */
    private String outputDir;

    /**
     * 备用：直接指定输入文件绝对路径（自定义路径接口使用）。
     */
    private String inputFile;

    /**
     * 备用：true 时忽略 outputDir，输出到 inputFile 同级的 DesignModel/ 子目录。
     */
    private boolean outputFollowInput = false;

    public String getInputDir()  { return inputDir; }
    public void setInputDir(String inputDir) { this.inputDir = inputDir; }

    public String getOutputDir() { return outputDir; }
    public void setOutputDir(String outputDir) { this.outputDir = outputDir; }

    public String getInputFile() { return inputFile; }
    public void setInputFile(String inputFile) { this.inputFile = inputFile; }

    public boolean isOutputFollowInput() { return outputFollowInput; }
    public void setOutputFollowInput(boolean outputFollowInput) {
        this.outputFollowInput = outputFollowInput;
    }
}
