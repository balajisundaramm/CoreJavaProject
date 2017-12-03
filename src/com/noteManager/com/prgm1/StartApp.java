package com.noteManager.com.prgm1;//package prgm1;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

public class StartApp {
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc1 = new Scanner(System.in);
		Scanner sc2 = new Scanner(System.in);
		String name;
		String desc;
		Date StDate;
		Date EndDate;
		String status;
		String priority;
		String tags;
		NoteBean bean;
		NoteModel model = new NoteModel();
		String msg;
		int ch;
		try
		{
			SimpleDateFormat parser = new SimpleDateFormat("dd/MM/YYYY");
			ch=0;
			while(ch!=5)
			{
				System.out.println("");
				System.out.println("Press 1 to create note");
				System.out.println("Press 2 to edit the note");
				System.out.println("Press 3 to Delete the note");
				System.out.println("Press 4 to List the note");
				System.out.println("Press 5 to Exit");
				System.out.println("Enter your choice");
				ch = sc1.nextInt();
				switch(ch)
				{
				case 2 : 
					List<NoteBean> note = model.getNote();
					if(note == null)
						System.out.println("Error");
					else
						for(NoteBean nb : note)
							System.out.println("Name : "+nb.getName()+"Desc :" +nb.getDesc()+" StartDate :"+parser.format(nb.getStDate())+"End date :"+parser.format(nb.getEndDate())+"status :"+nb.getStatus()+"Priority :"+nb.getPriority()+"tags :"+nb.getTags());
					        break;
					        
				case 1 : 
					System.out.println("Enter the name of the category");
					name = sc2.nextLine();
					while(NoteUtil.validName(name) == false)
					{
						System.out.println("enter the name");
						name = sc2.nextLine();
					}
				
					System.out.println("Enter the category description");
					String text="";
					desc="";
					if(desc.contains("[0-9]+")==false && desc.length()>10)
						desc=text;
						
					
					System.out.println("Load the category");
					
					System.out.println("Update the category");
					
					System.out.println("Delete the category");
					
					
					System.out.println("Enter the start date");
					 String dt = sc2.nextLine();
					  StDate = (Date)parser.parse(dt);
					/*EndDate=(Date)parser.parse(dt);
					status="process";
					priority="5";
					tags="hi";
					   bean = new com.noteManager.com.prgm1.NoteBean(name,desc,StDate,EndDate,status,priority,tags);
					   msg = model.addNote(bean);
					   if(msg.equals(com.noteManager.com.prgm1.Constants.SUCCESS))
					   {
						   System.out.println("Note "+name+"category successfully added");
						   
					   }
					   else {
						   System.out.println("Note could not yet added successfully msg = " + msg);
						   break;
					   }*/
					       
					 	System.out.println("Enter the End Date");
					  	String dt1 = sc2.nextLine();
					   	EndDate = (Date)parser.parse(dt1);

					       
					       System.out.println("Enter the priority");
					       priority = sc2.nextLine();
					       
					       System.out.println("Enter the status");
					       status = sc2.nextLine();
					       
					       System.out.println("Enter the tags");
					       tags = sc2.nextLine();

					bean = new NoteBean(name, desc, StDate, EndDate,status,priority,tags);
					msg = model.addNote(bean);
					if(msg.equals(Constants.SUCCESS))
					{
						System.out.println("Note "+name+"category successfully added");

					}
					else
						System.out.println("Note could not yet added successfully msg = "+msg);
					break;
				}
			}
			
		
			
		}
	catch(Exception e)
		{
		e.printStackTrace();
		}
		

	}

}
