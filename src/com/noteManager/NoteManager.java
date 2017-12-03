package com.noteManager;

import java.util.List;
import java.util.Scanner;

/**
 * Created by Balaji on 27/11/17.
 */
public class NoteManager {
    public static void main(String[] args) {
        Scanner sc1 = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);

        // First menu in console
        int ch = 0;
        while (ch != 7) {
            sc1= new Scanner(System.in);
            System.out.println("Press 1 to add catagory");
            System.out.println("Press 2 to load catagory");
            System.out.println("Press 3 to search catagory");
            System.out.println("Press 4 to list catagory");
            System.out.println("Press 5 to generate pdf");
            System.out.println("Press 6 to send email");
            System.out.println("Press 7 to exit");
            System.out.println("Enter the choice");
            if (!sc1.hasNextInt()) {
                System.err.println("Enter only integers");
            } else {
                ch = sc1.nextInt();
                cases(ch);
            }
        }
    }

    public static void cases(int ch) {
        Scanner sc1 = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        NBModel model=new NBModel();
        switch (ch) {
            case 1:
                System.out.println("Adding Catagory");
                System.out.println("Enter the name of the catagory");
                String catagory = sc2.nextLine();
                // validation
                String result =NoteManagerValidations.validateCatagoryName(catagory);
                while (!result.equals("SUCCESS")) {
                    System.err.println(result);
                    System.out.println("Enter the name of the catagory");
                    catagory = sc2.nextLine();
                    result = NoteManagerValidations.validateCatagoryName(catagory);
                }
                result=NBModel.addCatagory(catagory);
                if (result.equals("SUCCESS")) {
                    System.out.println("Catagory added successfully!!!");
                }
                else {
                    System.out.println("Oops something went wrong!!!");
                }
                System.out.println("----------------------");

                ViewUtilityMethods.subMenuForLoading();
                int ch2;
                if (!sc1.hasNextInt()) {
                    throw new IllegalArgumentException("Enter only integers");
                } else {
                    ch2 = sc1.nextInt();
                    NBMenu.subCatagoryCases(ch2,catagory);
                }


                break;
            case 2:
                    System.out.println("Loading the catagory");
                    System.out.println("--------------------");
                boolean flag=true;
                boolean loadResult=false;
                String loadName="";
                while (flag) {
                    System.out.println("List of catagories are,");
                    //System.out.println(model.showCatagories());
                    for (String s : model.showCatagories()) {
                        System.out.println(s);
                    }
                    System.out.println();
                    System.out.println("Pick the name of the catagory from the above list, " +
                            "which you want to load and type it(CASE SENSITIVE)");
                    loadName = sc2.nextLine();
                    for (String s : model.showCatagories()) {
                        if ((loadName + ".note").equals(s + ".note")) {
                            loadResult = true;
                            flag=false;
                            break;
                        } else {
                            loadResult = false;
                            flag=true;
                        }

                    }
                }
                flag=true;
                while (flag) {
                    if (loadResult) {
                        ch=0;
                        while (ch!=6) {
                            ViewUtilityMethods.subMenuForLoading();
                            sc1 = new Scanner(System.in);
                            if (!sc1.hasNextInt()) {
                                System.err.println("Enter only integers");
                            } else {
                                ch = sc1.nextInt();
                                NBMenu.subCatagoryCases(ch, loadName);
                                flag = false;
                            }
                        }
                    } else {
                        System.err.println("The name you entered is not valid.");
                        flag = true;
                    }
                }
                break;
            case 3:
                System.out.println("Searching catagories...");
                System.out.println("-------------------------");
                flag=true;
                while (flag) {
                    System.out.println("Enter the keyword");
                    String keyWord = sc2.nextLine();
                    NoteManagerValidations.inputValidation(keyWord);
                    List<String> list = model.searchCatagories(keyWord);
                    if (list.size() != 0) {
                        System.out.println("Total number of matches: " + list.size());
                        for (String s : list) {
                            System.out.println("List of matched catagories are,");
                            System.out.println(s);
                        }
                        flag=false;
                    } else {
                        System.out.println("No matches found search again...");
                        flag=true;
                    }
                }

                break;
            case 4:
                System.out.println("Listing the catagories");
                System.out.println("enter 1 to list the catagories by name");
                System.out.println("enter 2 to list the catagories by created date");
                if (!sc1.hasNextInt()) {
                    throw new IllegalArgumentException("Enter only integers");
                } else {
                    ch = sc1.nextInt();
                        switch (ch) {
                                case 1:
                                    System.out.println("Listing the catagories by name");
                                    ListOfCollections.printMap(model.listByName());
                                    break;
                                case 2:
                                    System.out.println("Listing the catagories by Created date");
                                    ListOfCollections.printMapForCreatedDate(model.listByDate());
                                    break;
                                default:
                                    System.out.println("Enter the value 1 to 2");
                                    cases(4);
                        }
                    }
                break;
            case 5:
                System.out.println("Generate  Pdf");
                System.out.println("---------------");
                System.out.println("Enter 1 to generate pdf for specfic catagory");
                System.out.println("Enter 2 to generate pdf for all catagories");
                if (sc1.hasNextInt()){
                    ch=sc1.nextInt();
                    switch (ch){
                        case 1:
                            System.out.println("Generating pdf for specific catagory");
                            for (String s : model.showCatagories()) {
                                System.out.println(s);
                            }
                            System.out.println("----------------------------");
                            System.out.println("Enter the name of the catagory in the above list" +
                                    "Note : (CASE SENSITIVE)");
                            catagory=sc2.nextLine();
                            NoteManagerValidations.inputValidation(catagory);
                            result=model.generatePdfForSpecific(catagory);
                            if (result.equals(Constants.SUCCESS)){
                                System.out.println("The new pdf file for the catagory "+catagory+" is generated..");
                            }
                            else {
                                System.out.println("Oops, something went wrong in generating pdf file..");
                            }
                            break;
                        case 2:
                            System.out.println("Generating pdf for all catagories till created.");
                            System.out.println("-------------------------------------------------");
                            result=model.generatePdfForAllCatagories();
                            if (result.equals(Constants.SUCCESS)){
                                System.out.println("The new pdf file for all catagories is generated..");
                            }
                            else {
                                System.out.println("Oops, something went wrong in generating pdf file..");
                            }


                            break;
                        default:
                            System.out.println("Enter the value 1 or 2.");
                            break;
                    }
                }
                else {
                    throw new IllegalArgumentException("Enter only integer..");
                }
                break;
            case 6:
                System.out.println("Enter 1 to send email of all tasks in specfic catagory");
                System.out.println("Enter 2 to send email of all tasks is all catagories till created");
                System.out.println("Enter your choice");
                if (sc1.hasNext()) {
                    ch = sc1.nextInt();
                    switch (ch){
                        case 1:
                            System.out.println("Sending email of all tasks in " +
                                    "specfic catagory");
                            System.out.println("---------------------------------");
                            List<EmailBean> list=ViewUtilityMethods.menuForEmail();
                            EmailBean bean=null;
                            for (EmailBean bean1:list) {
                                bean=bean1;
                            }
                            for (String s : model.showCatagories()) {
                                System.out.println(s);
                            }
                            System.out.println("----------------------------");
                            System.out.println("Enter the name of the catagory in the above list" +
                                    "Note : (CASE SENSITIVE)");
                            catagory=sc2.nextLine();
                            NoteManagerValidations.inputValidation(catagory);
                            result=model.sendEmail(catagory,bean);
                            if (result.equals(Constants.SUCCESS)){
                                System.out.println("Email has been sent!!!");
                            }
                            else {
                                System.out.println("Oops, something went wrong in sending email..");
                            }
                            break;
                        case 2:
                            System.out.println("Sending email of all tasks in " +
                                    "all catagories till created");
                            List<EmailBean> list2=ViewUtilityMethods.menuForEmail();
                            EmailBean bean2=null;
                            for (EmailBean bean1:list2) {
                                bean2=bean1;
                            }
                            result=model.sendEmailAllCatagories(bean2);
                            if (result.equals(Constants.SUCCESS)){
                                System.out.println("Email has been sent!!!");
                            }
                            else {
                                System.out.println("Oops, something went wrong in sending email..");
                            }

                            break;
                        default:
                            break;
                    }
                }
                else {
                    throw new IllegalArgumentException("Enter only Integer");
                }
                break;
            case 7:
                System.out.println("Thank you for visiting us!!!");
                break;
            default:
                System.out.println("Enter the integer value only 1 to 7");
                break;
        }
    }



}
