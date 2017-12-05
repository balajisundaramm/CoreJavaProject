package com.noteManager;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by Balaji on 27/11/17.
 */
public class NoteManager {
    public static void main(String[] args) {
        Scanner sc1 = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);

        int ch = 0;
        while (ch != 8) {
            sc1= new Scanner(System.in);
            System.out.println("Press 1 to add category");
            System.out.println("Press 2 to load category");
            System.out.println("Press 3 to search category");
            System.out.println("Press 4 to list category");
            System.out.println("Press 5 to generate pdf");
            System.out.println("Press 6 to send email");
            System.out.println("Press 7 to generate excel file");
            System.out.println("Press 8 to exit");
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
                System.out.println("Adding Category");
                System.out.println("Enter the name of the category");
                String catagory = sc2.nextLine();
                // validation
                String result =NoteManagerValidations.validateCatagoryName(catagory);
                while (!result.equals("SUCCESS")) {
                    System.err.println(result);
                    System.out.println("Enter the name of the category");
                    catagory = sc2.nextLine();
                    result = NoteManagerValidations.validateCatagoryName(catagory);
                }
                result=NBModel.addCatagory(catagory);
                if (result.equals("SUCCESS")) {
                    System.out.println("Category added successfully!!!");
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
                    System.out.println("Loading the category");
                    System.out.println("--------------------");
                boolean flag=true;
                boolean loadResult=false;
                String loadName="";
                while (flag) {
                    if (model.showCatagories().size() > 0) {
                        System.out.println("List of categories are,");
                        //System.out.println(model.showCatagories());
                        for (String s : model.showCatagories()) {
                            System.out.println(s);
                        }
                        System.out.println();
                        boolean sign=true;
                        while (sign) {
                            System.out.println("Pick the name of the category from the above list, " +
                                    "which you want to load and type it(CASE SENSITIVE)");
                            loadName = sc2.nextLine();
                            for (String s : model.showCatagories()) {
                                if ((loadName + ".note").equals(s + ".note")) {
                                    loadResult = true;
                                    sign = false;
                                    break;
                                } else {
                                    System.err.println("The name you entered is not valid.");
                                    loadResult = false;
                                    sign = true;
                                }
                            }
                        }
                        flag = true;
                        while (flag) {
                            if (loadResult) {
                                ch = 0;
                                while (ch != 6) {
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
                    }
                    else {
                        System.err.println("There are no categories.. add first and then load..");
                        flag=false;
                    }
                }
                break;
            case 3:
                System.out.println("Searching categories...");
                System.out.println("-------------------------");
                flag=true;
                while (flag) {
                    System.out.println("Enter the keyword");
                    String keyWord = sc2.nextLine();
                    NoteManagerValidations.inputValidation(keyWord);
                    List<Map<String,String>> list = model.searchCatagories(keyWord);
                    if (list.size() != 0) {
                        for (Map map:list) {
                            ListOfCollections.printMapString(map);
                        }
                        flag=false;
                    } else {
                        System.err.println("No matches found search again...");
                        flag=true;
                    }
                }

                break;
            case 4:
                flag=true;
                while (flag) {
                    System.out.println("Listing the categories");
                    System.out.println("enter 1 to list the categories by name");
                    System.out.println("enter 2 to list the categories by created date");
                    sc1=new Scanner(System.in);
                    if (!sc1.hasNextInt()) {
                        System.err.println("Enter only integers");
                        flag=true;
                    } else {
                        ch = sc1.nextInt();
                        switch (ch) {
                            case 1:
                                System.out.println("Listing the categories by name");
                                if (model.listByName().size()>0) {
                                    ListOfCollections.printMap(model.listByName());
                                    flag = false;
                                }
                                else {
                                    System.err.println("There are no categories. Add first and then list.");
                                    flag=false;
                                }
                                break;
                            case 2:
                                System.out.println("Listing the categories by Created date");
                                if (model.listByDate().size()>0) {
                                    ListOfCollections.printMapForCreatedDate(model.listByDate());
                                    flag = false;
                                }
                                else {
                                    System.err.println("There are no categories. Add first and then list.");
                                    flag=false;
                                }
                                break;
                            default:
                                System.err.println("Enter the value 1 to 2");
                                //cases(4);
                                flag=true;
                        }
                    }
                }
                break;
            case 5:

                    System.out.println("Generate  Pdf");
                    System.out.println("---------------");
                    System.out.println("Enter 1 to generate pdf for specfic category");
                    System.out.println("Enter 2 to generate pdf for all categories");
                    if (sc1.hasNextInt()) {
                        ch = sc1.nextInt();
                        switch (ch) {
                            case 1:
                                System.out.println("Generating pdf for specific category");
                                if (model.showCatagories().size()>0) {
                                    for (String s : model.showCatagories()) {
                                        System.out.println(s);
                                    }
                                    System.out.println("----------------------------");
                                    flag = true;
                                    while (flag) {
                                        System.out.println("Enter the name of the category in the above list" +
                                                "Note : (CASE SENSITIVE)");
                                        catagory = sc2.nextLine();
                                        if (NoteManagerValidations.inputValidation(catagory) &&
                                                !ListOfCollections.isCatagoryUnique(catagory)) {
                                            result = model.generatePdfForSpecific(catagory);
                                            if (result.equals(Constants.SUCCESS)) {
                                                System.out.println("The new pdf file for the catagory " + catagory + " is generated..");
                                            } else {
                                                System.out.println("Oops, something went wrong in generating pdf file..");
                                            }
                                            flag = false;
                                        } else {
                                            System.err.println("The category you entered doesn't exist..");
                                            flag = true;
                                        }
                                    }
                                }
                                else {
                                    System.err.println("There are no categories. add first then generate pdf.");
                                }
                                break;
                            case 2:
                                System.out.println("Generating pdf for all categories till created.");
                                System.out.println("-------------------------------------------------");
                                result = model.generatePdfForAllCatagories();
                                if (result.equals(Constants.SUCCESS)) {
                                    System.out.println("The new pdf file for all categories is generated..");
                                } else {
                                    System.out.println("Oops, something went wrong in generating pdf file..");
                                }
                                flag = false;
                                break;
                            default:
                                System.out.println("Enter the value 1 or 2.");
                                flag = true;
                                break;
                        }
                    }
                break;
            case 6:
                System.out.println("Sending email....");
                System.out.println("-------------------");
                    sc1 = new Scanner(System.in);
                    System.out.println("Enter 1 to send email of all tasks in specfic category");
                    System.out.println("Enter 2 to send email of all tasks is all categories till created");
                    System.out.println("Enter your choice");
                    if (sc1.hasNextInt()) {
                        ch = sc1.nextInt();
                        switch (ch) {
                            case 1:
                                System.out.println("Sending email of all tasks in " +
                                        "specfic category");
                                System.out.println("---------------------------------");
                                if (model.showCatagories().size()>0) {
                                    List<EmailBean> list = ViewUtilityMethods.menuForEmail();
                                    EmailBean bean = null;
                                    for (EmailBean bean1 : list) {
                                        bean = bean1;
                                    }
                                    for (String s : model.showCatagories()) {
                                        System.out.println(s);
                                    }
                                    System.out.println("----------------------------");
                                    flag = true;
                                    while (flag) {
                                        System.out.println("Enter the name of the category in the above list" +
                                                "Note : (CASE SENSITIVE)");
                                        catagory = sc2.nextLine();
                                        if (NoteManagerValidations.inputValidation(catagory) &&
                                                !ListOfCollections.isCatagoryUnique(catagory)) {
                                            result = model.sendEmail(catagory, bean);
                                            if (result.equals(Constants.SUCCESS)) {
                                                System.out.println("Email has been sent!!!");
                                            } else {
                                                System.out.println("Oops, something went wrong in sending email..");
                                            }
                                            flag = false;
                                        } else {
                                            System.err.println("The category does not exist.. Enter the correct name..");
                                            flag = true;
                                        }
                                    }
                                }
                                else {
                                    System.err.println("There are no categories. add first and then send email.");
                                }
                                break;
                            case 2:
                                System.out.println("Sending email of all tasks in " +
                                        "all categories till created");
                                if (model.showCatagories().size()>0) {
                                    List<EmailBean> list2 = ViewUtilityMethods.menuForEmail();
                                    EmailBean bean2 = null;
                                    for (EmailBean bean1 : list2) {
                                        bean2 = bean1;
                                    }
                                    result = model.sendEmailAllCatagories(bean2);
                                    if (result.equals(Constants.SUCCESS)) {
                                        System.out.println("Email has been sent!!!");
                                    } else {
                                        System.out.println("Oops, something went wrong in sending email..");
                                    }
                                    flag = false;
                                }
                                else {
                                    System.err.println("There are no categories. add first and then send email.");
                                }
                                break;
                            default:
                                System.err.println("Enter the value 1 or 2");
                                flag=true;
                                break;
                        }
                    } else {
                        System.err.println("Enter only numbers");
                        flag=true;
                    }
                break;
            case 7:
                System.out.println("Generating excel file...");
                if (model.showCatagories().size()>0) {
                    System.out.println("The list of categories is, ");
                    System.out.println(model.showCatagories());
                    System.out.println("---------------------------");
                    flag = true;
                    while (flag) {
                        System.out.println("Enter the name of the category");
                        catagory = sc2.nextLine();
                        if (NoteManagerValidations.inputValidation(catagory) &&
                                !ListOfCollections.isCatagoryUnique(catagory)) {
                            result = model.generateExcel(catagory);
                            if (result.equals(Constants.SUCCESS)) {
                                System.out.println("Excel document has been generated..");
                            } else {
                                System.out.println("There is a problem in generating excel..");
                            }
                            flag = false;
                        } else {
                            System.err.println("Enter the correct category name you want to export");
                            flag = true;
                        }
                    }
                }
                else {
                    System.err.println("There are no categories. add first and then generate excel file.");
                }
                break;
            case 8:
                System.out.println("Thank you for visiting us!!!");
                break;
            default:
                System.out.println("Enter the integer value only 1 to 8");
                break;
        }
    }



}
