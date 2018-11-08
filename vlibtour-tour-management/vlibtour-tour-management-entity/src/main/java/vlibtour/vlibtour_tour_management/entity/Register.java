package vlibtour.vlibtour_tour_management.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
//@Table(name = "REGISTERS")

public class Register {
	
	@Id
	@Column(name = "RegisterID")
	private int registerID;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "GroupID")
	private Group group;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UserID")
	private User user;

	/***************
	 * Constructor *
	 ***************/
	
	public int getRegisterID() {
		return registerID;
	}

	public void setRegisterID(int registerID) {
		this.registerID = registerID;
	}

	public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
    
    public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
