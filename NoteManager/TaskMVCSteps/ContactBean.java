package com.uttara.phoneBook;

/*
 * This is a data holder class. This class has instance variables
 * with setter/getters and a convenience constructor to set the state.
 * Since this class has state, we override equals(), hashCode(), toString()
 * so that objects of this class behave correctly if put into collections!
 * 
 */
public class ContactBean {

	private String name;
	private String phoneNum;
	
	public ContactBean() {
		// TODO Auto-generated constructor stub
	}
	
	public ContactBean(String name, String phoneNum) {
		super();
		this.name = name;
		this.phoneNum = phoneNum;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((phoneNum == null) ? 0 : phoneNum.hashCode());
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
		ContactBean other = (ContactBean) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (phoneNum == null) {
			if (other.phoneNum != null)
				return false;
		} else if (!phoneNum.equals(other.phoneNum))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ContactBean [name=" + name + ", phoneNum=" + phoneNum + "]";
	}
	
	
}
