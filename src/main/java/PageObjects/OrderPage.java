package PageObjects;

import Utils.Actions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrderPage extends Actions {
    AndroidDriver driver;

    public OrderPage(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Pickup point']")
    private WebElement pickupPointText;


//Address pickers
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Pickup point']/following::android.widget.EditText[@resource-id='global.dostavista.client.test:id/addressPicker']")
    private WebElement pickupAddressPicker;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Delivery point']/following::android.widget.EditText[@resource-id='global.dostavista.client.test:id/addressPicker']")
    private WebElement dropoffAddressPicker;

//Phone fields
    @AndroidFindBy (id = "global.dostavista.client.test:id/phoneEditText")
    private List <WebElement> phoneField;


//Inside addressPicker
    // Confirm inside addressPicker
    @AndroidFindBy (id = "global.dostavista.client.test:id/confirmButton")
     private WebElement confirmAddressBtn;
    //Address inside addressPicker
    @AndroidFindBy (id = "global.dostavista.client.test:id/addressEditText")
      private WebElement addressEditField;
    //Address inside addressPicker
    @AndroidFindBy (xpath = "//android.widget.TextView[@resource-id='global.dostavista.client.test:id/titleTextView']")
       private List<WebElement> addressSuggestion;


    public void waitForSuggestionAddresses(){
        waitForXpathElementToAppear("//android.widget.TextView[@resource-id=\"global.dostavista.client.test:id/bodyTextView\"]", driver);
    }
    public void choosePickupAddress(String address) {
        pickupAddressPicker.click();
        addressEditField.sendKeys(address);
        waitForSuggestionAddresses();
        addressSuggestion.get(1).click(); //click on first suggestion
        confirmAddressBtn.click();
    }

    public void chooseDropoffAddress(String address) {
        scrollOneTime();
        dropoffAddressPicker.click();
        addressEditField.sendKeys(address);
        waitForSuggestionAddresses();
        addressSuggestion.get(1).click(); //click on first suggestion
        confirmAddressBtn.click();
    }

    public void enterPickupPointPhone(String phoneNumber){
        phoneField.get(0).sendKeys(phoneNumber);
    }

    public void enterDropoffPointPhone(String phoneNumber){
        int phoneFieldCount = phoneField.size();
        if (phoneFieldCount > 1) {
            phoneField.get(1).sendKeys(phoneNumber);
        }else {
            phoneField.get(0).sendKeys(phoneNumber);
        }
    }

}
