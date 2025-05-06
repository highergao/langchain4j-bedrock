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
            "before student add,need validate class room  if existed" +
            "-studentName " +
            "-classRoom should in school class room" +
            "confirm need user to confirm all param info" +
            " Once all needed information is available, present a chore summary and ask the user to type \"Done\" to confirm and save.")
    public String studentToClass(@P("studentName") String studentName,
                                   @P("classRoom") String classRoom,
                                    @P("confirm") boolean confirm
                                   ){
        System.out.println("studentToClass done");
        if(confirm){
            return "add new student"+studentName+"to class room"+classRoom;
        }else {
            return "are you sure for do this";
        }

    }
}
