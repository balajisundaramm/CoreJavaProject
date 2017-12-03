package com.noteManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Balaji on 29/11/17.
 */
public class ViewUtilityMethods {
    public static void subMenuForLoading() {
        System.out.println("Press 1 to add a Task");
        System.out.println("Press 2 to list Tasks");
        System.out.println("Press 3 to edit a Task");
        System.out.println("Press 4 to remove a Task");
        System.out.println("Press 5 to search a Task");
        System.out.println("Press 6 to go back");
        System.out.println("Enter your choice");
    }

    public static void menuForList() {
        System.out.println("Press 1 to list by name");
        System.out.println("Press 2 to list by priority");
        System.out.println("Press 3 to list by due date");
        System.out.println("Press 4 to list by created date");
        System.out.println("Enter your choice");
    }

    public static void menuForStatus() {
        System.out.println("Press 1 for IN-PROCESS");
        System.out.println("Press 2 for Yet to be STARTED");
        System.out.println("Press 3 for COMPLETED");
        System.out.println("Press 4 for CANCELLED");
    }

    public static List<EmailBean> menuForEmail(){
        Scanner sc1= new Scanner(System.in);
        Scanner sc2= new Scanner(System.in);
        String result="";
        System.out.println("Enter the email id from which you want to send an mail.");
        String email=sc2.nextLine();
        result=NoteManagerValidations.validateEmailId(email);
        while (!result.equals(Constants.SUCCESS)) {
            System.err.println(result);
            System.out.println("Enter the email Id again");
            email = sc2.nextLine();
            result = NoteManagerValidations.validateEmailId(email);
        }
        System.out.println("From Email Id added successfully!!!");
        System.out.println("------------------------");
        System.out.println("Enter the password of the email id");
        String pass=sc2.nextLine();
        NoteManagerValidations.inputValidation(pass);
        System.out.println("password added successfully!!!");
        System.out.println("------------------------");
        System.out.println("Enter the email id to whom you want to send an mail.");
        String toEmail=sc2.nextLine();
        result=NoteManagerValidations.validateEmailId(toEmail);
        while (!result.equals(Constants.SUCCESS)) {
            System.err.println(result);
            System.out.println("Enter the email Id again");
            toEmail = sc2.nextLine();
            result = NoteManagerValidations.validateEmailId(toEmail);
        }
        System.out.println("To Email Id added successfully!!!");
        System.out.println("------------------------");
        System.out.println("Enter the subject of the mail...");
        String subject=sc2.nextLine();
        System.out.println("Subject added successfully!!!");
        System.out.println("------------------------");
        System.out.println("Enter the content of the body of the mail...");
        String content=sc2.nextLine();
        System.out.println("Content added successfully!!!");
        System.out.println("------------------------");
        System.out.println("Creating Email Bean...");
        EmailBean bean=new EmailBean(email,pass,toEmail,subject,content);
        List<EmailBean> list=new ArrayList<EmailBean>();
        list.add(bean);
        return list;
    }

    public static String casesForStatus(int ch, boolean flag){
        String result="";

        return result;
    }
}
