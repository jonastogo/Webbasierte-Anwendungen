package TBL;

public class TblLocation {
	int id;
	String name;
	String street;
	String housenumber;
	String postCode;
	String floor;
	String latitude;
	String longitude;
	String city;

	
	public TblLocation(int id, String name, String street, String housenumber, String postCode, String floor, String latitude, String longitude, String city){
		this.id = id;
		this.name = name;
		this.street = street;
		this.housenumber = housenumber;
		this.postCode = postCode;
		this.floor = floor;
		this.latitude = latitude;
		this.longitude = longitude;
		this.city = city;

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

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getHousenumber() {
		return housenumber;
	}

	public void setHousenumber(String housenumber) {
		this.housenumber = housenumber;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}
	
	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
}
