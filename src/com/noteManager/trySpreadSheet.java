package com.noteManager;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


/**
 * Created by Balaji on 4/12/17.
 */
public class trySpreadSheet {
    public static void main(String[] args) {
        try {

            String path = "/home/gokul/Git_projects/CoreJavaProject/";

            FileInputStream fstream = new FileInputStream(path +"hi.note");
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String strLine;
            List list = new ArrayList();


            while ((strLine = br.readLine()) != null) {
                list.add(strLine);
            }

            String tname = "", disc = "", edt = "", sts = "", pri = "", tag = "";

            Iterator itr;

            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet("Excel Sheet");
            HSSFRow rowhead = sheet.createRow((short) 0);

            rowhead.createCell(0).setCellValue("Task Name");
            rowhead.createCell(1).setCellValue("Description");
            rowhead.createCell(2).setCellValue("End Date");
            rowhead.createCell(3).setCellValue("Status");
            rowhead.createCell(4).setCellValue("priority");
            rowhead.createCell(5).setCellValue("Tags");


            int index = 1;
            for (itr = list.iterator(); itr.hasNext(); ) {
                String str = itr.next().toString();
                String[] splitSt = str.split(":");

                for (int i = 0; i < splitSt.length; i++) {
                    tname = splitSt[0];
                    disc = splitSt[1];
                    edt = splitSt[2];
                    sts = splitSt[3];
                    pri = splitSt[4];
                    tag = splitSt[5];
                }


                HSSFRow row = sheet.createRow((short) index);

                row.createCell(0).setCellValue(tname);
                row.createCell(1).setCellValue(disc);
                row.createCell(2).setCellValue(edt);
                row.createCell(3).setCellValue(sts);
                row.createCell(4).setCellValue(pri);
                row.createCell(5).setCellValue(tag);

                index++;
            }


            FileOutputStream fileOut = new FileOutputStream(path +"gdTask.xls");
            wb.write(fileOut);
            fileOut.close();

            System.out.println("Data is saved in excel file.");
        }
        catch(Exception e) {
            e.printStackTrace();

        }
    }
}


