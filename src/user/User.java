package user;

public class User {
	String userName;
	int userAge;
	String userCountry;
	String userPosition;
	
	public User() {}
	
	public User(String userName, int userAge, String userCountry, String userPosition) {
		super();
		this.userName = userName;
		this.userAge = userAge;
		this.userCountry = userCountry;
		this.userPosition = userPosition;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getUserAge() {
		return userAge;
	}
	public void setUserAge(int userAge) {
		this.userAge = userAge;
	}
	public String getUserCountry() {
		return userCountry;
	}
	public void setUserCountry(String userCountry) {
		this.userCountry = userCountry;
	}
	public String getUserPosition() {
		return userPosition;
	}
	public void setUserPosition(String userPosition) {
		this.userPosition = userPosition;
	}
}
