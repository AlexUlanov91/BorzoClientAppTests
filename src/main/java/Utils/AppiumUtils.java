package Utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

public class AppiumUtils {
    public static AppiumDriverLocalService service;


    public static AppiumDriverLocalService startAppiumServer(String ipAddress, int port) {
        service = new AppiumServiceBuilder().withAppiumJS(new File("C:\\Users\\user\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
                .withIPAddress(ipAddress).usingPort(port).build();  //Starting Appium server
        service.start();
        return service;
    }
    public void waitForXpathElementToAppear(String xpath, AppiumDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5)); //WebDriverWait call provided by Selenium. Waiting untill 5 seconds
         wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
    }

    public void waitForXpathElementToBeClickable(String xpath, AppiumDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5)); //WebDriverWait call provided by Selenium. Waiting untill 5 seconds
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
    }


    public void waitForIdElementToAppear(String id, AppiumDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5)); //WebDriverWait call provided by Selenium. Waiting untill 5 seconds
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(id)));
    }

    public boolean checkIsElementDisplayed(WebElement locator) {
        try {
            return locator.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public static List<HashMap<String, String>> getJsonData(String jsonFilePath) throws IOException {
        String jsonContent = FileUtils.readFileToString(new File(jsonFilePath), StandardCharsets.UTF_8); // Parse Json file to Json String

        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(jsonContent,
                new TypeReference<List<HashMap<String, String>>>(){}); // Json String to Hash map

        return data;
    }

}
