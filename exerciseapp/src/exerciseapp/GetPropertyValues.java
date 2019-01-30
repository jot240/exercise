package exerciseapp;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
// method to get configuration settings
public class GetPropertyValues {
	String value = "";
	InputStream inputStream;
 public String getPropValues(String key) throws IOException {
	 
	 try {
		 Properties prop = new Properties();
		 String propFileName = "config.properties";
		 inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
		 
		 if (inputStream != null) {
			 prop.load(inputStream);
		 } else {
			 throw new FileNotFoundException("property file '" + propFileName + "' not found");
			 
		 }
		 value = prop.getProperty(key);
	 }
	 catch(Exception e) {
		 System.out.println("exception: " + e);
	 }
	 finally {
		 inputStream.close();
	 }
	 return value;
 }
}
