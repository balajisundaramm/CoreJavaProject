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

                String name=AddTaskMethods.addName(catagory);
                String des=AddTaskMethods.addDescription();
                String dueDate=AddTaskMethods.addDueDate();
                int priority=AddTaskMethods.addPriority();
                String tagline=AddTaskMethods.addTags();
                String status=AddTaskMethods.addStatus();

                Date createdDate = new Date();
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
                String cre_date=sdf.format(createdDate);
                // creating bean and invoking business logic
               // System.out.println("creating bean");
                TaskBean addbean=new TaskBean(name,des,dueDate,tagline,status,cre_date,priority);
                //System.out.println("Invoking model-->addContact()");
                result=model.addTask(addbean,catagory);
                //System.out.println("main()-> result from addContact() "+result);
                if(result.equals(Constants.SUCCESS))
                {
                    System.out.println("Task name "+name+" has been added " +
                            "successfully to the category-->"+catagory);
                }
                else
                {
                    System.err.println("oops some problem in adding..."+result);
                }
                System.out.println("------------------------------------");
                break;

            case 2:
                System.out.println("Listing Tasks in the category-->"+catagory);
                System.out.println("----------------");
                boolean flag=true;
                while (flag) {
                    ViewUtilityMethods.menuForList();
                    sc1 = new Scanner(System.in);
                    if (!sc1.hasNextInt()) {
                        System.err.println("Enter only integers");
                        flag=true;
                    } else {
                        ch = sc1.nextInt();
                        switch (ch) {
                            case 1:
                                System.out.println("Listing the tasks in the category "
                                        + catagory + " by name");
                                if (model.taskListByName(catagory).size()>0){
                                    for (String s : model.taskListByName(catagory)) {
                                        System.out.println(s);
                                    }
                                    flag = false;
                                }
                                else {
                                    System.err.println("There are no tasks.. add task first and then list..");
                                    flag=false;
                                }
                                break;
                            case 2:
                                System.out.println("Listing the tasks in the category "
                                        + catagory + " by priority");
                                if (model.taskListByPriority(catagory).size()>0) {
                                    ListOfCollections.printMapInteger(model.taskListByPriority(catagory));
                                    flag = false;
                                }
                                 else {
                                    System.err.println("There are no tasks.. add task first and then list..");
                                    flag = false;
                                }

                                break;
                            case 3:
                                System.out.println("Listing the tasks in the category "
                                        + catagory + " by due date");
                                if (model.taskListByDue_Date(catagory).size()>0) {
                                    ListOfCollections.printMapDate(model.taskListByDue_Date(catagory));
                                    flag = false;
                                }
                                else {
                                    System.err.println("There are no tasks.. add task first and then list..");
                                    flag = false;
                                }

                                break;
                            case 4:
                                System.out.println("Listing the tasks in the category "
                                        + catagory + " by Created date");
                                if (model.taskListByDue_Date(catagory).size()>0) {
                                    ListOfCollections.printMapDate(model.taskListByCreated_date(catagory));
                                    flag = false;
                                }
                                else {
                                    System.err.println("There are no tasks.. add task first and then list..");
                                    flag = false;
                                }
                                break;
                            default:
                                System.out.println("Enter the value from 1 to 4");
                                flag=true;

                        }
                    }
                }
                break;
            case 3:
                System.out.println("Editing a task");
                System.out.println("---------------");
                flag=true;
                while (flag) {
                    if (model.showTasks(catagory).size() > 0) {
                        System.out.println("List of tasks in the category " + catagory + " is,");
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
                            } else
                                findTask = false;
                        }

                        if (findTask) {
                            // print the details of the task
                            System.out.println(model.showTaskDetails(catagory, editingTaskName));
                            List<TaskBean> list = model.editTask(catagory, editingTaskName);
                            TaskBean bean = null;
                            for (TaskBean bean1 : list) {
                                bean = bean1;
                            }
                            flag = true;
                            while (flag) {
                                sc1 = new Scanner(System.in);
                                ViewUtilityMethods.menuForEdit();
                                if (!sc1.hasNextInt()) {
                                    System.err.println("Enter only numbers");
                                    flag = true;
                                } else {
                                    ch = sc1.nextInt();
                                    switch (ch) {
                                        case 1:
                                            bean.setName(AddTaskMethods.addName(catagory));
                                            sc1 = new Scanner(System.in);
                                            break;
                                        case 2:
                                            bean.setDescription(AddTaskMethods.addDescription());
                                            sc1 = new Scanner(System.in);
                                            break;
                                        case 3:
                                            bean.setPriority(AddTaskMethods.addPriority());
                                            sc1 = new Scanner(System.in);
                                            break;
                                        case 4:
                                            bean.setDueDate(AddTaskMethods.addDueDate());
                                            sc1 = new Scanner(System.in);
                                            break;
                                        case 5:
                                            bean.setStatus(AddTaskMethods.addStatus());
                                            sc1 = new Scanner(System.in);
                                            break;
                                        case 6:
                                            bean.setTagLine(AddTaskMethods.addTags());
                                            sc1 = new Scanner(System.in);
                                            break;
                                        case 7:
                                            System.out.println("Thank you for editing...");
                                            flag = false;
                                            break;
                                        default:
                                            System.err.println("Enter the value from 1 to 7");
                                            break;
                                    }
                                }

                            }
                            createdDate = new Date();
                            sdf = new SimpleDateFormat("yyyy/MM/dd");
                            cre_date = sdf.format(createdDate);
                            bean.setCre_Date(cre_date);
                            System.out.println("Invoking model-->addTask()");
                            result = model.addTask(bean, catagory);
                            System.out.println("main()-> result from addTask() " + result);
                            if (result.equals(Constants.SUCCESS)) {
                                System.out.println("New Task, " + bean.getName() + " has been added " +
                                        "successfully to the category-->" + catagory);
                            } else {
                                System.err.println("oops some problem in editting the task..." + result);
                            }
                            System.out.println("------------------------------------");
                            model.deleteTask(catagory, editingTaskName);
                            System.out.println("The task is editted successfully!!!");
                            flag = false;
                        } else {
                            System.err.println("Tne name you entered is invalid.");
                            flag = true;
                        }
                    } else {
                        System.err.println("There are no tasks. First add then edit..");
                        flag=false;
                    }
                    System.out.println("---------------------------");
                }
                break;
            case 4:
                System.out.println("Removing a task");
                flag=true;
                while (flag) {
                    if (model.showTasks(catagory).size() > 0) {
                        System.out.println("List of tasks in the category " + catagory + " is,");
                        for (String s : model.showTasks(catagory)) {
                            System.out.println(s);
                        }
                        System.out.println("Enter the task name which you want to delete  " +
                                "(Note:CASE SENSITIVE)");
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
                            String deletedResult = model.deleteTask(catagory, deleteTaskName);
                            if (deletedResult.equals(Constants.SUCCESS))
                                System.out.println("Task deleted successfully!!!");
                            else
                                System.out.println(deletedResult);
                            flag = false;
                        } else {
                            System.err.println("Tne name you entered is invalid.");
                            flag = true;
                        }
                    } else {
                        System.err.println("There are no tasks. Add first and then remove.");
                        flag = false;
                    }
                }
                break;
            case 5:
                System.out.println("Searching tasks in the category : "+catagory);
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
