package vlibtour.vlibtour_tour_management.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
//@Table(name="USERS")
//@NamedQueries({
//    @NamedQuery(
//        name = "findUser",
//        query = "select userID, user_Name from Users"
//        ),
//})

public class User implements Serializable {
	/**
	 * the serial number.
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "UserID")
	private int userID;
	
	@ManyToMany
	@JoinTable(
	   name="REGISTERS",
	   joinColumns=@JoinColumn(name="UserID"),
	   inverseJoinColumns=@JoinColumn(name="GroupID"))
	private Collection<Group> groups = new ArrayList<Group>();
	
	@Column(name = "User_Name")
	private String userName;
	
	@Column(name = "User_Phone")
	private String phone;
	
	@Column(name = "User_Address")
	private String userAddress;
	
	@Column(name = "User_GPSLocation")
	private String user_GPSLocation;

	/***************
	 * Constructor *
	 ***************/

	public int getUserID() {
		return userID;
	}

	
	public void setUserID(int userID) {
		this.userID = userID;
	}

	
	public String getUserName() {
		return userName;
	}

	
	public void setUserName(String userName) {
		this.userName = userName;
	}

	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	
	public String getUserAddress() {
		return userAddress;
	}

	
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	
	public Collection<Group> getGroups() {
		return groups;
	}

	public void setGroups(final Collection<Group> newValue) {
		this.groups = newValue;
	}
	
	
	public String getUser_GPSLocation() {
		return user_GPSLocation;
	}

	public void setUser_GPSLocation(String user_GPSLocation) {
		this.user_GPSLocation = user_GPSLocation;
	}

}