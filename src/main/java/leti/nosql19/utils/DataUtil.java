package leti.nosql19.utils;

import com.google.gson.Gson;
import leti.nosql19.model.Course;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DataUtil
{
    public static Course getCourseFromJson(String filename){
        Course course = null;
        try(BufferedReader br = new BufferedReader(new FileReader(filename))) {
            course = new Gson().fromJson(br, Course.class);
        }
        catch (FileNotFoundException ex){
            System.out.println("File not found");
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
        return course;
    }
}
