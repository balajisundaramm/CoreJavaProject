package com.noteManager;

import java.util.Scanner;

/**
 * Created by Balaji on 4/12/17.
 */
public class AddTaskMethods {
    public static String addName(String catagory) {
        Scanner sc1=new Scanner(System.in);
        Scanner sc2=new Scanner(System.in);
        String result="";
        System.out.println("Enter the name of the Task");
        String name=sc2.nextLine();
        result=NoteManagerValidations.validateTaskName(catagory,name);
        while (!result.equals(Constants.SUCCESS)) {
            System.err.println(result);
            System.out.println("Enter the name of the Task");
            name = sc2.nextLine();
            result = NoteManagerValidations.validateTaskName(catagory,name);
        }
        System.out.println("Name added successfully!!!");
        System.out.println("------------------------");
        return name;
    }

    public static String addDescription() {
        Scanner sc1 = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        String result = "";
        System.out.println("Give us a brief description related to the task");
        String des = sc2.nextLine();
        result = NoteManagerValidations.validateDescription(des);
        while (!result.equals(Constants.SUCCESS)) {
            System.err.println(result);
            System.out.println("Give us a brief description related to the task");
            des = sc2.nextLine();
            result = NoteManagerValidations.validateDescription(des);
        }
        System.out.println("Description added Succesfully");
        System.out.println("--------------------------");
        return des;
    }

    public static String addDueDate() {
        Scanner sc1 = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        String result = "";
        System.out.println("Enter the due date. Task supposed to be " +
                "completed within due date. format(YYYY/MM/DD)");
        String dueDate = sc1.next();
        result = NoteManagerValidations.validateDate(dueDate);
        while (!result.equals(Constants.SUCCESS)) {
            System.err.println(result);
            System.out.println("Enter the due date. Task supposed to be " +
                    "completed within due date. format(YYYY/MM/DD)");
            dueDate = sc1.next();
            result = NoteManagerValidations.validateDate(dueDate);
        }
        System.out.println("Due date added successfully!!!");
        System.out.println("------------------------------");
        return dueDate;
    }
    public static int addPriority() {
        Scanner sc1 = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        String result = "";
        System.out.println("Enter the priority level (1-10) 1-->low priority " +
                "10--> high priority");
        int priority =1;
        boolean flag = true;
        while (flag) {
            sc1=new Scanner(System.in);
            if (sc1.hasNextInt()) {
                priority = sc1.nextInt();
                if (priority>0 && priority<11) {
                    flag = false;
                }
                else {
                    System.err.println("Priority must be from 1 to 10");
                    flag=true;
                }
            } else {
                System.err.println("Enter the integer value");
                flag = true;
            }
        }
        String result4 = NoteManagerValidations.validatePriority(priority);
        ;
        while (!result4.equals(Constants.SUCCESS)) {
            System.err.println(result4);
            System.out.println("Enter the priority level (1-10)");
            if (sc1.hasNextInt()) {
                priority = sc1.nextInt();
                result4 = NoteManagerValidations.validatePriority(priority);
            } else {
                throw new IllegalArgumentException("Enter the integer value");
            }
        }
        System.out.println("Priority is added successfully");
        System.out.println("-------------------------------");
        return priority;
    }

    public static String addTags() {
        Scanner sc1 = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        String result = "";
        System.out.println("Enter the tags(keywords related to task)" +
                " you want to add to the task this would be helpful" +
                " when you search the task (COMMA SEPERATED EX: tag1,tag2)");
        String tagline = sc2.nextLine();
        result = NoteManagerValidations.validateTag(tagline);
        while (!result.equals(Constants.SUCCESS)) {
            System.err.println(result);
            System.out.println("Enter the tags(keywords related to task)" +
                    " you want to add to the task this would be helpful" +
                    " when you search the task (COMMA SEPERATED EX: tag1,tag2)");
            tagline = sc2.nextLine();
            result = NoteManagerValidations.validateTag(tagline);
        }
        System.out.println("Tags added successfully!!!");
        System.out.println("----------------------");
        return tagline;
    }

    public static String addStatus() {
        Scanner sc1 = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        String result = "";
        boolean flag = true;
        int ch = 0;
        String status = "";
        while (flag) {
            ViewUtilityMethods.menuForStatus();
            System.out.println("Enter the choice");
            if (sc1.hasNext()) {
                ch = sc1.nextInt();
                switch (ch) {
                    case 1:
                        status = "IN-PROCESS";
                        flag = false;
                        break;
                    case 2:
                        status = "Yet to be STARTED";
                        flag = false;
                        break;
                    case 3:
                        status = "COMPLETED";
                        flag = false;
                        break;
                    case 4:
                        status = "CANCELLED";
                        flag = false;
                        break;
                    default:
                        System.out.println("Enter the value from 1 to 4");
                        flag = true;
                        break;
                }
            } else {
                System.out.println("Enter only numbers..");
            }
        }
        return status;
    }
}
