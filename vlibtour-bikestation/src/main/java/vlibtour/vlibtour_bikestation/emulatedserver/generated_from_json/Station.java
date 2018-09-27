package vlibtour.vlibtour_bikestation.emulatedserver.generated_from_json;

<<<<<<< HEAD
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
=======
>>>>>>> origin
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"number",
"name",
"address",
"position",
"banking",
"bonus",
"status",
"contract_name",
"bike_stands",
"available_bike_stands",
"available_bikes",
"last_update"
})
public class Station {

@JsonProperty("number")
<<<<<<< HEAD
private long number;
@JsonProperty("name")
private String name;
@JsonProperty("address")
private String address;
@JsonProperty("position")
private Position position;
@JsonProperty("banking")
private boolean banking;
@JsonProperty("bonus")
private boolean bonus;
@JsonProperty("status")
private String status;
@JsonProperty("contract_name")
private String contractName;
@JsonProperty("bike_stands")
private long bikeStands;
@JsonProperty("available_bike_stands")
private long availableBikeStands;
@JsonProperty("available_bikes")
private long availableBikes;
@JsonProperty("last_update")
private long lastUpdate;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();
=======
public long number;
@JsonProperty("name")
public String name;
@JsonProperty("address")
public String address;
@JsonProperty("position")
public Position position;
@JsonProperty("banking")
public boolean banking;
@JsonProperty("bonus")
public boolean bonus;
@JsonProperty("status")
public String status;
@JsonProperty("contract_name")
public String contractName;
@JsonProperty("bike_stands")
public long bikeStands;
@JsonProperty("available_bike_stands")
public long availableBikeStands;
@JsonProperty("available_bikes")
public long availableBikes;
@JsonProperty("last_update")
public long lastUpdate;
>>>>>>> origin

/**
* No args constructor for use in serialization
* 
*/
public Station() {
}

/**
* 
* @param position
* @param bikeStands
* @param status
* @param address
* @param lastUpdate
* @param name
* @param availableBikeStands
* @param banking
* @param bonus
* @param number
* @param contractName
* @param availableBikes
*/
public Station(long number, String name, String address, Position position, boolean banking, boolean bonus, String status, String contractName, long bikeStands, long availableBikeStands, long availableBikes, long lastUpdate) {
super();
this.number = number;
this.name = name;
this.address = address;
this.position = position;
this.banking = banking;
this.bonus = bonus;
this.status = status;
this.contractName = contractName;
this.bikeStands = bikeStands;
this.availableBikeStands = availableBikeStands;
this.availableBikes = availableBikes;
this.lastUpdate = lastUpdate;
<<<<<<< HEAD
}

@JsonProperty("number")
public long getNumber() {
return number;
}

@JsonProperty("number")
public void setNumber(long number) {
this.number = number;
}

@JsonProperty("name")
public String getName() {
return name;
}

@JsonProperty("name")
public void setName(String name) {
this.name = name;
}

@JsonProperty("address")
public String getAddress() {
return address;
}

@JsonProperty("address")
public void setAddress(String address) {
this.address = address;
}

@JsonProperty("position")
public Position getPosition() {
return position;
}

@JsonProperty("position")
public void setPosition(Position position) {
this.position = position;
}

@JsonProperty("banking")
public boolean isBanking() {
return banking;
}

@JsonProperty("banking")
public void setBanking(boolean banking) {
this.banking = banking;
}

@JsonProperty("bonus")
public boolean isBonus() {
return bonus;
}

@JsonProperty("bonus")
public void setBonus(boolean bonus) {
this.bonus = bonus;
}

@JsonProperty("status")
public String getStatus() {
return status;
}

@JsonProperty("status")
public void setStatus(String status) {
this.status = status;
}

@JsonProperty("contract_name")
public String getContractName() {
return contractName;
}

@JsonProperty("contract_name")
public void setContractName(String contractName) {
this.contractName = contractName;
}

@JsonProperty("bike_stands")
public long getBikeStands() {
return bikeStands;
}

@JsonProperty("bike_stands")
public void setBikeStands(long bikeStands) {
this.bikeStands = bikeStands;
}

@JsonProperty("available_bike_stands")
public long getAvailableBikeStands() {
return availableBikeStands;
}

@JsonProperty("available_bike_stands")
public void setAvailableBikeStands(long availableBikeStands) {
this.availableBikeStands = availableBikeStands;
}

@JsonProperty("available_bikes")
public long getAvailableBikes() {
return availableBikes;
}

@JsonProperty("available_bikes")
public void setAvailableBikes(long availableBikes) {
this.availableBikes = availableBikes;
}

@JsonProperty("last_update")
public long getLastUpdate() {
return lastUpdate;
}

@JsonProperty("last_update")
public void setLastUpdate(long lastUpdate) {
this.lastUpdate = lastUpdate;
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
return new ToStringBuilder(this).append("number", number).append("name", name).append("address", address).append("position", position).append("banking", banking).append("bonus", bonus).append("status", status).append("contractName", contractName).append("bikeStands", bikeStands).append("availableBikeStands", availableBikeStands).append("availableBikes", availableBikes).append("lastUpdate", lastUpdate).append("additionalProperties", additionalProperties).toString();
}

}
=======
}

@Override
public String toString() {
return new ToStringBuilder(this).append("number", number).append("name", name).append("address", address).append("position", position).append("banking", banking).append("bonus", bonus).append("status", status).append("contractName", contractName).append("bikeStands", bikeStands).append("availableBikeStands", availableBikeStands).append("availableBikes", availableBikes).append("lastUpdate", lastUpdate).toString();
}

}
	
	

>>>>>>> origin
