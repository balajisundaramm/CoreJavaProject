package com.noteManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Balaji on 27/11/17.
 */
public class NoteManagerValidations {

    public static String validateCatagoryName(String name) {
        final Pattern VALIDATE_NAME =Pattern.compile("^[A-Z ]{2,30}$",Pattern.CASE_INSENSITIVE);
        Matcher matcher = VALIDATE_NAME.matcher(name);
        String msg="Name should contain only letters whose characters shuold be 2 to 30";
        inputValidation(name);
        if(matcher.find()) {
            if (ListOfCollections.isCatagoryUnique(name))
                return "SUCCESS";
            else
                msg="Catagory name is already exist. Enter the new name.";
        }
        return msg;
    }
    public static String validateTaskName(String catagory,String name) {
        final Pattern VALIDATE_NAME =Pattern.compile("^[A-Z ]{2,30}$",Pattern.CASE_INSENSITIVE);
        Matcher matcher = VALIDATE_NAME.matcher(name);
        String msg="Name should contain only letters whose characters shuold be 2 to 30";
        inputValidation(name);
        if(matcher.find()) {
            if (ListOfCollections.isNameUnique(catagory,name)) {
                return "SUCCESS";
            }
            else
                msg="The name is already exist. Enter the new name.";
        }
        return msg;
    }

    public static String validateDescription(String des){
        final Pattern VALIDATE_DES =Pattern.compile("^[A-Z0-9 ,.+!@#$%^<&*>(:;')\"_-\\`|=~]{6,250}$",Pattern.CASE_INSENSITIVE);
        Matcher matcher = VALIDATE_DES.matcher(des);
        String msg="Description cannot be a word should contain words..";
        inputValidation(des);
        if (matcher.find()){
            return "SUCCESS";
        }
        else {
            return msg;
        }
    }

    public static boolean inputValidation(String name){
        if (name==null || name.trim().equals("")){
            throw new IllegalArgumentException("Name neither be null nor blank");
        }
        else {
            return true;
        }
    }

    public static String validateDate(String d){
        final Pattern VALIDATE_DATE =Pattern.compile("^[0-9]{4}+[/]+[0-9]{2}+[/]+[0-9]{2}$");
        Matcher matcher = VALIDATE_DATE.matcher(d);
        Date curDate=new Date();
        String msg="Enter the date in the given format(yyyy/MM/dd)ex:2017/11/16";
        inputValidation(d);
        if (matcher.find()) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            try {
                Date date = sdf.parse(d);
                if (!d.equals(sdf.format(date)) || date.before(curDate))
                    msg = "The date you entered is not valid please enter the valid date";
                else {
                    return "SUCCESS";
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return msg;
    }

    public static String validatePriority(int priority){
        String msg;
        if (priority<0 || priority>10){
            msg="The priority level should be 1 to 10";
            return msg;
        }
        return "SUCCESS";
    }

    public static String validateTag(String tagline){
        String msg;
        final Pattern VALIDATE_NAME =Pattern.compile("^[A-Z0-9, ]{1,250}$",Pattern.CASE_INSENSITIVE);
        Matcher matcher = VALIDATE_NAME.matcher(tagline);
        inputValidation(tagline);
        if (matcher.find()){
            return "SUCCESS";
        }
        else {
            msg="Tags can be a collection of words. It maynot contain special characters";
        }
        return msg;

    }

    public static String validateEmailId(String email){
        String msg="";
        final Pattern VALIDATE_EMAIL=Pattern.compile("^[A-Z0-9._,%+-=]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
                Pattern.CASE_INSENSITIVE);
        Matcher matcher = VALIDATE_EMAIL.matcher(email);
        inputValidation(email);
        if (matcher.find()){
            return "SUCCESS";
        }
        else {
            msg="Email is invalid. Enter the correct email Id EX(abcd123@gmail.com)";
        }
        return msg;

    }

}
