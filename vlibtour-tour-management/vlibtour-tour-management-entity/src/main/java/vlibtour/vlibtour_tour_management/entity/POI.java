package vlibtour.vlibtour_tour_management.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The entity bean defining a point of interest (POI). A {@link Tour} is a
 * sequence of points of interest.
 * 
 * @author Denis Conan
 * @author Nguyen Duyen Phuc
 * @author Ngo Hoang Phong
 */

@Entity
//@Table(name="POIS")
//@NamedQueries({
//    @NamedQuery(
//        name = "findPOI",
//        query = "select..."
//        ),
//    @NamedQuery(
//            name = "findPOIsByTourId",
//            query = "from POIS p where u.tour.id = :namePOI"
//            ),
//})

public class POI implements Serializable {
	/**
	 * the serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column
	private int poid;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TourId")
	private Tour tour;
	
	@Column(name = "POI_Name")
	private String namePOI;
	@Column(name = "POI_Description")
	private String descriptionPOI;
	@Column(name = "POI_Address")
	private String addressPOI;
	@Column(name = "Duration")
	private String duration;
	
	/* 
		For simplification, we choose String as data type for "GPSPosition"
		only in this EJB component.The reason is Derby DB not fully 
		support spatial data type. The other components will use gps data type
		provided in library.
	*/
	@Column(name = "GPSPosition")
	private String gpsPosition;

	/***************
	 * Constructor *
	 ***************/


	public int getPoid() {
		return poid;
	}

	

	public void setPoid(int poid) {
		this.poid = poid;
	}

	

	public String getNamePOI() {
		return namePOI;
	}

	
	public void setNamePOI(String namePOI) {
		this.namePOI = namePOI;
	}

	
	public String getDescriptionPOI() {
		return descriptionPOI;
	}

	
	public void setDescriptionPOI(String descriptionPOI) {
		this.descriptionPOI = descriptionPOI;
	}


	public String getAddressPOI() {
		return addressPOI;
	}

	
	public void setAddressPOI(String addressPOI) {
		this.addressPOI = addressPOI;
	}

	
	public Tour getTour() {
		return tour;
	}

	public void setTour(Tour tour) {
		this.tour = tour;
	}

	
	public String getGpsPosition() {
		return gpsPosition;
	}

	public void setGpsPosition(String gpsPosition) {
		this.gpsPosition = gpsPosition;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String toString() {
        return namePOI + " " + descriptionPOI + " " + duration;
    }

}