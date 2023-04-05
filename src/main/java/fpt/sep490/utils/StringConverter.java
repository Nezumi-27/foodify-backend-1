package fpt.sep490.utils;

import org.springframework.stereotype.Component;

@Component
public class StringConverter {
//    public static void main(String[] args){
//
//
//        String input = "THIS IS                  A     STRING";
//        String output = input.replaceAll("\\s+", " ").trim().toLowerCase();
//        output = output.substring(0, 1).toUpperCase() + output.substring(1);
//        System.out.println(output); // output: "This is a string"
//
//    }
    public String convertString(String input){
        String output = input.replaceAll("\\s+", " ").trim().toLowerCase();
        output = output.substring(0, 1).toUpperCase() + output.substring(1);
        return output;
    }
}
