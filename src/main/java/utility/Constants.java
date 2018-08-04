package utility;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Constants {
	// PROJECT INFO
	public static final String PROJECT_ED = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
	
	// System URL or EndPoint and Path parameters
	public static final String URL = "http://restapi.demoqa.com/customer/";
	public static final String URL_PATH = "register";
	
	// Arquivo de massa de dados
	public static final String PATH = System.getProperty("user.dir") + "\\data\\";
	public static final String FILE = "massa.xlsx";
	public static final Integer USER_R = 1;
	public static final Integer USER_C = 1;
	public static final Integer PASS_R = 1;
	public static final Integer PASS_C = 2;
	
	// Logger
	public static final Logger logger = LogManager.getLogger(Logger.class);
}
