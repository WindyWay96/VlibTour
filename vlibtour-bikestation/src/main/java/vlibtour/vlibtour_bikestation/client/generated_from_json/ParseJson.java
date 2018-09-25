package vlibtour.vlibtour_bikestation.client.generated_from_json;
import java.util.Map;
import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.core.JsonProcessingException;

public class ParseJson {
	 public static Station fromJsonString(String json) throws IOException {
	        return getObjectReader().readValue(new File("station.json"));
	    }

	    public static String toJsonString(Station obj) throws JsonProcessingException {
	        return getObjectWriter().writeValueAsString(obj);
	    }

	    private static ObjectReader reader;
	    private static ObjectWriter writer;

	    private static void instantiateMapper() {
	        ObjectMapper mapper = new ObjectMapper();
	        reader = mapper.readerFor(Station.class);
	        writer = mapper.writerFor(Station.class);
	    }

	    private static ObjectReader getObjectReader() {
	        if (reader == null) instantiateMapper();
	        return reader;
	    }

	    private static ObjectWriter getObjectWriter() {
	        if (writer == null) instantiateMapper();
	        return writer;
	    }

}
