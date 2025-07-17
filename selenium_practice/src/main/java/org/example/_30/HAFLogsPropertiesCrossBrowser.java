public class HAFLogsPropertiesCrossBrowser{
/**
         * 2) Adding logs to Test Cases (log4j2) : recording all events in the form of text
                 * steps :
                         * 1) add log4j2.xml in src/test/resources
                         * 2) update base class
                         * 3) add log statements to AccountRegistrationTest
                 * types of logs : application logs and system logs
                 * we also maintain the log files especially for debugging
                 * log levels : (increasing order of priority)
                         * ALL
                         * 1) Trace : all logs
                         * 2) Debug : all debug and higher levels
                         * 3) Info : all info and higher levels
                         * 4) Warn : all warn and higher levels
                         * 5) Error : all error and higher levels
                         * 6) Fetal : all fetal and higher levels
                         * OFF
                 * Appenders and Loggers :
                         * Appenders : where to generate the logs (console/file) : file is preferred
                         * Loggers : what type of logs generate (levels : ALL < Trace < Debug < Info < Warn < Error < Fetal < OFF)
                         * in the log4j2.xml file we specify the appenders and loggers
                 * in the xml file :
                         * in the property we specify the base path + logs folder to place the log files
                         * in rolling file we specify the name of file along with time stamp, so log files will be creating with time stamp in their name
                         * in appenders ref we specify where we like to put our data in either console or file
                         * in root we can specify any of the above logging levels
                         * if file size becomes more than specified it will create a new file with the over flowing logs
                 * in BaseClass we need to update to load the xml file :
                         * create Logger object () -> import org.apache.logging.log4j.Logger;
                         * instantiate the logger object using LoggerManager -> logger= LogManager.getLogger(this.getClass()); import org.apache.logging.log4j.LogManager;
                         * now use this logger object to call its methods and log specific levels and messages
         * 3) Run tests on desired browser/cross browser/parallel : xml files should be created at the project level
                 * steps :
                         * 1) Create testng.xml file to Run Test Cases and parameterize browser name and OS to BaseClass →setup() method.
                         * 2) Update BaseClass -setup() method, launch browser based on conditions.
                         * 3) Maintain separate xml to run tests multiple browsers parallelly.
                 * pass the parameter in BaseClass using xml file and using switch we can dynamically choose which browser to open
                 * os is changed using grid
                 * to run multiple test cases on different browsers we can make multiple tests in xml file and provide different parameters there along thread count at suite level along with parallel="tests"
         * 4) Read Common values from config.properties file.
                * steps :
                         * 1) Add config.properties file under src/test/resources.
                         * 2) Update BaseClass -setup() method, add script to load config.properties file.
         * use of properties file : common values for multiple test cases
         * */
}