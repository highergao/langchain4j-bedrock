package org.example.config;

import lombok.Data;

@Data
public class LangChain4jChatResponse {
    private String responseData;
    private ToolExecutionDto toolExecutionDto;

    public LangChain4jChatResponse(){

    }

    public LangChain4jChatResponse(String responseData, ToolExecutionDto toolExecutionDto) {
        this.responseData = responseData;
        this.toolExecutionDto = toolExecutionDto;
    }


}
