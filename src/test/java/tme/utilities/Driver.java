package tme.utilities;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.util.HashMap;


public class Driver {

    private Driver() {

    }

    private static WebDriver driver;


    public static WebDriver get() {
        // Test 19/05/2022
        if (driver == null) {
            // this line will tell which browser should open based on the value from properties file
            String browser = ConfigurationReader.get("browser");
            switch (browser) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
                    chromePrefs.put("profile.default_content_settings.popups", 0);
                    chromePrefs.put("download.default_directory", System.getProperty("user.dir"));
                    ChromeOptions options = new ChromeOptions();
                    options.setExperimentalOption("prefs", chromePrefs);
                    driver = new ChromeDriver(options);
                   // driver = new ChromeDriver(new ChromeOptions().addArguments("window-size=1920,1080"));
                    break;
                case "chrome-headless":
                    WebDriverManager.chromedriver().setup();
                    HashMap<String, Object> chromePrefs_less = new HashMap<String, Object>();
                    chromePrefs_less.put("profile.default_content_settings.popups", 0);
                    chromePrefs_less.put("download.default_directory", System.getProperty("user.dir"));
                    ChromeOptions options_less = new ChromeOptions();
                    options_less.setExperimentalOption("prefs", chromePrefs_less);
                    driver = new ChromeDriver(new ChromeOptions().setHeadless(true).addArguments("window-size=1920,1080"));
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                case "firefox-headless":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver(new FirefoxOptions().setHeadless(true));
                    break;
                case "ie":
                    if (!System.getProperty("os.name").toLowerCase().contains("windows"))
                        throw new WebDriverException("Your OS doesn't support Internet Explorer");
                    WebDriverManager.iedriver().setup();
                    driver = new InternetExplorerDriver();
                    break;

                case "edge":
                    if (!System.getProperty("os.name").toLowerCase().contains("windows"))
                        throw new WebDriverException("Your OS doesn't support Edge");
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                    break;

                case "safari":
                    if (!System.getProperty("os.name").toLowerCase().contains("mac"))
                        throw new WebDriverException("Your OS doesn't support Safari");
                    WebDriverManager.getInstance(SafariDriver.class).setup();
                    driver = new SafariDriver();
                    break;
            }

        }

        return driver;
    }

    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}

//
//package tme.utilities;
//
//
//        import io.github.bonigarcia.wdm.WebDriverManager;
//        import org.openqa.selenium.WebDriver;
//        import org.openqa.selenium.WebDriverException;
//        import org.openqa.selenium.chrome.ChromeDriver;
//        import org.openqa.selenium.chrome.ChromeOptions;
//        import org.openqa.selenium.edge.EdgeDriver;
//        import org.openqa.selenium.firefox.FirefoxDriver;
//        import org.openqa.selenium.firefox.FirefoxOptions;
//        import org.openqa.selenium.ie.InternetExplorerDriver;
//        import org.openqa.selenium.safari.SafariDriver;
//
//
//public class Driver {
//    private Driver() {
//
//    }
//
//    // InheritableThreadLocal  --> this is like a container, bag, pool.
//    // in this pool we can have separate objects for each thread
//    // for each thread, in InheritableThreadLocal we can have separate object for that thread
//
//    // driver class will provide separate webdriver object per thread
//    private static InheritableThreadLocal<WebDriver> driverPool = new InheritableThreadLocal<>();
//
//    public static WebDriver get() {
//        //if this thread doesn't have driver - create it and add to pool
//        if (driverPool.get() == null) {
//
////            if we pass the driver from terminal then use that one
////           if we do not pass the driver from terminal then use the one properties file
//            String browser = System.getProperty("browser") != null ? browser = System.getProperty("browser") : ConfigurationReader.get("browser");
//
//            switch (browser) {
//                case "chrome":
//                    WebDriverManager.chromedriver().setup();
//                    driverPool.set(new ChromeDriver());
//                    break;
//                case "chrome-headless":
//                    WebDriverManager.chromedriver().setup();
//                    driverPool.set(new ChromeDriver(new ChromeOptions().setHeadless(true)));
//                    break;
//                case "firefox":
//                    WebDriverManager.firefoxdriver().setup();
//                    driverPool.set(new FirefoxDriver());
//                    break;
//                case "firefox-headless":
//                    WebDriverManager.firefoxdriver().setup();
//                    driverPool.set(new FirefoxDriver(new FirefoxOptions().setHeadless(true)));
//                    break;
//                case "ie":
//                    if (!System.getProperty("os.name").toLowerCase().contains("windows"))
//                        throw new WebDriverException("Your OS doesn't support Internet Explorer");
//                    WebDriverManager.iedriver().setup();
//                    driverPool.set(new InternetExplorerDriver());
//                    break;
//
//                case "edge":
//                    if (!System.getProperty("os.name").toLowerCase().contains("windows"))
//                        throw new WebDriverException("Your OS doesn't support Edge");
//                    WebDriverManager.edgedriver().setup();
//                    driverPool.set(new EdgeDriver());
//                    break;
//
//                case "safari":
//                    if (!System.getProperty("os.name").toLowerCase().contains("mac"))
//                        throw new WebDriverException("Your OS doesn't support Safari");
//                    WebDriverManager.getInstance(SafariDriver.class).setup();
//                    driverPool.set(new SafariDriver());
//                    break;
//            }
//        }
//        return driverPool.get();
//    }
//
//    public static void closeDriver() {
//        driverPool.get().quit();
//        driverPool.remove();
//    }
//}