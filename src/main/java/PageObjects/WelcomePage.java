package PageObjects;

import Utils.Actions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class WelcomePage extends Actions {
    AndroidDriver driver;

    public WelcomePage (AndroidDriver driver){
        super(driver);
        this.driver = driver;
    PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

@AndroidFindBy (id = "global.dostavista.client.test:id/acceptButton")
    private WebElement yesBtn;
@AndroidFindBy (id = "global.dostavista.client.test:id/welcomeScreenCountrySuggestionContainer")
    private WebElement countrySelector;
@AndroidFindBy (xpath = "//android.widget.Button[@resource-id=\"global.dostavista.client.test:id/skipButton\"]")
    private WebElement skipSurveyBtn;
@AndroidFindBy (id = "global.dostavista.client.test:id/declineButton")
    private WebElement declineNotificationsBtn;

    public void chooseCountry(String countryName){
        countrySelector.click();
        driver.findElement(By.xpath("//android.widget.TextView[@resource-id='global.dostavista.client.test:id/localNameView' and @text=\""+countryName+"\"]")).click();
        yesBtn.click();
    }
    public void selectCity (String cityName){
        driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"global.dostavista.client.test:id/localNameView\" and @text=\""+cityName+"\"]")).click();
    }
    public void skipSurvey(){
        skipSurveyBtn.click();
    }
    public void declineNotifications(){
        declineNotificationsBtn.click();
    }


    public ProfilePage skipWelcomeAndGoToProfilePage() {
        if (checkIsElementDisplayed(countrySelector)) {
            chooseCountry("India");
            selectCity("Mumbai");
            skipSurvey();
            declineNotifications();
            // Иногда пункты меню нужно кликнуть 2жды так как рандомно всплывает поп ап подсказки, который можно скипнуть тапом в любое место
            clickElementMultipleTimes(By.id("global.dostavista.client.test:id/bottom_navigation_profile"), 2);
        } else {
            clickElementMultipleTimes(By.id("global.dostavista.client.test:id/bottom_navigation_profile"), 2);
    }

        return new ProfilePage(driver);
    }

    public OrderPage skipWelcomeAndGoToOrderPage(){
        if (checkIsElementDisplayed(countrySelector)) {
            chooseCountry("India");
            selectCity("Mumbai");
            skipSurvey();
            declineNotifications();
            driver.findElement(By.id("global.dostavista.client.test:id/bottom_navigation_new_order")).click();
        } else {
            driver.findElement(By.id("global.dostavista.client.test:id/bottom_navigation_new_order")).click();
        }

        return new OrderPage(driver);

    }

}
