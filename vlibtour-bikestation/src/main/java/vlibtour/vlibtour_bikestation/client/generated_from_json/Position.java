// CHECKSTYLE:OFF
package vlibtour.vlibtour_bikestation.client.generated_from_json;
import java.util.Map;
import com.fasterxml.jackson.annotation.*;

public class Position {
	 private double lat;
	    private double lng;

	    @JsonProperty("lat")
	    public double getLat() { return lat; }
	    @JsonProperty("lat")
	    public void setLat(double value) { this.lat = value; }

	    @JsonProperty("lng")
	    public double getLng() { return lng; }
	    @JsonProperty("lng")
	    public void setLng(double value) { this.lng = value; }
}
