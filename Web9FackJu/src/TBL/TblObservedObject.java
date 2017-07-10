package TBL;

import java.util.ArrayList;

public class TblObservedObject {
	int id;
	String name;
	TblLocation location;
	int parent;
	ArrayList<Data> table;
	
	public TblObservedObject(int id, String name, TblLocation location, int parent, ArrayList<Data> table){
		this.id = id;
		this.name = name;
		this.location = location;
		this.parent = parent;
		this.table = table;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TblLocation getLocation() {
		return location;
	}

	public void setLocation(TblLocation location) {
		this.location = location;
	}

	public int getParent() {
		return parent;
	}

	public void setParent(int parent) {
		this.parent = parent;
	}

	public ArrayList<Data> getTable() {
		return table;
	}

	public void setTable(ArrayList<Data> table) {
		this.table = table;
	}
}
