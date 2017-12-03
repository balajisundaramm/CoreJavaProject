package com.noteManager;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Balaji on 27/11/17.
 */
public class Try {
    public static void main(String[] args) {
        /*TaskBean bean=new TaskBean("callMom","at evening","2014/12/11","call,mom,home,feelings",10);
        String ca="hi,how,are,you";
        String[] s=ca.split(",");
        String re="";
        for (String s1:s) {
            re=re+s1+":";
        }
        System.out.println(re);

        System.out.println("hi\thello\nhow");*/

       // System.out.println(ListOfCollections.isCatagoryUnique("person"));
       File file=new File("personal.note");
       // System.out.println(file.getAbsolutePath());*/
        ///home/gokul/Git_projects/CoreJavaProject/hi.note
        //deleteFromFile("hi.note","Call");
        /*Path p = Paths.get(file.getAbsolutePath());
        try {
            BasicFileAttributes attr = Files.readAttributes(p, BasicFileAttributes.class);
            System.out.println("creationTime: " + attr.creationTime());
            System.out.println("lastAccessTime: " + attr.lastAccessTime());
            System.out.println("lastModifiedTime: " + attr.lastModifiedTime());

            System.out.println("isDirectory: " + attr.isDirectory());
            System.out.println("isOther: " + attr.isOther());
            System.out.println("isRegularFile: " + attr.isRegularFile());
            System.out.println("isSymbolicLink: " + attr.isSymbolicLink());
            System.out.println("size: " + attr.size());
        } catch (IOException e) {
            e.printStackTrace();
        }*/


        SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
        Date check=null;
        try {
            check=sdf.parse("2017/11/11");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Date cur=new Date();
        System.out.println("check "+check);
        System.out.println("cur "+cur);
        System.out.println("before "+check.before(cur));
        System.out.println("after "+check.after(cur));
    }


    public static void dateCheck() {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
        String s="2017/02/29";
        Date cd=new Date();
        System.out.println(cd);
        try {
            Date date=sdf.parse(s);
            if (!s.equals(sdf.format(date)))
                System.out.println("no");
            else
                System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    public static void addTask(TaskBean bean, String catagory) {
        System.out.println("Model--> addTask()-->catagory " + catagory + " bean" + bean);
        Date createdDate = new Date();
        String status = "In process";
        String[] tags = bean.getTagLine().split(",");
        System.out.println();
    }

    public static void deleteFromFile(String file,String remove) {
        BufferedReader br = null;
        File f = new File("hi.note");
        StringBuilder sb = new StringBuilder();
        try {
            br = new BufferedReader(new FileReader(f));
            String l;
            while ((l = br.readLine()) != null) {
                if (l.startsWith(remove)) {
                    continue;
                }
                sb.append(l);
                sb.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(f));
            bw.write(String.valueOf(sb));
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtillMethods.readerClose(br);
            IOUtillMethods.writerClose(bw);
        }
        System.out.println("process ended");
    }
    /*while ( (line = br.readLine()) != null )//BufferedReader contains readline method
    {

        Pattern p=Pattern.compile(searchString);*//*here u an specify the line u want to delete *//*
        Matcher m=p.matcher(line);
        line=m.replaceAll(replaceString);*//*here replace String u can " " so that it will be emptied *//*
        System.out.println(line);
    }
    //System.out.println(line);
} */

      }



