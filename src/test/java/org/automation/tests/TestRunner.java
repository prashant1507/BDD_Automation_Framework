package org.automation.tests;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.automation.driver.Driver;
import org.automation.setpath.ScreenshotPath;
import org.automation.setpath.VideoPath;
import org.testng.annotations.*;

@CucumberOptions(
        features = "src/test/resources/Features",
        glue={"org.automation.steps"},
        monochrome = true
        // tags = "@Test1",
        // tags = "@Test1, @Test3",
        // tags = "not @Test1",
        // tags = "@wip and not @slow",
        // tags = "@smoke and @fast",
        // tags = "@gui or @database",
        // tags = "(@smoke or @ui) and (not @slow)",
        // To run specific tag using mvn -
        //          mvn clean test -Dcucumber.filter.tags="@TAGNAME"
        //          mvn clean test -Dcucumber.filter.tags="@TAGNAME and @TAGENAME2"
        //          mvn clean test -Dcucumber.filter.tags="@TAGNAME or @TAGNAME2"
)
public class TestRunner extends AbstractTestNGCucumberTests {

    @Parameters("Browser")
    @BeforeMethod
    protected void setUp(String browser) {
        try {
            Driver.initDriver(browser);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterMethod
    protected void tearDown() {
        Driver.quiteDriver();
        ScreenshotPath.unloadDir();
        VideoPath.flushVideoPath();
    }

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
