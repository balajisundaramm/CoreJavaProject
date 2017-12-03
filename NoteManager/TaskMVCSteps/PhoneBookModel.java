package com.uttara.phoneBook;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PhoneBookModel {

	/*
	 * will open the file for the phone book given, read
	 * one line at a time, split it, inject it to a bean objj,
	 * add the bean to a List and finally return it.
	 */
	public List<ContactBean> listContacts(String phBook)
	{
		List<ContactBean> contacts = new ArrayList<ContactBean>();
		
		BufferedReader br = null;
		try
		{
			br = new BufferedReader(new FileReader(phBook+".pb"));
			String line;
			while((line = br.readLine())!=null)
			{
				String[] sa = line.split(":");
				ContactBean bean = new ContactBean(sa[0],sa[1]);
				contacts.add(bean);
			}
			return contacts;
		}
		catch(IOException e)
		{
			e.printStackTrace();
			// we should throw a custom business exception here...for now I am returning null!
			return null;
		}
		finally
		{
			if(br!=null)
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
	
	}
	/*
	 * This method will open the file if it exists or will create a new one
	 * with the phBook name given and will write a line with the 
	 * format name:phnum using the beans data
	 */
	public String addContact(ContactBean cb,String phBook)
	{
		System.out.println("model->addContact() "+cb+" phB "+phBook);
		// first perform business validations
		// only if successful, apply business logic
		// only if B.L succeeds, return "success"
		// else return error msg to be shown to user!
		
		String line = cb.getName()+":"+cb.getPhoneNum();
		System.out.println("model->addContact()->line = "+line);
		BufferedWriter bw = null;
		try
		{
			bw = new BufferedWriter(new FileWriter(phBook+".pb",true));
			bw.write(line);
			bw.newLine();
			return Constants.SUCCESS;
		}
		catch(IOException e)
		{
			e.printStackTrace();
			return "Bad thing happened! "+e.getMessage();
			
		}
		finally
		{
			if(bw!=null)
				try {
					bw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	
		//System.out.println("returning success");
		
	}
	
}




