package rahulshettyacademy.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import com.fasterxml.jackson.core.type.TypeReference;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {
	public ArrayList<HashMap<String, String>> getJsonDataToMap() throws IOException
	{
		//Read Json to String
		String jsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir")+"//src//test//java//rahulshettyacademy//data//PurchaseOrder.json"),StandardCharsets.UTF_8);
		
		//String to HashMap >jackson databind
		ObjectMapper mapper= new ObjectMapper();
		ArrayList<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<ArrayList<HashMap<String,String>>>(){

		});
		return data;
	}

}
