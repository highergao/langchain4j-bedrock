package org.example.config;


import dev.langchain4j.service.*;


public interface ChatAssistant {

    @SystemMessage("You are chat assistant" )
    TokenStream streamingChat(@MemoryId String memoryId,
                              @UserMessage String userMessage,
                              @UserName String userName);
}
