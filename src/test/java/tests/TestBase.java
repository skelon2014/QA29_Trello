package tests;

import framework.ApplicationManager;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;
import java.util.Arrays;

public class TestBase {
    protected static ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));
    Logger logger = LoggerFactory.getLogger(TestBase.class);

    @BeforeSuite
    public void setUp() throws InterruptedException {
        app.init();
    }

    @BeforeMethod
    public void startLogger(Method m, Object[] p){
        logger.info("Start method " + m.getName() + " with parameters: " + Arrays.asList(p));
    }

    @AfterMethod(alwaysRun = true)
    public void stopLogger(Method m){
        logger.info("Stopped method " + m.getName());
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown(){
        app.stop();
    }
}
