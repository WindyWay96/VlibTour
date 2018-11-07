package vlibtour.vlibtour_tour_management.entity;

import java.sql.Date;
import java.sql.Time;


public class TourDate {
	
	private int dateID;
	private Date tourDate;
	private Time startTime;
	private Time endTime;
	private String duration;
	
	
	
	/***************
     * Constructor *
     ***************/
	
	public int getDateID() {
		return dateID;
	}

	public void setDateID(int dateID) {
		this.dateID = dateID;
	}

	public Date getTourDate() {
		return tourDate;
	}

	public void setTourDate(Date tourDate) {
		this.tourDate = tourDate;
	}

	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public Time getEndTime() {
		return endTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}
	

}