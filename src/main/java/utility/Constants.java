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
	
	// Arquivo Web de massa de dados
	public static final String FILE_PATH = System.getProperty("user.dir") + "\\data\\";
	public static final String FILE_NAME = "massa.xlsx";
	
	// column(_C) and row(_C)
	// User and password
	public static final Integer USER_R = 1;
	public static final Integer USER_C = 1;
	public static final Integer PASS_R = 1;
	public static final Integer PASS_C = 2;
	
	// Arquivo REST de massa de dados
	public static final String REST_FILE_PATH = System.getProperty("user.dir") + "\\data\\";
	public static final String REST_FILE_NAME = "massaJSON.xlsx";
	
	// Title Column(_TC) and Content Column(_CC)
	// Header and Fields
	public static final Integer HEADER_TC = 1;
	public static final Integer HEADER_CC = 2;
	public static final Integer FIELD_TC = 3;
	public static final Integer FIELD_CC = 4;
	public static final Integer START_CONTENT_LINE = 2;

	// Logger
	public static final Logger logger = LogManager.getLogger(Logger.class);
}
