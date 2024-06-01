package FancodeAPI.stepDefinitions;

import utils.Assertions;
import utils.Log;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import listeners.TestListener;

public class Hooks {

    public static Assertions assertion;//=new Assertions();
    public static final Log log = new Log();


    /**
     * This hook is a common hook to set up the test in the extent report
     * whenever a scenario starts this run before the scenario
     * @param scenario Started scenario param
     */
    @Before
    public void setUpTest(Scenario scenario) {
        TestListener.setTest(scenario);
        assertion= new Assertions();
    }

    @After
    public void performAssertAll() {
        assertion.assertAll();
    }
}
