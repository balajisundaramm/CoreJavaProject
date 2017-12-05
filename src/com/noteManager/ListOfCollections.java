package com.noteManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.attribute.FileTime;
import java.util.*;

/**
 * Created by Balaji on 28/11/17.
 */
public class ListOfCollections {
    public static boolean isNameUnique(String catatagory, String name) {
        BufferedReader br=null;
        Set<String> nameSet=new HashSet<String>();
        try
        {
            br = new BufferedReader(new FileReader(catatagory+".note"));
            String line;
            while((line = br.readLine())!=null)
            {
                String[] sa = line.split(":");
                nameSet.add(sa[0]);
            }
            if (nameSet.add(name))
                return true;
            else
                return false;
        }
        catch(IOException e)
        {
            e.printStackTrace();
            return false;
        }

        finally
        {
           IOUtillMethods.readerClose(br);
        }
    }

    public static boolean isCatagoryUnique(String catagory) {
        File file=new File(catagory+".note");
        if (file.exists()){
            return false;
        }
        else
            return true;
    }

    public static void printMap(Map<String, String> map)
    {
        for (Map.Entry<String, String > entry : map.entrySet())
        {
            System.out.println("Category : " + entry.getKey() + "\n\nTask details in the catagory :\n"+ entry.getValue());
        }
    }

    public static void printMapForCreatedDate(SortedSet<Map.Entry<String,FileTime>> map){
        for (Map.Entry<String, FileTime> entry : map)
        {
            System.out.println("Category : " + entry.getKey() + "\nCreated date : "+ entry.getValue()+"\n");
        }
    }

    public static void printMapInteger(Map<String, Integer> map)
    {
        for (Map.Entry<String, Integer> entry : map.entrySet())
        {
            System.out.println("Priority : " + entry.getValue() + "|| Task Details: "+ entry.getKey());
        }
    }

    public static void printMapDate(Map<String, Date> map)
    {
        for (Map.Entry<String, Date> entry : map.entrySet())
        {
            System.out.println("Date : " + entry.getValue() + "|| Task Details: "+ entry.getKey());
        }
    }

    public static void printMapString(Map<String, String> map)
    {
        for (Map.Entry<String, String> entry : map.entrySet())
        {
            System.out.println(entry.getKey() + entry.getValue());
        }
    }
}
