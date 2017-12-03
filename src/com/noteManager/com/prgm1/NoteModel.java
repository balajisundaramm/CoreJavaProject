package com.noteManager.com.prgm1;//package prgm1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NoteModel
{
	
//	private static com.noteManager.com.prgm1.NoteBean bean;

	public String addNote(NoteBean bean)
	{
		String msg = bean.validate();
		if(msg.equals(Constants.SUCCESS))
		{
			BufferedWriter bw = null;
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/YYYY");
			Date dt = new Date();
			try
			{
				bw = new BufferedWriter(new FileWriter("student.txt",true));
				bw.write(bean.getName()+":" +dt.getTime());
				bw.newLine();
				
				return Constants.SUCCESS;
			
			}
			catch(IOException e)
			{
				e.printStackTrace();
				
			}
			finally
			{
				if(bw!=null)
					try
				{
						bw.close();
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
			}
			
		}
		return msg;
	
	}

public List<NoteBean> getNote()
{
	List<NoteBean> note = new ArrayList<NoteBean>();
	BufferedReader br = null;
	try
	{
		String line;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		br = new BufferedReader(new FileReader("student.txt"));
		while((line = br.readLine())!=null)
		{
			String[] sa = line.split(":");
			NoteBean bean = new NoteBean(sa[0],sa[1],sa[2],sdf.parse(sa[3]),sdf.parse(sa[4]),sa[5],sa[6],sa[7]);
			note.add(bean);
		}
	

	}
	catch(Exception e)
	{
		e.printStackTrace();
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
	return note;
}


}
