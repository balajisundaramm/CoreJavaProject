package com.noteManager;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

/**
 * Created by Balaji on 1/12/17.
 */
public class EmailSendingCode {
    public static String SendMaill(String fromEmailId, String password, String toEmailId, String subject, String content, String fileName) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.debug", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        //   props.put("mail.smtp.localhost", "www.uttarainfo.com");

        Session s = Session.getInstance(props, null);
        s.setDebug(true);
        Multipart multipart=new MimeMultipart();
        MimeMessage message = new MimeMessage(s);
        MimeBodyPart contentPart=new MimeBodyPart();
        MimeBodyPart attachPart=new MimeBodyPart();


        try {

            InternetAddress from = new InternetAddress(fromEmailId, "Balaji");// "Govind"  your name

            InternetAddress to = new InternetAddress(toEmailId);

            //message.setSentDate(new Date());
            message.setFrom(from);
            message.addRecipient(Message.RecipientType.TO, to);

            message.setSubject(subject);
            contentPart.setContent(content, "text/plain");
            String filename =fileName;
            DataSource source = new FileDataSource(filename);
            attachPart.setDataHandler(new DataHandler(source));
            attachPart.setFileName(filename);

            multipart.addBodyPart(contentPart);
            multipart.addBodyPart(attachPart);
            message.setContent(multipart);

            Transport tr = s.getTransport("smtp");
            tr.connect("smtp.gmail.com", fromEmailId, password);
            message.saveChanges();
            tr.sendMessage(message, message.getAllRecipients());
            System.out.println("..........................................");
            tr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "SUCCESS";
    }

}
