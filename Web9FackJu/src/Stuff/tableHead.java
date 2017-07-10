package Stuff;

import java.util.ArrayList;

public class tableHead {
	ArrayList<String> colNames;
	int id;
	
	public tableHead(ArrayList<String> colNames, int id){
		this.colNames = colNames;
		this.id = id;
	}

	public ArrayList<String> getColNames() {
		return colNames;
	}

	public void setColNames(ArrayList<String> colNames) {
		this.colNames = colNames;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
