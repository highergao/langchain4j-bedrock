package org.example.config;


import dev.langchain4j.service.*;


public interface ChatAssistant {

    @SystemMessage("You are chat assistant. when you perform tool use, please keep looping and don't break" +
            "will return  tool  param with tag<param></param> " )
    TokenStream streamingChat(@MemoryId String memoryId,
                              @UserMessage String userMessage,
                              @UserName String userName);
}
