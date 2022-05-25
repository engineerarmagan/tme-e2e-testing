package tme.step_definitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import tme.utilities.Driver;

import java.util.concurrent.TimeUnit;

public class Hooks {
    @Before("@db")
    public void setUpdb()
    {
        System.out.println("db connection");
    }

    @After
        public void tearDown(Scenario scenario)
        {
       if(scenario.isFailed()){
           final  byte[] screenshot= ((TakesScreenshot)Driver.get()).getScreenshotAs(OutputType.BYTES);// this will take screetshot as byte
           scenario.attach(screenshot,"image/png","screenshot");// attaching screenshot to my report
       }
           Driver.closeDriver();
        }

    @Before
    public void setUp()
    {
        System.out.println("this is before");
         Driver.get().manage().window().maximize();

    }}