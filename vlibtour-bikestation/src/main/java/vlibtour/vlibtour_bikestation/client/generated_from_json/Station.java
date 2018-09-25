// CHECKSTYLE:OFF
package vlibtour.vlibtour_bikestation.client.generated_from_json;
import java.util.Map;
import java.io.IOException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.core.JsonProcessingException;

public class Station {
	
	    private long number;
	    private String contractName;
	    private String name;
	    private String address;
	    private Position position;
	    private boolean banking;
	    private boolean bonus;
	    private long bikeStands;
	    private long availableBikeStands;
	    private long availableBikes;
	    private String status;
	    private long lastUpdate;

	    @JsonProperty("number")
	    public long getNumber() { return number; }
	    @JsonProperty("number")
	    public void setNumber(long value) { this.number = value; }

	    @JsonProperty("contract_name")
	    public String getContractName() { return contractName; }
	    @JsonProperty("contract_name")
	    public void setContractName(String value) { this.contractName = value; }

	    @JsonProperty("name")
	    public String getName() { return name; }
	    @JsonProperty("name")
	    public void setName(String value) { this.name = value; }

	    @JsonProperty("address")
	    public String getAddress() { return address; }
	    @JsonProperty("address")
	    public void setAddress(String value) { this.address = value; }

	    @JsonProperty("position")
	    public Position getPosition() { return position; }
	    @JsonProperty("position")
	    public void setPosition(Position value) { this.position = value; }

	    @JsonProperty("banking")
	    public boolean getBanking() { return banking; }
	    @JsonProperty("banking")
	    public void setBanking(boolean value) { this.banking = value; }

	    @JsonProperty("bonus")
	    public boolean getBonus() { return bonus; }
	    @JsonProperty("bonus")
	    public void setBonus(boolean value) { this.bonus = value; }

	    @JsonProperty("bike_stands")
	    public long getBikeStands() { return bikeStands; }
	    @JsonProperty("bike_stands")
	    public void setBikeStands(long value) { this.bikeStands = value; }

	    @JsonProperty("available_bike_stands")
	    public long getAvailableBikeStands() { return availableBikeStands; }
	    @JsonProperty("available_bike_stands")
	    public void setAvailableBikeStands(long value) { this.availableBikeStands = value; }

	    @JsonProperty("available_bikes")
	    public long getAvailableBikes() { return availableBikes; }
	    @JsonProperty("available_bikes")
	    public void setAvailableBikes(long value) { this.availableBikes = value; }

	    @JsonProperty("status")
	    public String getStatus() { return status; }
	    @JsonProperty("status")
	    public void setStatus(String value) { this.status = value; }

	    @JsonProperty("last_update")
	    public long getLastUpdate() { return lastUpdate; }
	    @JsonProperty("last_update")
	    public void setLastUpdate(long value) { this.lastUpdate = value; }
	}
	
	

