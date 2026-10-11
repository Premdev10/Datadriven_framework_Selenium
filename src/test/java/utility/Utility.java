package utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Utility {
	
	public Object readpropertyvalues(String key) throws IOException {
		FileInputStream fis= new FileInputStream("./src/main/resources/Config/config.properties");
		Properties prop= new Properties();
		prop.load(fis);
		return prop.get(key);
		
	}

}
