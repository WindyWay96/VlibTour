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

*/
package vlibtour.vlibtour_tour_management.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Entity
@Table(name = "GROUPS")

public class Group {

	@Id
	@Column(name = "GroupID")
	private int groupID;
	
	@OneToOne(mappedBy = "group")
	private Tour tour;
	
	@ManyToMany(mappedBy="groups")
	//private List<User> users;
	private Collection<User> users = new ArrayList<User>();


	@Column(name = "No0fParticipants")
	private int no0fParticipants;
	
	@Column(name = "GroupStatus")
	private String groupStatus;
	
	/***************
	 * Constructor *
	 ***************/

	public int getGroupID() {
		return groupID;
	}

	public void setGroupID(int groupID) {
		this.groupID = groupID;
	}

	public int getNo0fParticipants() {
		return no0fParticipants;
	}

	public void setNo0fParticipants(int no0fParticipants) {
		this.no0fParticipants = no0fParticipants;
	}

	public String getGroupStatus() {
		return groupStatus;
	}

	public void setGroupStatus(String groupStatus) {
		this.groupStatus = groupStatus;
	}
	
	public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }
    
    public Collection<User> getUsers() {
    	return users;
	}
    
    public void setUsers(final Collection<User> newValue) {
		this.users = newValue;
	}


}
