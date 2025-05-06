package org.example.config;

import lombok.Data;

@Data
public class ToolExecutionDto {

    String result;

    ToolExecutionParamDto toolExecutionParamDto;

    public ToolExecutionDto(){

    }

    public ToolExecutionDto( String result, ToolExecutionParamDto toolExecutionParamDto){
        this.result = result;
        this.setToolExecutionParamDto(toolExecutionParamDto);
    }
}
