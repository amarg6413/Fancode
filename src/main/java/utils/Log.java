package utils;

import com.aventstack.extentreports.Status;
import listeners.TestListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * This class will have common log method which will log in extent report and log file using log4j2.xml
 */
public class Log {

    private static Logger logger = LogManager.getRootLogger();

    public void info(Status statusReport, String message){
        logger.info(message);
        try {
            TestListener.test.get().log(statusReport,message);
        }catch (Exception e){
        }
    }

    public void warn(Status statusReport, String message){
        logger.warn(message);
        TestListener.test.get().log(statusReport,message);
    }
}
