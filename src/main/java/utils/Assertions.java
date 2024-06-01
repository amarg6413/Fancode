package utils;

import com.aventstack.extentreports.Status;
import org.testng.asserts.IAssert;
import org.testng.asserts.SoftAssert;

/**
 * This class is extending soft assertion to add pass fail steps in logs and reports
 */
public class Assertions extends SoftAssert {

    private static Log log =new Log();

    public void onAssertSuccess(IAssert<?> iAssert) {
        super.onAssertSuccess(iAssert);
        log.info(Status.PASS,iAssert.getMessage()+" Expected Value: " + iAssert.getExpected() + " ,Actual Value: " + iAssert.getActual());
    }

    public void onAssertFailure(IAssert<?> iAssert, AssertionError assertionError) {
        super.onAssertFailure(iAssert, assertionError);
        log.info(Status.FAIL,iAssert.getMessage()+" Expected Value: " + iAssert.getExpected() + " ,Actual Value: " + iAssert.getActual());
    }

}
