package com.noteManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by Balaji on 4/12/17.
 */
public class TrySearchCatagory {
    //static int totalCount=0,nameCount=0,desCount=0,tagCount=0;

    public static void main(String[] args) {
        String key="enhance";

        System.out.println(searchCatagories(key));
    }
    public static List<Map<String,String>> searchCatagories(String keyWord) {
        System.out.println("model-->searchCatagories()");
        int totalCount=0,nameCount=0,desCount=0,tagCount=0;
        Map<String, String> searchInName = new HashMap<String, String>();
        Map<String, String> searchInDes = new HashMap<String, String>();
        Map<String, String> searchInTag = new HashMap<String, String>();
        Map<String, String> countMap = new HashMap<String, String>();
        Map<String, String> nameHeadingMap = new HashMap<String, String>();
        Map<String, String> desHeadingMap = new HashMap<String, String>();
        Map<String, String> tagHeadingMap = new HashMap<String, String>();




        List<Map<String,String>> listOfTasks=new ArrayList<Map<String, String>>();
        Map<String,String> allMaps=new LinkedHashMap<String,String>();
        final String path = "/home/gokul/Git_projects/CoreJavaProject";
        File file = new File(path);
        File[] fa = file.listFiles();
        for (File files : fa) {
            String fName = files.getName();
            if ((fName.endsWith(".note"))) {
                String cataName = fName.substring(0, fName.indexOf("."));
                BufferedReader reader = null;
                file = new File(cataName + ".note");

                try {
                    reader = new BufferedReader(new FileReader(file));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        String[] states = line.split(":");
                        if (states[0].indexOf(keyWord) != -1) {
                            totalCount++;
                            nameCount++;
                            searchInName.put("Catagory name : " + cataName, " || Task Details : " + showLine(line));
                        }

                        if (states[1].indexOf(keyWord) != -1) {
                            totalCount++;
                            desCount++;
                            searchInDes.put("Catagory name : " + cataName, " || Task Details : " + showLine(line));
                        }

                        if (states[4].indexOf(keyWord) != -1) {
                            totalCount++;
                            tagCount++;
                            searchInTag.put("Catagory name : " + cataName, " || Task Details : " + showLine(line));
                        }

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    IOUtillMethods.readerClose(reader);
                }
            }
        }
                    String mainHeading="Total number of matches found : ";
                    countMap.put(mainHeading,String.valueOf(totalCount));
                    listOfTasks.add(countMap);

                    if (searchInName.size()!=0) {
                        String nameHeaading="Number of matches found in Name : ";
                        nameHeadingMap.put(nameHeaading,String.valueOf(nameCount));
                        listOfTasks.add(nameHeadingMap);
                        listOfTasks.add(searchInName);
                    }
                    if (searchInDes.size()!=0) {
                        String desHeading="Number of matches found in description : ";
                        desHeadingMap.put(desHeading,String.valueOf(desCount));
                        listOfTasks.add(desHeadingMap);
                        listOfTasks.add(searchInDes);
                    }
                    if (searchInTag.size()!=0) {
                        String tagHeading="Number of matches found in tag : ";
                        tagHeadingMap.put(tagHeading,String.valueOf(tagCount));
                        listOfTasks.add(tagHeadingMap);
                        listOfTasks.add(searchInTag);
                    }

        return listOfTasks;
        }


    public static String showLine(String oldLine){
        String[] deatils=oldLine.split(":");
        StringBuilder newLine=new StringBuilder();
        if (deatils.length==7) {
            newLine.append("Name : " + deatils[0] + "\t||\t");
            newLine.append("Description : " + deatils[1] + "\t||\t");
            newLine.append("Priority : " + deatils[2] + "\t||\t");
            newLine.append("Due_Date : " + deatils[3] + "\t||\t");
            newLine.append("Tags : " + deatils[4] + "\t||\t");
            newLine.append("Created_Date : " + deatils[5] + "\t||\t");
            newLine.append("Status : " + deatils[6] + "\t||\t");
            return String.valueOf(newLine);
        }
        else {
            return "there are some details missing in the task.";
        }
    }
}
