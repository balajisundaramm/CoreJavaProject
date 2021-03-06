package com.noteManager;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.nio.channels.Pipe;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;

/**
 * Created by Balaji on 28/11/17.
 */
public class NBModel {
    public static String addCatagory(String catagory) {
      //  System.out.println("Model-->addCatagory()-->catagory-->" + catagory);
        File f = new File(catagory + ".note");
        String msg = "";
        try {
            if (f.createNewFile()) {
                return "SUCCESS";
            } else {
                msg = "File is already exist";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return msg;

    }

    public String addTask(TaskBean bean, String catagory) {
        //System.out.println("Model--> addTask()-->catagory " + catagory + " bean" + bean);
        //name,des,pri,dueDate,tagline,cre_DateString,sts
        String line = (bean.getName() + ":" + bean.getDescription() + ":" +
                bean.getPriority() + ":" + bean.getDueDate() + ":" +
                bean.getTagLine() + ":" + bean.getCre_Date() + ":" + bean.getStatus());
        //System.out.println("model-->addTask-->line-->" + line);
        BufferedWriter bw = null;
        File f = new File(catagory + ".note");
        try {
            bw = new BufferedWriter(new FileWriter(catagory + ".note", true));
            bw.write(line);
            bw.newLine();
            //System.out.println(f.getAbsolutePath());
            return Constants.SUCCESS;
        } catch (IOException e) {
            e.printStackTrace();
            return "Oops! somthing went wrong in IO" + e.getMessage();

        } finally {
            IOUtillMethods.writerClose(bw);
        }
    }

    public Set<String> showTasks(String catagory) {
        //System.out.println("model-->showTask()-->catagory-->" + catagory);
        BufferedReader reader = null;
        File file = new File(catagory + ".note");
        Set<String> ListOfTasks = new TreeSet<String>();
        try {
            reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] taskName = line.split(":");
                ListOfTasks.add(taskName[0]);

            }
            return ListOfTasks;
        } catch (IOException e) {
            e.printStackTrace();
            return null;

        } finally {
            IOUtillMethods.readerClose(reader);
        }
    }

    public Set<String> showCatagories() {
        // System.out.println("model-->showCatagories()");
        Set<String> ListOfCatagories = new TreeSet<String>();
        final String path = "/home/gokul/Git_projects/CoreJavaProject";
        File file = new File(path);
        File[] fa = file.listFiles();
        for (File files : fa) {
            if (files.getName().endsWith(".note"))
                ListOfCatagories.add(files.getName().substring(0, (files.getName().indexOf("."))));
        }
        return ListOfCatagories;

    }

    public static List<Map<String, String>> searchCatagories(String keyWord) {
        //System.out.println("model-->searchCatagories()");
        int totalCount = 0, nameCount = 0, desCount = 0, tagCount = 0;
        Map<String, String> searchInName = new HashMap<String, String>();
        Map<String, String> searchInDes = new HashMap<String, String>();
        Map<String, String> searchInTag = new HashMap<String, String>();
        Map<String, String> countMap = new HashMap<String, String>();
        Map<String, String> nameHeadingMap = new HashMap<String, String>();
        Map<String, String> desHeadingMap = new HashMap<String, String>();
        Map<String, String> tagHeadingMap = new HashMap<String, String>();


        List<Map<String, String>> listOfTasks = new ArrayList<Map<String, String>>();
        Map<String, String> allMaps = new LinkedHashMap<String, String>();
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
                            searchInName.put("Category name : " + cataName, " || Task Details : " + showLine(line));
                        }

                        if (states[1].indexOf(keyWord) != -1) {
                            totalCount++;
                            desCount++;
                            searchInDes.put("Category name : " + cataName, " || Task Details : " + showLine(line));
                        }

                        if (states[4].indexOf(keyWord) != -1) {
                            totalCount++;
                            tagCount++;
                            searchInTag.put("Category name : " + cataName, " || Task Details : " + showLine(line));
                        }

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    IOUtillMethods.readerClose(reader);
                }
            }
        }
        String mainHeading = "Total number of matches found : ";
        countMap.put(mainHeading, String.valueOf(totalCount));
        listOfTasks.add(countMap);

        if (searchInName.size() != 0) {
            String nameHeaading = "Number of matches found in Name : ";
            nameHeadingMap.put(nameHeaading, String.valueOf(nameCount));
            listOfTasks.add(nameHeadingMap);
            listOfTasks.add(searchInName);
        }
        if (searchInDes.size() != 0) {
            String desHeading = "Number of matches found in description : ";
            desHeadingMap.put(desHeading, String.valueOf(desCount));
            listOfTasks.add(desHeadingMap);
            listOfTasks.add(searchInDes);
        }
        if (searchInTag.size() != 0) {
            String tagHeading = "Number of matches found in tag : ";
            tagHeadingMap.put(tagHeading, String.valueOf(tagCount));
            listOfTasks.add(tagHeadingMap);
            listOfTasks.add(searchInTag);
        }

        return listOfTasks;
    }

    public String deleteTask(String catagory, String task) {
        //System.out.println("model-->deleteTask-->");
        BufferedReader reader = null;
        File file = new File(catagory + ".note");
        StringBuilder sb = new StringBuilder();
        int count = 0;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(task) && count == 0) {
                    count++;
                    continue;
                }
                sb.append(line);
                sb.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;

        } finally {
            IOUtillMethods.readerClose(reader);
        }
        writeMethodForEditAndDelete(file, sb);
        return "SUCCESS";
    }

    public String showTaskDetails(String catagory, String task) {
        String oldLine = editLine(catagory, task);
        return showLine(oldLine);
    }

    public List<TaskBean> editTask(String catagory, String task) {
        //System.out.println("model-->editTask-->");
        BufferedReader reader = null;
        File file = new File(catagory + ".note");
        String taskLine = "";
        TaskBean bean = new TaskBean();
        List<TaskBean> list = new ArrayList<TaskBean>();
        try {
            reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(task)) {
                    taskLine = line;
                    break;
                }
            }
            String[] details = taskLine.split(":");
            for (int i = 0; i < details.length; i++) {
                bean.setName(details[0]);
                bean.setDescription(details[1]);
                bean.setPriority(Integer.parseInt(details[2]));
                bean.setDueDate(details[3]);
                bean.setTagLine(details[4]);
                bean.setStatus(details[6]);
            }
            list.add(bean);
        } catch (IOException e) {
            e.printStackTrace();
            return null;

        } finally {
            IOUtillMethods.readerClose(reader);
        }
        return list;
    }


    public static String showLine(String oldLine) {
        String[] deatils = oldLine.split(":");
        StringBuilder newLine = new StringBuilder();
        newLine.append("Name : " + deatils[0] + "\t||\t");
        newLine.append("Description : " + deatils[1] + "\t||\t");
        newLine.append("Priority : " + deatils[2] + "\t||\t");
        newLine.append("Due_Date : " + deatils[3] + "\t||\t");
        newLine.append("Tags : " + deatils[4] + "\t||\t");
        newLine.append("Created_Date : " + deatils[5] + "\t||\t");
        newLine.append("Status : " + deatils[6] + "\t||\t");
        return String.valueOf(newLine);
    }

    public Map<String, String> listByName() {
        //System.out.println("model-->showCatagories()");
        Map<String, String> catagoriesListByName = new TreeMap<>();
        final String path = "/home/gokul/Git_projects/CoreJavaProject";
        File file = new File(path);
        File[] fa = file.listFiles();
        Path p;
        BasicFileAttributes attr;
        FileTime cer_date;
        for (File files : fa) {
            if (files.getName().endsWith(".note")) {
                try {
                    p = Paths.get(files.getAbsolutePath());
                    attr = Files.readAttributes(p, BasicFileAttributes.class);
                    cer_date = attr.creationTime();
                    String fileName = files.getName().substring(0, files.getName().indexOf("."));
                    String catagoryDetails = fileName + " created date : " + cer_date;
                    Set<String> set = showTasks(fileName);
                    String oldLine = "";
                    StringBuilder taskDetails = new StringBuilder();
                    for (String task : set) {
                        oldLine = editLine(fileName, task);
                        taskDetails.append(showLine(oldLine) + "\n");
                    }
                    catagoriesListByName.put(catagoryDetails, taskDetails.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return catagoriesListByName;
    }

    public SortedSet<Map.Entry<String, FileTime>> listByDate() {
        //System.out.println("model-->showCatagories()");
        Map<String, FileTime> catagoriesListByDate = new TreeMap<String, FileTime>();
        final String path = "/home/gokul/Git_projects/CoreJavaProject";
        File file = new File(path);
        File[] fa = file.listFiles();
        Path p;
        BasicFileAttributes attr;
        FileTime cer_date = null;

        for (File files : fa) {
            if (files.getName().endsWith(".note")) {
                try {
                    p = Paths.get(files.getAbsolutePath());
                    attr = Files.readAttributes(p, BasicFileAttributes.class);
                    cer_date = attr.creationTime();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String fileName = files.getName().substring(0, files.getName().indexOf("."));
                String catagoryDetails = fileName;
                StringBuilder taskDetails = new StringBuilder();
                taskDetails.append(catagoryDetails + "\n");
                Set<String> set = showTasks(fileName);
                String oldLine = "";
                for (String task : set) {
                    oldLine = editLine(fileName, task);
                    taskDetails.append(showLine(oldLine) + "\n");
                }

                catagoriesListByDate.put(taskDetails.toString(), cer_date);
            }
        }
        return CompareByDate.entriesSortedByValues(catagoriesListByDate);
    }

    public Set<String> taskListByName(String catagory) {
        //System.out.println("model-->taskListByName() catagory-->" + catagory);
        BufferedReader reader = null;
        File file = new File(catagory + ".note");
        Set<String> listOfTasks = new TreeSet<String>();
        try {
            reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] taskName = line.split(":");
                listOfTasks.add("Task Name-->" + taskName[0] + "||  Details-->" + showLine(line));

            }
            return listOfTasks;
        } catch (IOException e) {
            e.printStackTrace();
            return null;

        } finally {
            IOUtillMethods.readerClose(reader);
        }

    }

    public Map<String, Integer> taskListByPriority(String catagory) {
        //System.out.println("model-->taskListByPriority() catagory-->" + catagory);
        BufferedReader reader = null;
        File file = new File(catagory + ".note");
        Map<String, Integer> listOfTasks = new TreeMap<String, Integer>();
        try {
            reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] taskName = line.split(":");
                listOfTasks.put(showLine(line), Integer.parseInt(taskName[2]));

            }
            Map<String, Integer> result = SortTaskByPriority.sortByComparator(listOfTasks, true);
            return result;

        } catch (IOException e) {
            e.printStackTrace();
            return null;

        } finally {
            IOUtillMethods.readerClose(reader);
        }

    }

    public Map<String, Date> taskListByDue_Date(String catagory) {
        //System.out.println("model-->taskListByDue_Date catagory-->" + catagory);
        BufferedReader reader = null;
        File file = new File(catagory + ".note");
        Map<String, Date> listOfTasks = new TreeMap<String, Date>();
        try {
            reader = new BufferedReader(new FileReader(file));
            String line;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            while ((line = reader.readLine()) != null) {
                String[] taskName = line.split(":");
                Date dueDate = null;
                try {
                    dueDate = sdf.parse(taskName[3]);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                listOfTasks.put(showLine(line), dueDate);

            }
            Map<String, Date> result = SortTaskByPriority.sortTaskByDue_Date(listOfTasks, true);
            return result;

        } catch (IOException e) {
            e.printStackTrace();
            return null;

        } finally {
            IOUtillMethods.readerClose(reader);
        }

    }

    public Map<String, Date> taskListByCreated_date(String catagory) {
        //System.out.println("model-->taskListByCreated_Date catagory-->" + catagory);
        BufferedReader reader = null;
        File file = new File(catagory + ".note");
        Map<String, Date> listOfTasks = new TreeMap<String, Date>();
        try {
            reader = new BufferedReader(new FileReader(file));
            String line;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            while ((line = reader.readLine()) != null) {
                String[] taskName = line.split(":");
                Date dueDate = null;
                try {
                    dueDate = sdf.parse(taskName[5]);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                listOfTasks.put(showLine(line), dueDate);

            }
            Map<String, Date> result = SortTaskByPriority.sortTaskByDue_Date(listOfTasks, true);
            return result;

        } catch (IOException e) {
            e.printStackTrace();
            return null;

        } finally {
            IOUtillMethods.readerClose(reader);
        }

    }

    public List<Map<String, String>> searchTasks(String catagory, String keyWord) {
        //System.out.println("model-->searchTasks()");
        List<Map<String, String>> listOfTasks = new ArrayList<Map<String, String>>();
        BufferedReader reader = null;
        File file = new File(catagory + ".note");
        Map<String, String> searchInName = new HashMap<String, String>();
        Map<String, String> searchInDes = new HashMap<String, String>();
        Map<String, String> searchInTag = new HashMap<String, String>();
        Map<String, String> countMap = new HashMap<String, String>();
        Map<String, String> nameHeadingMap = new HashMap<String, String>();
        Map<String, String> desHeadingMap = new HashMap<String, String>();
        Map<String, String> tagHeadingMap = new HashMap<String, String>();


        try {
            reader = new BufferedReader(new FileReader(file));
            String line;
            int totalCount = 0, nameCount = 0, desCount = 0, tagCount = 0;
            while ((line = reader.readLine()) != null) {
                String[] states = line.split(":");
                if (states[0].indexOf(keyWord) != -1) {
                    totalCount++;
                    nameCount++;
                    searchInName.put("Task name : " + states[0], " || Task Details : " + showLine(line));
                }

                if (states[1].indexOf(keyWord) != -1) {
                    totalCount++;
                    desCount++;
                    searchInDes.put("Task name : " + states[0], " || Task Details : " + showLine(line));
                }

                if (states[4].indexOf(keyWord) != -1) {
                    totalCount++;
                    tagCount++;
                    searchInTag.put("Task name : " + states[0], " || Task Details : " + showLine(line));
                }

            }
            String mainHeading = "Total number of matches found : ";
            countMap.put(mainHeading, String.valueOf(totalCount));
            listOfTasks.add(countMap);

            if (searchInName.size() != 0) {
                String nameHeaading = "Number of matches found in Name : ";
                nameHeadingMap.put(nameHeaading, String.valueOf(nameCount));
                listOfTasks.add(nameHeadingMap);
                listOfTasks.add(searchInName);
            }
            if (searchInDes.size() != 0) {
                String desHeading = "Number of matches found in description : ";
                desHeadingMap.put(desHeading, String.valueOf(desCount));
                listOfTasks.add(desHeadingMap);
                listOfTasks.add(searchInDes);
            }
            if (searchInTag.size() != 0) {
                String tagHeading = "Number of matches found in tag : ";
                tagHeadingMap.put(tagHeading, String.valueOf(tagCount));
                listOfTasks.add(tagHeadingMap);
                listOfTasks.add(searchInTag);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtillMethods.readerClose(reader);
        }
        return listOfTasks;
    }

    public static String getContent(String catagory) {
        //System.out.println("Model--> generatePdf()");
        BufferedReader reader = null;
        File file = new File(catagory + ".note");
        StringBuilder sb = new StringBuilder();
        sb.append("Category name : " + catagory + "\n");
        int task = 1;

        try {
            reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append("Task No : " + task++ + "\n");
                sb.append(showLine(line));
                sb.append("\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtillMethods.readerClose(reader);
        }
        return sb.toString();

    }

    public String generatePdfForSpecific(String catagory) {
        StringBuilder content = new StringBuilder();
        content.append(getContent(catagory));
        content.append("----------------------------------------------------END--" +
                "--------------------------------------------------");
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(catagory + ".pdf"));
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        document.open();
        Paragraph p = new Paragraph(content.toString());
        p.setAlignment(Element.ALIGN_JUSTIFIED);

        try {
            document.add(p);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        document.close();
        return "SUCCESS";
    }

    public String generatePdfForAllCatagories() {
        StringBuilder content = new StringBuilder();
        if (showCatagories().size() > 0) {
            for (String s : showCatagories()) {
                content.append(getContent("/home/gokul/Git_projects/CoreJavaProject/" + s));
                content.append("----------------------------------------------------END" +
                        "----------------------------------------------------\n");
                Document document = new Document();
                try {
                    PdfWriter.getInstance(document, new FileOutputStream("AllCategories.pdf"));
                } catch (DocumentException e) {
                    e.printStackTrace();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                document.open();
                Paragraph p = new Paragraph(content.toString());
                p.setAlignment(Element.ALIGN_JUSTIFIED);

                try {
                    document.add(p);
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
                document.close();
            }
            return "SUCCESS";
        } else {
            System.err.println("There are no categories. add first and then generate pdf.");
            return "";
        }
    }

    public String sendEmail(String catagory, EmailBean bean) {
        String result = new NBModel().generatePdfForSpecific(catagory);
        String fromemail = bean.getFromEmail();
        String toEmail = bean.getToEmail();
        String pass = bean.getPassword();
        String subject = bean.getSubject();
        String content = bean.getContent();
        String msg = "";
        if (result.equals(Constants.SUCCESS)) {
            EmailSendingCode.SendMaill(fromemail, pass, toEmail, subject, content,
                    (catagory + ".pdf"));
            if (result.equals(Constants.SUCCESS)) {

                System.out.println("Email hass been sent successfully!!!");
                result = "SUCCESS";
            } else {
                msg = "Unable to send email....";
            }

        }
        return result;
    }

    public String generateExcel(String catagory) {

        try {
            String path = "/home/gokul/Git_projects/CoreJavaProject/";

            FileInputStream fstream = new FileInputStream(path +catagory+".note");
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String strLine;
            List list = new ArrayList();


            while ((strLine = br.readLine()) != null) {
                list.add(strLine);
            }

            String tname = "", disc = "", edt = "", sts = "", pri = "", tag = "", cred="";

            Iterator itr;

            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet("Excel Sheet");
            HSSFRow rowhead = sheet.createRow((short) 0);

            rowhead.createCell(0).setCellValue("Task Name");
            rowhead.createCell(1).setCellValue("Description");
            rowhead.createCell(2).setCellValue("Priority");
            rowhead.createCell(3).setCellValue("End date");
            rowhead.createCell(4).setCellValue("Tags");
            rowhead.createCell(5).setCellValue("Created Date");
            rowhead.createCell(6).setCellValue("Status");


            int index = 1;
            for (itr = list.iterator(); itr.hasNext(); ) {
                String str = itr.next().toString();
                String[] splitSt = str.split(":");

                for (int i = 0; i < splitSt.length; i++) {
                    tname = splitSt[0];
                    disc = splitSt[1];
                    pri = splitSt[2];
                    edt = splitSt[3];
                    tag = splitSt[4];
                    cred = splitSt[5];
                    sts = splitSt[6];

                }


                HSSFRow row = sheet.createRow((short) index);

                row.createCell(0).setCellValue(tname);
                row.createCell(1).setCellValue(disc);
                row.createCell(2).setCellValue(pri);
                row.createCell(3).setCellValue(edt);
                row.createCell(4).setCellValue(tag);
                row.createCell(5).setCellValue(cred);
                row.createCell(6).setCellValue(cred);

                index++;
            }


            FileOutputStream fileOut = new FileOutputStream(path +catagory+".xls");
            wb.write(fileOut);
            fileOut.close();

            System.out.println("Data is saved in excel file.");
        } catch (Exception e) {
            e.printStackTrace();

        }
        return "SUCCESS";
    }



    public String sendEmailAllCatagories(EmailBean bean){
        String result=new NBModel().generatePdfForAllCatagories();
        String fromemail=bean.getFromEmail();
        String toEmail=bean.getToEmail();
        String pass=bean.getPassword();
        String subject=bean.getSubject();
        String content=bean.getContent();
        String msg="";
        if (result.equals(Constants.SUCCESS)){
            EmailSendingCode.SendMaill(fromemail,pass,toEmail,subject,content,
                    ("AllCategories.pdf"));
            if (result.equals(Constants.SUCCESS)){

                System.out.println("Email hass been sent successfully!!!");
                result="SUCCESS";
            }
            else {
                msg="Unable to send email....";
            }

        }
        return result;
    }


    public static String editLine(String catagory,String task) {
       // System.out.println("model-->editLine()-->");
        BufferedReader reader = null;
        File file = new File(catagory + ".note");
        String result="";
        try {
            reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(task)) {
                    result=line;
                    break;
                }
                else{
                    result="Something went wrong.";
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;

        }
        finally {
            IOUtillMethods.readerClose(reader);
        }
        return result;
    }


    public static void writeMethodForEditAndDelete(File file,StringBuilder sb){
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(file));
            writer.write(String.valueOf(sb));
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            IOUtillMethods.writerClose(writer);
        }
    }
}
