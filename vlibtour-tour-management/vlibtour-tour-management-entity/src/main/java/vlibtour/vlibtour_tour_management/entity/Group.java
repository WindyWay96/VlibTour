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

public class Group {

	private int groupID;
	private int no0fParticipants;

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
	
}
