package utility;

public class Log {	
	// print the beginning of the test case
	public static void startTestCase(String sTestCaseName)
	{
		Constants.logger.error("****************************************************************************************");
		Constants.logger.error("****************************************************************************************");
		Constants.logger.error("$$$$$$$$$$$$$$$$$$$$$                 "+sTestCaseName+ "       $$$$$$$$$$$$$$$$$$$$$$$$$");
		Constants.logger.error("****************************************************************************************");
		Constants.logger.error("****************************************************************************************");
	}
	
	public static void endTestCase(String sTestCaseName)
	{
		Constants.logger.error("XXXXXXXXXXXXXXXXXXXXXXX             "+"-E---N---D-"+"             XXXXXXXXXXXXXXXXXXXXXX");
		Constants.logger.error("X");	 
		Constants.logger.error("X");	 
		Constants.logger.error("X");	 
		Constants.logger.error("X");
	}
}
