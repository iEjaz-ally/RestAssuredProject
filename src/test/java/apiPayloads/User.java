package apiPayloads;

public class User {
	int id;
	String userNameString;
	String firstNameString;
	String lastNameString;
	String emailString;
	String passwoString;
	String phoneNumString;
	int userStatus=0;
	public String getUserNameString() {
		return userNameString;
	}
	public void setUserNameString(String userNameString) {
		this.userNameString = userNameString;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstNameString() {
		return firstNameString;
	}
	public void setFirstNameString(String firstNameString) {
		this.firstNameString = firstNameString;
	}
	public String getLastNameString() {
		return lastNameString;
	}
	public void setLastNameString(String lastNameString) {
		this.lastNameString = lastNameString;
	}
	public String getEmailString() {
		return emailString;
	}
	public void setEmailString(String emailString) {
		this.emailString = emailString;
	}
	public String getPasswoString() {
		return passwoString;
	}
	public void setPasswoString(String passwoString) {
		this.passwoString = passwoString;
	}
	public String getPhoneNumString() {
		return phoneNumString;
	}
	public void setPhoneNumString(String phoneNumString) {
		this.phoneNumString = phoneNumString;
	}
	public int getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(int userStatus) {
		this.userStatus = userStatus;
	}

}
