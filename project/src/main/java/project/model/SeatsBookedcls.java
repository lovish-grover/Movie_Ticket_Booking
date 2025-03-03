package project.model;

import java.sql.Date;

public class SeatsBookedcls {
	String slot;
	Date DOS;
	
	
	
	public SeatsBookedcls() {
		super();
	}



	public SeatsBookedcls( Date DOS,String slot) {
		super();
		
		this.DOS = DOS;
		this.slot = slot;
	}

	public Date getDOS() {
		return DOS;
	}
	public void setDOS(Date DOS) {
		this.DOS = DOS;
	}
	public String getSlot() {
		return slot;
	}
	public void setSlot(String slot) {
		this.slot = slot;
	}

	

}
