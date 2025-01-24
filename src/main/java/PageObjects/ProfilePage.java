package PageObjects;

import Utils.Actions;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePage extends Actions {
    AndroidDriver driver;

    public ProfilePage(AndroidDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy (id = "global.dostavista.client.test:id/loginButton")
    private WebElement loginBtn;
    @AndroidFindBy (id = "global.dostavista.client.test:id/tabStoreTextView")
    private WebElement storeBtn;
    @AndroidFindBy (id = "global.dostavista.client.test:id/phoneEditText")
    private WebElement phoneField;
    @AndroidFindBy (id = "global.dostavista.client.test:id/emailEditText")
    private WebElement emailField;
    @AndroidFindBy (id = "global.dostavista.client.test:id/passwordEditText")
    private WebElement passwordField;
    @AndroidFindBy (id = "global.dostavista.client.test:id/fab")
    private WebElement proceedBtn;
    @AndroidFindBy (id = "global.dostavista.client.test:id/toolbarSubtitleView")
    private WebElement phoneToolbar;
        public WebElement getPhoneToolbar() {
          return phoneToolbar;
      }
    @AndroidFindBy (id = "global.dostavista.client.test:id/topUpButton")
    private WebElement topUpButton;
        public WebElement getTopUpButton() {
            return topUpButton;
        }
    @AndroidFindBy (id = "global.dostavista.client.test:id/logoutButton")
    private WebElement logOutBtn;
        public WebElement getLogOutBtn() {
            return logOutBtn;
      }
    @AndroidFindBy (xpath = "//android.widget.Button[@text=\"Log out\"]")
    private WebElement logOutBtnWidget;


    public void pressLoginBtn(){
        loginBtn.click();
    }
    public void logOut() {
        logOutBtn.click();
        waitForXpathElementToAppear("//android.widget.Button[@text=\"Log out\"]", driver);
        logOutBtnWidget.click();
        waitForIdElementToAppear("global.dostavista.client.test:id/loginButton", driver);




    }
    public void loginIndividualPerson(String phoneNumber, String password){
        phoneField.sendKeys(phoneNumber);
        passwordField.sendKeys(password);
        passwordField.click();
        driver.pressKey(new KeyEvent(AndroidKey.DIGIT_3));
        driver.pressKey(new KeyEvent(AndroidKey.DEL));
        proceedBtn.click();
    }

    public void loginCompany(String email, String password){
    storeBtn.click();
    emailField.sendKeys(email);
    passwordField.sendKeys(password);
    passwordField.click();
    driver.pressKey(new KeyEvent(AndroidKey.DIGIT_6));
    driver.pressKey(new KeyEvent(AndroidKey.DEL));
    emailField.click();
    driver.pressKey(new KeyEvent(AndroidKey.DIGIT_6));
    driver.pressKey(new KeyEvent(AndroidKey.DEL));
    proceedBtn.click();
    }

}
