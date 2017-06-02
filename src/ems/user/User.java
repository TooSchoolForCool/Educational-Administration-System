package ems.user;

public class User {
	private String Password;
	private int Identity;
	
	public final static int IDEN_STUDENT = 1;
	public final static int IDEN_TEACHER = 2;
	public final static int IDEN_MANAGER = 4;
	
	public User(){
		
	}
	public User(String Password,int Identity){
		this.Password = Password;
		this.Identity = Identity;
	}
	
	public void setPassword(String Password){
		this.Password = Password;
	}
	public String getPassword(){
		return Password;
	}
	public void setIdentity(int Identity){
		this.Identity = Identity;
	}
	public int getIdentity(){
		return Identity;
	}
	
	
}
