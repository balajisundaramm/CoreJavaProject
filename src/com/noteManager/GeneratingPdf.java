package com.noteManager;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.*;

import java.io.*;

/**
 * Created by Balaji on 30/11/17.
 */
public class GeneratingPdf {
    public static final String RESULT = "/home/gokul/Git_projects/CoreJavaProject/hi.note";

    public static void generate() {
        try {
            PdfReader reader = new PdfReader(RESULT);
            PdfDictionary dict = reader.getPageN(1);
            PdfObject object = dict.getDirectObject(PdfName.CONTENTS);

            if (object instanceof PRStream) {

                PRStream stream = (PRStream) object;
                System.out.println("Stream-->" + stream);
                byte[] data = PdfReader.getStreamBytes(stream);
                //System.out.println("fghj");
                String eredeti = "Balaji";
                final String s = new String(eredeti.getBytes());
                //System.out.println(new String(data));
                //System.out.println(s);
                stream.setData(new String(data).replace("Job", s).getBytes("ISO-8859-2"));

                Document d = new Document();
                d.open();
                Paragraph paragraph = new Paragraph();
              //  paragraph.add(data);
                d.add(paragraph);
                d.close();
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
            throws IOException, DocumentException {
        System.out.println("model-->deleteTask-->");
        BufferedReader reader = null;
        PdfWriter writer=null;
        File file = new File(RESULT);
        StringBuilder sb = new StringBuilder();
        try {
            reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            IOUtillMethods.readerClose(reader);
        }
        Document document = new Document();
        writer = PdfWriter.getInstance(document,
                new FileOutputStream("/home/gokul/Git_projects/CoreJavaProject/modified_hi.pdf"));
        document.open();
        Paragraph p=new Paragraph(sb.toString());
        document.add(p);



        /*Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document,
                new FileOutputStream("/home/gokul/Git_projects/CoreJavaProject/modified_hi.pdf"));
        document.open();
        BufferedReader reader = new BufferedReader(new FileReader(RESULT));*/
        document.close();
        reader.close();
    }
}
