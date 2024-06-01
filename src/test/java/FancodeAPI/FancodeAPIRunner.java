package FancodeAPI;

import io.cucumber.testng.*;
import listeners.TestListener;
import org.testng.annotations.*;
import utils.Base;

/**
 * This is the runner file to run the feature file that we had written
 */
@Listeners(TestListener.class)
@CucumberOptions(
        features = {"src/test/java/FancodeAPI/features"},
        plugin = {"html:Reports/report.html","json:Reports/report.json"},
        glue = {"FancodeAPI.stepDefinitions"},
        tags = "@Fancode"
)
public class FancodeAPIRunner extends AbstractTestNGCucumberTests {
    private TestNGCucumberRunner testNGCucumberRunner;

    @BeforeClass(alwaysRun = true)
    @Parameters({"environment", "platform"})
    public void setUp(@Optional("dev") String environment, @Optional("fancode") String platform){
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
        Base.properties.setProperty("environment", environment);
        Base.loadPropertiesFromEnv(platform);
    }

    @Test(groups = {"cucumber"}, description = "Runs Cucumber Scenarios", dataProvider = "scenarios")
    public void runScenario(PickleWrapper pickleWrapper, FeatureWrapper featureWrapper) {
        this.testNGCucumberRunner.runScenario(pickleWrapper.getPickle());
    }

    @DataProvider(name = "scenarios")
    public Object[][] scenarios() {
        return this.testNGCucumberRunner == null ? new Object[0][0] : this.testNGCucumberRunner.provideScenarios();
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() {
        if (this.testNGCucumberRunner != null) {
            this.testNGCucumberRunner.finish();
        }
    }

}
