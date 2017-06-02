package ems.user;

public class Student extends User{
	
	private String stuID;
	
	private String stuName;
	private String stuSex;
	private String stuClass;
	private String stuAddress;
	private String Memo;
	private String stuBirthday;
	private String stuPhone;
	private String stuInDate;
	
	public Student(){
		
	}
	
	
	public void setstuID(String stuID){
		this.stuID = stuID;
	}
	public String getstuID(){
		return stuID;
	}
	public void setstuName(String stuName){
		this.stuName = stuName;
	}
	public String getstuName(){
		return stuName;
	}
	public void setstuSex(String stuSex){
		this.stuSex = stuSex;
	}
	public String getstuSex(){
		return stuSex;
	}
	public void setstuClass(String stuClass){
		this.stuClass = stuClass;
	}
	public String getstuClass(){
		return stuClass;
	}
	public void setstuAddress(String stuAddress){
		this.stuAddress = stuAddress;
	}
	public String getstuAddress(){
		return stuAddress;
	}
	public void setMemo(String Memo){
		this.Memo = Memo;
	}
	public String getMemo(){
		return Memo;
	}
	public void setstuBirthday(String stuBirthday){
		this.stuBirthday = stuBirthday;
	}
	public String getstuBirthday(){
		return stuBirthday;
	}
	public void setstuPhone(String stuPhone){
		this.stuPhone = stuPhone;
	}
	public String getstuPhone(){
		return stuPhone;
	}
	public void setstuInDate(String stuInDate){
		this.stuInDate = stuInDate;
	}
	public String getstuInDate(){
		return stuInDate;
	}
	/**
	 * private String stuID;
	
	private String stuName;
	private String stuSex;
	private String stuClass;
	private String stuAddress;
	private String Memo;
	private String stuBirthday;
	private String stuPhone;
	private String stuInDate;
	 */
	
	
	@Override
	public String toString() {
		return new String(
				getIdentity()+" "
				+getstuID()+" "
				+getPassword()+" "
				+getstuName()+" "
				+getstuSex()+" "
				+getstuClass()+" "
				+getstuAddress()+" "
				+getMemo()+" "
				+getstuBirthday()+" "
				+getstuPhone()+" "
				+getstuInDate()+" "
				);
	}
	
	
}
