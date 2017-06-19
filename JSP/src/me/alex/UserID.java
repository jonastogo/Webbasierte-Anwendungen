package me.alex;
import java.io.Serializable;

public class UserID implements Serializable{

	
	private String id ;
	
	
	public UserID(){
		this("Hallo");
	}
	
	public UserID(String id){
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id =  id;
	}




}
