package project.model;

import java.sql.Date;

public class DateSlotCls {
	Date DOS;
	String slot;
	
	
	public DateSlotCls() {
		super();
	}
	
	public DateSlotCls(String slot,Date DOS) {
		super();
		
		this.slot = slot;
		this.DOS = DOS;
	}
	
	public Date getDOS() {
		return DOS;
	}
	public void setDOS(Date dOS) {
		this.DOS = dOS;
	}
	public String getSlot() {
		return slot;
	}
	public void setSlot(String slot) {
		this.slot = slot;
	}
}
