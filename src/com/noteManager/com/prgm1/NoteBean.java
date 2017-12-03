package com.noteManager.com.prgm1;//package prgm1;

import java.sql.Date;

public class NoteBean {
	
	String name;
	String desc;
	Date StDate;
	Date EndDate;
	String status;
	String priority;
	String tags;
	public NoteBean(String sa, String sa2, String sa3, java.util.Date date, java.util.Date date2, String sa4, String sa5, String sa6)
	{
		
	}

	
	@Override
	public String toString() {
		return "com.noteManager.com.prgm1.NoteBean [name=" + name + ", desc=" + desc + ", StDate=" + StDate + ", EndDate=" + EndDate + ", status="
				+ status + ", priority=" + priority + ", tags=" + tags + "]";
	}


	public NoteBean(String name, String desc, Date stDate, Date endDate, String status, String priority, String tags) {
		super();
		this.name = name;
		this.desc = desc;
		StDate = stDate;
		EndDate = endDate;
		this.status = status;
		this.priority = priority;
		this.tags = tags;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((EndDate == null) ? 0 : EndDate.hashCode());
		result = prime * result + ((StDate == null) ? 0 : StDate.hashCode());
		result = prime * result + ((desc == null) ? 0 : desc.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((priority == null) ? 0 : priority.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((tags == null) ? 0 : tags.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NoteBean other = (NoteBean) obj;
		if (EndDate == null) {
			if (other.EndDate != null)
				return false;
		} else if (!EndDate.equals(other.EndDate))
			return false;
		if (StDate == null) {
			if (other.StDate != null)
				return false;
		} else if (!StDate.equals(other.StDate))
			return false;
		if (desc == null) {
			if (other.desc != null)
				return false;
		} else if (!desc.equals(other.desc))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (priority == null) {
			if (other.priority != null)
				return false;
		} else if (!priority.equals(other.priority))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (tags == null) {
			if (other.tags != null)
				return false;
		} else if (!tags.equals(other.tags))
			return false;
		return true;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDesc() {
		return desc;
	}


	public void setDesc(String desc) {
		this.desc = desc;
	}


	public Date getStDate() {
		return StDate;
	}


	public void setStDate(Date stDate) {
		StDate = stDate;
	}


	public Date getEndDate() {
		return EndDate;
	}


	public void setEndDate(Date endDate) {
		EndDate = endDate;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}

	public String getPriority() {
		return priority;
	}


	public void setPriority(String priority) {
		this.priority = priority;
	}


	public String getTags() {
		return tags;
	}


	public void setTags(String tags) {
		this.tags = tags;
	}


	public String validate()
	{
		String msg = " ";
		if(name == null || name.trim().equals(""))
			msg = "Name doesn't match";
		else
			if(name.length() > 30)
			msg = msg+"Name contains many characters";
		
		if(desc == null || desc.trim().equals(""))
			msg = "Not proper description";
		else
			if(!desc.contains("1"))
				msg = msg+ " description does not contain numbers";
		
		if(StDate == null || EndDate == null)
			msg = "Not a valid date format";
		if(StDate.equals(EndDate))
		{
			msg = msg+"Valid date format";
		}
		
		if(EndDate.after(StDate))
		{
			msg = msg+"correct date";
		}
			 
		if(msg.equals(""))
			return Constants.SUCCESS;
		else
			return msg;
	}

}
