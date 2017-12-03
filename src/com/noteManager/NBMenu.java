package com.noteManager;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Balaji on 27/11/17.
 */
public class NBMenu {
    public static void subCatagoryCases(int ch,String catagory) {
        Scanner sc1 = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        NBModel model=new NBModel();
        String result;
        switch (ch){
            case 1:
                System.out.println("Adding Task");
                System.out.println("-------------");
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

                System.out.println("Give us a brief description related to the task");
                String des=sc2.nextLine();
                result=NoteManagerValidations.validateDescription(des);
                while (!result.equals(Constants.SUCCESS)){
                    System.err.println(result);
                    System.out.println("Give us a brief description related to the task");
                    des=sc2.nextLine();
                    result=NoteManagerValidations.validateDescription(des);
                }
                System.out.println("Description added Succesfully");
                System.out.println("--------------------------");

                System.out.println("Enter the due date. Task supposed to be " +
                        "completed within due date. format(YYYY/MM/DD)");
                String dueDate=sc1.next();
                result=NoteManagerValidations.validateDate(dueDate);
                while (!result.equals(Constants.SUCCESS)){
                    System.err.println(result);
                    System.out.println("Enter the due date. Task supposed to be " +
                            "completed within due date. format(YYYY/MM/DD)");
                    dueDate=sc1.next();
                    result=NoteManagerValidations.validateDate(dueDate);
                }
                System.out.println("Due date added successfully!!!");
                System.out.println("------------------------------");

                System.out.println("Enter the priority level (1-10) 1-->low priority " +
                        "10--> high priority");
                int priority=0;
                boolean flag=true;
                while (flag) {
                    if (sc1.hasNextInt()) {
                        priority = sc1.nextInt();
                        flag = false;
                    } else {
                        System.out.println("Enter the integer value");
                        flag = true;
                    }
                }
                String result4=NoteManagerValidations.validatePriority(priority);;
                while (!result4.equals(Constants.SUCCESS)) {
                    System.err.println(result4);
                    System.out.println("Enter the priority level (1-10)");
                    if (sc1.hasNextInt()) {
                        priority = sc1.nextInt();
                        result4 = NoteManagerValidations.validatePriority(priority);
                    }
                    else {
                        throw new IllegalArgumentException("Enter the integer value");
                    }
                }
                System.out.println("Priority is added successfully");
                System.out.println("-------------------------------");

                System.out.println("Enter the tags(keywords related to task)" +
                        " you want to add to the task this would be helpful" +
                        " when you search the task (COMMA SEPERATED EX: tag1,tag2)");
                String tagline=sc2.nextLine();
                result=NoteManagerValidations.validateTag(tagline);
                while (!result.equals(Constants.SUCCESS)) {
                    System.err.println(result);
                    System.out.println("Enter the tags(keywords related to task)" +
                            " you want to add to the task this would be helpful" +
                            " when you search the task (COMMA SEPERATED EX: tag1,tag2)");
                    tagline=sc2.nextLine();
                    result=NoteManagerValidations.validateTag(tagline);
                }
                System.out.println("Tags added successfully!!!");
                System.out.println("----------------------");

                Date createdDate = new Date();
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
                String cre_date=sdf.format(createdDate);
                //String cre_DateString=createdDate.toString();

                flag=true;
                String status = "";
                while (flag) {
                    ViewUtilityMethods.menuForStatus();
                    System.out.println("Enter the choice");
                    if (sc1.hasNext()) {
                        ch = sc1.nextInt();
                        switch (ch){
                            case 1:
                                status="IN-PROCESS";
                                flag=false;
                                break;
                            case 2:
                                status="Yet to be STARTED";
                                flag=false;
                                break;
                            case 3:
                                status="COMPLETED";
                                flag=false;
                                break;
                            case 4:
                                status="CANCELLED";
                                flag=false;
                                break;
                            default:
                                System.out.println("Enter the value from 1 to 4");
                                flag=true;
                                break;
                        }
                    } else {
                        System.out.println("Enter only numbers..");
                    }
                }
                // creating bean and invoking business logic
                System.out.println("creating bean");
                TaskBean addbean=new TaskBean(name,des,dueDate,tagline,status,cre_date,priority);
                System.out.println("Invoking model-->addContact()");
                result=model.addTask(addbean,catagory);
                System.out.println("main()-> result from addContact() "+result);
                if(result.equals(Constants.SUCCESS))
                {
                    System.out.println("Task name "+name+" has been added " +
                            "successfully to the catagory-->"+catagory);
                }
                else
                {
                    System.err.println("oops some problem in adding..."+result);
                }
                System.out.println("------------------------------------");
                break;

            case 2:
                System.out.println("Listing Tasks in the catagory-->"+catagory);
                System.out.println("----------------");

                ViewUtilityMethods.menuForList();
                if (!sc1.hasNextInt()) {
                    throw new IllegalArgumentException("Enter only integers");
                } else {
                    ch = sc1.nextInt();
                    switch (ch) {
                        case 1:
                            System.out.println("Listing the tasks in the catagory "
                                    +catagory+" by name");
                            for (String s:model.taskListByName(catagory)) {
                                System.out.println(s);
                            }
                            break;
                        case 2:
                            System.out.println("Listing the tasks in the catagory "
                                    +catagory+" by priority");
                            ListOfCollections.printMapInteger(model.taskListByPriority(catagory));


                            break;
                        case 3:
                            System.out.println("Listing the tasks in the catagory "
                                    +catagory+" by due date");
                            ListOfCollections.printMapDate(model.taskListByDue_Date(catagory));

                            break;
                        case 4:
                            System.out.println("Listing the tasks in the catagory "
                                    +catagory+" by Created date");
                            ListOfCollections.printMapDate(model.taskListByCreated_date(catagory));

                            break;
                        default:
                            System.out.println("Enter the value from 1 to 4");
                    }
                }



                break;
            case 3:
                System.out.println("Editing a task");
                System.out.println("---------------");
                flag=true;
                while (flag) {
                    System.out.println("List of tasks in the catagory "+catagory+" is,");
                    for (String s : model.showTasks(catagory)) {
                        System.out.println(s);
                    }
                    System.out.println("Enter the task name which you want to edit" +
                            "Note:(CASE SENSITIVE)");
                    String editingTaskName = sc2.nextLine();
                    boolean findTask = false;
                    for (String s : model.showTasks(catagory)) {
                        if ((editingTaskName).equals(s)) {
                            findTask = true;
                            System.out.println("task found");
                            break;
                        }
                        else
                            findTask = false;
                    }

                    if (findTask) {
                        // print the details of the task
                        System.out.println(model.editTask(catagory,editingTaskName));
                        subCatagoryCases(1,catagory);
                        model.deleteTask(catagory,editingTaskName);
                        System.out.println("The task is editted successfully!!!");
                        flag=false;
                    }
                    else {
                        System.err.println("Tne name you entered is invalid.");
                        flag=true;
                    }
                }
                System.out.println("---------------------------");

                break;
            case 4:
                System.out.println("Removing a task");
                flag=true;
                while (flag) {
                    System.out.println("List of tasks in the catagory "+catagory+" is,");
                    for (String s : model.showTasks(catagory)) {
                        System.out.println(s);
                    }
                    System.out.println("Enter the task name which you want to delete " +
                            "Note:(CASE SENSITIVE)");
                    String deleteTaskName = sc2.nextLine();
                    boolean findTask = false;

                    for (String s : model.showTasks(catagory)) {
                        if ((deleteTaskName).equals(s)) {
                            findTask = true;
                            break;
                        } else
                            findTask = false;
                    }


                    if (findTask) {
                        String deletedResult=model.deleteTask(catagory,deleteTaskName);
                        if (deletedResult.equals(Constants.SUCCESS))
                            System.out.println("Task deleted successfully!!!");
                        else
                            System.out.println(deletedResult);
                        flag=false;
                    }
                    else {
                        System.err.println("Tne name you entered is invalid.");
                        flag=true;
                    }
                }
                break;
            case 5:
                System.out.println("Searching tasks in the catagory : "+catagory);
                System.out.println("---------------------------------------------");
                flag=true;
                while (flag) {
                    System.out.println("Enter the keyword");
                    String keyWord = sc2.nextLine();
                    NoteManagerValidations.inputValidation(keyWord);
                    List<Map<String,String>> list=model.searchTasks(catagory,keyWord);


                    if (list.size()!=0){

                        for (Map map:list) {
                            ListOfCollections.printMapString(map);
                        }
                        flag=false;
                    }
                    else {
                        System.out.println("No matches found search again...");
                        flag=true;
                    }
                }
                break;
            case 6:
                System.out.println("tata");

                break;
            default:
                System.out.println("Enter the integer value 1 to 6");
                break;
        }

    }
}
/*
balajitpgit13@gmail.com

balaji@1234*/
