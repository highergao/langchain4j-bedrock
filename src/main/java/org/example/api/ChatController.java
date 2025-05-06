package org.example.api;


import ch.qos.logback.core.util.StringUtil;
import com.alibaba.fastjson.JSONObject;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.service.TokenStream;
import dev.langchain4j.service.tool.ToolExecution;
import jakarta.servlet.http.HttpServletRequest;

import lombok.val;
import org.example.config.ChatAssistant;
import org.example.config.LangChain4jChatResponse;
import org.example.config.ToolExecutionDto;
import org.example.config.ToolExecutionParamDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;


@RestController
@RequestMapping("/coz")
public class ChatController {

        @Autowired
        ChatAssistant chatAssistant;

        @RequestMapping (value = "/streamingChat" , produces = MediaType.TEXT_HTML_VALUE) // 指定返回SSE流
        public SseEmitter openaiChat(@RequestBody JSONObject jsonObject, HttpServletRequest request) {
            System.out.println("streamingChat");
            String message = jsonObject.getString("message");
            String userId = request.getHeader("userId");
            String conversationId = jsonObject.getString("conversationId");
            if(StringUtil.isNullOrEmpty(conversationId)) {
                conversationId = userId;
            }
            SseEmitter emitter = new SseEmitter();
            String finalConversationId = conversationId;
          //CompletableFuture.runAsync(() -> {
            TokenStream tokenStream = chatAssistant.streamingChat(finalConversationId, message, userId);
            tokenStream
                    .onToolExecuted((ToolExecution toolExecution) ->{
                        val toolRequest = toolExecution.request();
                        ToolExecutionParamDto toolExecutionParamDto =
                                new ToolExecutionParamDto(toolRequest.id(),toolRequest.name(),toolRequest.arguments());
                        ToolExecutionDto toolExecutionDto = new ToolExecutionDto(toolExecution.result(),toolExecutionParamDto);
                        LangChain4jChatResponse chatResponse = new LangChain4jChatResponse();
                        chatResponse.setToolExecutionDto(toolExecutionDto);
                        try {
                            // 发送部分响应到客户端
                            emitter.send(SseEmitter.event().data(JSONObject.toJSONString(chatResponse)).build());
                           // Thread.sleep(100);
                        } catch (Exception e) {
                            emitter.completeWithError(e);
                        }
                    })
                    .onPartialResponse((String partialResponse) -> {
                                   try {
                                       System.out.println(partialResponse);
                                       // 发送部分响应到客户端
                                       emitter.send(SseEmitter.event().data(partialResponse));
                                      // Thread.sleep(300);
                                   } catch (Exception e) {
                                       emitter.completeWithError(e);
                                   }
                               })
                               .onCompleteResponse((ChatResponse response) -> emitter.complete())
                               .onError((Throwable error) -> emitter.completeWithError(error))
                               .start();
          // });
            return emitter;
        }


}
