package properties;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Config {
	
	static Properties config;
	
	// open file
	public static void loadConfig() throws IOException
	{
		config = new Properties();
		File f = new File(System.getProperty("user.dir") + "/src/main/java/properties/config.properties");
		FileReader obj = new FileReader(f);
		config.load(obj);
	}
	
	// load data from key value of config file
	public static String readConfig(String key) throws IOException
	{
		loadConfig();
		return config.getProperty(key);
	}
}
