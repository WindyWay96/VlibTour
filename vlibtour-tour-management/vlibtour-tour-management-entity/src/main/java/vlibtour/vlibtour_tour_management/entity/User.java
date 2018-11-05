/**
This file is part of the course CSC5002.

Copyright (C) 2017 Télécom SudParis

This is free software: you can redistribute it and/or modify
it under the terms of the GNU Lesser General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This software platform is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public License
along with the muDEBS platform. If not, see <http://www.gnu.org/licenses/>.

Initial developer(s): Denis Conan
Contributor(s): Nguyen Duyen Phuc 	<duyen.nguyen@telecom-sudparis.eu/>
				Ngo Hoang Phong

*/
package vlibtour.vlibtour_tour_management.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name="USERS")
@NamedQueries({
    @NamedQuery(
        name = "findUserById",
        query = "from USERS u where u.userID = :User_Name"
        ),
})

public class User implements Serializable {
	/**
	 * the serial number.
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private int userID;
	@Column(name = "User_Name") private String userName;
	@Column(name = "Phone") private String phone;
	@Column(name = "User_Address") private String userAddress;

	/***************
     * Constructor *
     ***************/

	/**
	 * gets id of the user.
	 * 
	 * @return id of the user.
	 */	
	public int getUserID() {
		return userID;
	}
	
	/**
	 * sets the identifier.
	 * 
	 * @param userID
	 *            the user identifier.
	 */
	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	/**
	 * gets name of the user.
	 * 
	 * @return name of the user.
	 */
	public String getUserName() {
		return userName;
	}
	
	/**
	 * sets the name of user.
	 * 
	 * @param name
	 *            the new name.
	 */	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	/**
	 * gets phone of the user.
	 * 
	 * @return phone of the user.
	 */
	public String getPhone() {
		return phone;
	}
	
	/**
	 * sets the phone of user.
	 * 
	 * @param phone
	 *            the new phone.
	 */	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	/**
	 * gets address of the user.
	 * 
	 * @return address of the user.
	 */
	public String getUserAddress() {
		return userAddress;
	}
	
	/**
	 * sets the user address.
	 * 
	 * @param address
	 *            the new address.
	 */
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	

}
