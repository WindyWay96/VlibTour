package vlibtour.vlibtour_tour_management.entity;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
//@Table(name = "TOURDATE")

public class TourDate {
	@Id
	@Column(name = "DateID")
	private int dateID;
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "tourDate")
	private Collection<Tour> tours = new ArrayList<Tour>();

	@Column(name = "Tour_Date")
	private Date tourDate;

	@Column(name = "Start_Time")
	private Time startTime;

	@Column(name = "End_Time")
	private Time endTime;

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

	public Collection<Tour> getTours() {
		return tours;
	}

	public void setTours(final Collection<Tour> newValue) {
		this.tours = newValue;
	}

}