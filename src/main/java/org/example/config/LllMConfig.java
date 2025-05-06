package org.example.config;

import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.bedrock.BedrockChatRequestParameters;
import dev.langchain4j.model.bedrock.BedrockStreamingChatModel;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;
import dev.langchain4j.service.AiServices;
import org.example.tool.ChatTools;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.bedrock.BedrockClient;
import software.amazon.awssdk.services.bedrockruntime.BedrockRuntimeClient;

import java.time.Duration;

@Configuration
public class LllMConfig {

    @Bean
    public ChatAssistant assistantService() {


        return AiServices.builder(ChatAssistant.class)
                .streamingChatLanguageModel(this.streamingChatLanguageModel())
                .chatMemoryProvider(memoryId -> MessageWindowChatMemory.withMaxMessages(30))
                .tools(new ChatTools())
                // .toolProvider(toolProvider)
                // .toolProvider(toolProvider)
                .build();
    }

    @Bean
    public BedrockClient bedrockClient() {
        return BedrockClient.builder()
                .region(Region.US_EAST_1) // Use appropriate region
                .build();
    }

    @Bean
    public BedrockRuntimeClient bedrockRuntimeClient() {
        return BedrockRuntimeClient.builder()
                .region(Region.US_EAST_1) // Use appropriate region
                .build();
    }

/*    @Bean
    public BedrockStreamingChatModel streamingChatLanguageModel() {
        return BedrockStreamingChatModel.builder()
                .region(Region.US_EAST_1)
               //.modelId("amazon.nova-pro-v1:0")
               // .modelId("amazon.nova-lite-v1:0")
              //.modelId("us.anthropic.claude-3-7-sonnet-20250219-v1:0")
                .modelId("us.anthropic.claude-3-5-sonnet-20241022-v2:0")
                .maxRetries(2)
                .timeout(Duration.ofMinutes(1))
                .defaultRequestParameters(BedrockChatRequestParameters.builder()
                        .temperature(0.7)
                        .topP(0.9).maxOutputTokens(2048).build())
                .build();
    }*/

   @Bean
    public StreamingChatLanguageModel streamingChatLanguageModel() {
        return OpenAiStreamingChatModel.builder()
                .apiKey("")
              .modelName("gpt-4o-mini") // common parameter
                .temperature(1.0) // common parameter
                .build();
    }
}
