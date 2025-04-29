package org.example.tool;


import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import org.springframework.context.annotation.Description;

import java.util.Arrays;
import java.util.List;

public class ChatTools {


    @Tool(" show all school class room")
    public List<String> showAllClassRoom(){
        System.out.println("class room query");
        return Arrays.asList("classRoomA","classRoomB","classRoomC");
    }

    @Tool("validate class room")
    public boolean myClassRom(String classRoomNum){
        System.out.println("validate class room");
        return  Arrays.asList("classRoomA","classRoomB","classRoomC").contains(classRoomNum);
    }

    @Tool("add new student to class" +
            "增加学生之前,需要检查 class room 是否有效 存在" +
            "-studentName " +
            "- classRoom should in school class room")
    public String studentToClass(@P("studentName") String studentName,
                                   @P("classRoom") String classRoom
                                   ){
        System.out.println("studentToClass done");
        return "add new student"+studentName+"to class room"+classRoom;
    }
}
