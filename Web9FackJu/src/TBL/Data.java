package TBL;

import java.util.ArrayList;

public class Data {
	int id; 
	String timestamp;
	ArrayList<String> data;
	
	public Data( ArrayList<String> data){
		this.data = data;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public ArrayList<String> getData() {
		return data;
	}

	public void setData(ArrayList<String> data) {
		this.data = data;
	}
	
}
