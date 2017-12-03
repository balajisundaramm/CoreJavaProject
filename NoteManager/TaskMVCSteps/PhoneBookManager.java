package com.uttara.phoneBook;

import java.util.List;
import java.util.Scanner;

public class PhoneBookManager {

	/*
	 * This method will validate the string given
	 * to check if it contains multiple words or
	 * spl chars. If yes, it returns an error msg
	 * else it returns constant string success!
	 */
	public static String validateName(String n)
	{
		if(n==null || n.trim().equals(""))
			throw new IllegalArgumentException("name cannot be null");
		
		String[] sa = n.split(" ");
		if(sa.length > 1)
			return "Name cannot contain multiple words!Enter a single worded string as input!";
		
		for(int i = 0 ; i < n.length() ; i++)
		{
			char c = n.charAt(i);
			
			if (!(Character.isDigit(c) || Character.isLetter(c)))
				return "Name should not contain special chars!";
		}
		return Constants.SUCCESS;
	}
	/*
	 * This method forms the View of the application
	 * this method will display the menu, accept the
	 * inputs, display success/error msgs to the user
	 * and invoke methods of model!
	 */
	public static void main(String[] args) {
		Scanner sc1 = new Scanner(System.in);
		Scanner sc2 = new Scanner(System.in);
		int ch1=0;
		String phBook,name,phone;
		PhoneBookModel model = new PhoneBookModel();
		while(ch1!=5)
		{
			System.out.println("");
			System.out.println("Press 1 to create Phone Book");
			System.out.println("Press 2 to load Phone Book");
			System.out.println("Press 3 to search Phone Books");
			System.out.println("Press 4 to list Phone Books");
			System.out.println("Press 5 to exit");
			System.out.println("Enter choice");
			System.out.println("");
			
			//until user gives correct int, keep showing error msg and ignore token read
			while(!sc1.hasNextInt())
			{
				System.out.println("Enter only int inputs boss...asthu gotilla!!");
				sc1.next();
			}
			//read the valid token int input from scanner
			ch1 = sc1.nextInt();
			System.out.println("choice = "+ch1);
			
			switch(ch1)
			{
				case 1:	System.out.println("creating phone book...");
						System.out.println("Enter name of phone book");
						phBook = sc2.nextLine();
						//input validations!
						String result = PhoneBookManager.validateName(phBook);
						//until the input validations succeed, keep asking the user to give new input
						//and show error msg
						while(!result.equals(Constants.SUCCESS))
						{
							System.out.println("Enter proper name which single word, no spl char and starts with letter...");
							phBook = sc2.nextLine();
							result = PhoneBookManager.validateName(phBook);
						}
						
						int ch2 = 0;
						while(ch2!=5)
						{
							System.out.println("");
							System.out.println("Press 1 to add contact");
							System.out.println("Press 2 to list contacts");
							System.out.println("Press 3 to edit contact");
							System.out.println("Press 4 to remove contact");
							System.out.println("Press 5 to go back");
							System.out.println("");
							while(!sc1.hasNextInt())
							{
								System.out.println("Enter only int inputs boss...asthu gotilla!!");
								sc1.next();
							}
							ch2 = sc1.nextInt();
							System.out.println("choice = "+ch2);		
							
							switch(ch2)
							{
							case 1:	System.out.println("");
									System.out.println("adding contact...");
							
									System.out.println("Enter name of contact");
									name = sc2.nextLine();
									System.out.println("Enter phone num of contact "+name );
									phone = sc2.nextLine();
									//create bean obj to hold user inputs
									ContactBean bean = new ContactBean(name, phone);
									System.out.println("main()->invoking models addContact()");
									//invoke method of model to add the contact to file
									result = model.addContact(bean, phBook);
									System.out.println("main()-> result from addContact() "+result);
									//if model method returned Constants.SUCCESS, it means that 
									//addition was successful,else it is returning error msg to display
									//to user
									if(result.equals(Constants.SUCCESS))
									{
										System.out.println("Contact "+name+" has been added successfully to phone book "+phBook );
									}
									else
									{
										System.out.println("oops some problem in adding..."+result);
									}
							
									break;
									
							case 2:
									System.out.println("");
									//invoke method of model to get the collection of beans from file
									List<ContactBean> contacts = model.listContacts(phBook);
									//if null is returned, it means there was a problem
									if(contacts==null)
									{
										System.out.println("Oops some problem during listing! Contact Admin! (look at console stacktrace)");
									}
									else
									{
										//loop over the list and invoke getter methods on bean to display to user
										for(ContactBean cb : contacts)
										{
											System.out.println("Name : "+cb.getName()+" Phone : "+cb.getPhoneNum());
										}
									}
									break;
							default:
									System.out.println("Yet to be implemented! Wait maadi!!");
									break;
							}
						}
						
						break;
				case 2:	System.out.println("loading phone book...");
				break;
				case 3:	System.out.println("searching phone book...");
				break;
				case 4:	System.out.println("listing phone book...");
				break;
				case 5:	System.out.println("exiting...bye bye...come back soon...");
				break;
				default:	System.out.println("give only 1-5 input boss!");
				break;
				
			}
			
		}
		
	}

}





