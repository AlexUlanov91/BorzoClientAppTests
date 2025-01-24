package TestUtils;

import PageObjects.WelcomePage;
import Utils.AppiumUtils;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.JavascriptExecutor;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.Properties;

public class Base extends AppiumUtils {
    public static AppiumDriverLocalService service;
    public static AndroidDriver driver;
    public static WelcomePage welcomePage;
    private static final String appPackage = "global.dostavista.client.test";
    private static final String appActivity = "com.sebbia.delivery.client.ui.splash.SplashActivity";
    private static final String appLocation = System.getProperty("user.dir")+ "\\src\\test\\resources\\global.dostavista.client.test-1.104.0.2278.apk";


    @BeforeAll
    public static void ConfigureAppium() throws URISyntaxException, MalformedURLException {
        Properties properties = new Properties();
        FileInputStream fis;
        try {
            fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\data.properties");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String ipAddress = properties.getProperty("ipAddress");
        String port = properties.getProperty("port");
        service = startAppiumServer(ipAddress, Integer.parseInt(port));


        UiAutomator2Options options = getUiAutomator2Options();
        driver = new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(), options); // url + options to launch appium
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); //setting timeout to find element
        welcomePage = new WelcomePage(driver);
    }

    private static UiAutomator2Options getUiAutomator2Options() {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("Device"); //emulator device name
        options.setChromedriverExecutable("D:\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe"); //setting chromedriver
        options.setApp(appLocation);//resouse (app) path
        options.setCapability("appPackage", appPackage);
        options.setCapability("appActivity", appActivity);
        options.setCapability("enforceXPath1", true);
        return options;
    }

    @AfterEach
    public void resetApp() throws InterruptedException {
        driver.terminateApp(appPackage);
        driver.activateApp(appPackage);
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.terminateApp(appPackage);  // Закрывает приложение
            driver.quit();      // Завершает сессию, если не закрыто
        }
        if (service != null) {
            service.stop();     // Останавливает сервер Appium
        }
    }

}
