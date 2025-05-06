package org.example.config;

import lombok.Data;

@Data
public class ToolExecutionParamDto {
     String id;
     String name;
     String arguments;

     public ToolExecutionParamDto(){

     }

     public ToolExecutionParamDto( String id, String name,  String arguments){
         this.setId(id);
         this.setName(name);
         this.setArguments(arguments);
     }

}
