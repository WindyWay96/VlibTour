package vlibtour.vlibtour_bikestation.emulatedserver.generated_from_json;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"lat",
"lng"
})
public class Position {

@JsonProperty("lat")
private double lat;
@JsonProperty("lng")
private double lng;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

/**
* No args constructor for use in serialization
* 
*/
public Position() {
}

/**
* 
* @param lng
* @param lat
*/
public Position(double lat, double lng) {
super();
this.lat = lat;
this.lng = lng;
}

@JsonProperty("lat")
public double getLat() {
return lat;
}

@JsonProperty("lat")
public void setLat(double lat) {
this.lat = lat;
}

@JsonProperty("lng")
public double getLng() {
return lng;
}

@JsonProperty("lng")
public void setLng(double lng) {
this.lng = lng;
}

@JsonAnyGetter
public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

@JsonAnySetter
public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

@Override
public String toString() {
return new ToStringBuilder(this).append("lat", lat).append("lng", lng).append("additionalProperties", additionalProperties).toString();
}

}