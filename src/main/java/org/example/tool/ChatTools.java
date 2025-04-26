package org.example.tool;


import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import org.springframework.context.annotation.Description;

import java.util.Arrays;
import java.util.List;

public class ChatTools {


    @Tool("school class room")
    public List<String> myClassRom(){
        System.out.println("class room query");
        return Arrays.asList("classRoomA","classRoomB","classRoomC");
    }

    @Tool("add new student to class" +
            "-studentName " +
            "- classRoom should in school class room")
    public String studentToClass(@P("studentName") String studentName,
                                   @P("classRoom") String classRoom
                                   ){
        System.out.println("studentToClass done");
        return "add new student"+studentName+"to class room"+classRoom;
    }
}
