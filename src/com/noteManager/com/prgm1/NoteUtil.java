package com.noteManager.com.prgm1;//package prgm1;

public class NoteUtil {
	public static boolean validName(String name)
	{
		if(name==null || name.trim().equals(""))
			return false;
		if(!Character.isLetter(name.charAt(0)))
			return false;
		
		if(name.split(" ").length > 1)
			return false;
		
		for(int i = 1 ; i < name.length() ; i++)
		{
			char c = name.charAt(i);
			if(!(Character.isDigit(c) || Character.isLetter(c)))
				return false;
		}
		return true;
		
	}


}
