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
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by Balaji on 1/12/17.
 */
public class EmailSending {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String fromEmailId = "balajitpgit13@gmail.com";  // it should be your
        // gmailId
        String password = "balaji@1234";                  // from emailId's password
        String toEmailId = "balaji161192@gmail.com";        // it should be gmailId (to
        // whom you want to send)

        String subject="** Testing send email from Java **";

        String content="Hello, This is Balaji....";

       /* Calendar today = Calendar.getInstance();
        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            public void run() {
                long min= LocalDateTime.now().getMinute();
                long hour=LocalDateTime.now().getHour();
                if(hour==10 && (min==50)){                //6:43PM        //Time at which you want to send the mail //for PM add +12 to your time
                    System.out.println("Sending mail now");
       */             SendMaill(fromEmailId, password, toEmailId,subject,content);
         /*       }else{
                    System.out.println("Checking for the time");
                }
            }
        }, today.getTime(), TimeUnit.SECONDS.toMillis(5)); //TimeUnit.SECONDS.toMillis(5) -->> interval*/
    }

    public static void SendMaill(String fromEmailId, String password, String toEmailId, String subject, String content) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.debug", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.localhost", "www.uttarainfo.com");

        Session s = Session.getInstance(props, null);
        s.setDebug(true);
        Multipart multipart=new MimeMultipart();
        MimeMessage message = new MimeMessage(s);
        MimeBodyPart mimeBodyPart=new MimeBodyPart();
        MimeBodyPart attachPart=new MimeBodyPart();

        try {

            InternetAddress from = new InternetAddress(fromEmailId, "Balaji");// "Govind"  your name

            InternetAddress to = new InternetAddress(toEmailId);

            message.setSentDate(new Date());
            message.setFrom(from);
            message.addRecipient(Message.RecipientType.TO, to);

            message.setSubject(subject);
            String filename = "hi.pdf";//change accordingly
            DataSource source = new FileDataSource(filename);
            attachPart.setDataHandler(new DataHandler(source));
            attachPart.setFileName(filename);
            mimeBodyPart.setContent(content, "text/plain");

            multipart.addBodyPart(mimeBodyPart);
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
    }

}
