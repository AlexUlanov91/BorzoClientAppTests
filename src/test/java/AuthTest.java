import PageObjects.ProfilePage;
import TestUtils.Base;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.NoSuchElementException;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

public class AuthTest extends Base{
    ProfilePage profile = welcomePage.skipWelcomeAndGoToProfilePage();



    @ParameterizedTest
    @MethodSource("getIndividualPersonData")
    public void successIndividualPersonLogin(HashMap<String, String> input) {
        profile.pressLoginBtn();
        profile.loginIndividualPerson(input.get("phoneNumber"), input.get("password"));

        // Comparing phone number in toolbar and used phone number to log in
        Assertions.assertEquals(
                StringUtils.deleteWhitespace(profile.getPhoneToolbar().getText()),
                input.get("phoneNumber")
        );

        // Top-up button presence assert
        Assertions.assertDoesNotThrow(() -> profile.getTopUpButton(),
                "Top up button is not present.");
        profile.logOut();
    }

    @ParameterizedTest
    @MethodSource("getCompanyData")
    public void successCompanyLogin(HashMap<String, String> input) {
        profile.pressLoginBtn();
        profile.loginCompany(input.get("email"), input.get("password"));

        // Comparing email in toolbar and used email to log in
        Assertions.assertEquals(
                StringUtils.deleteWhitespace(profile.getPhoneToolbar().getText()),
                input.get("email")
        );
        // Top-up button presence assert

        Assertions.assertDoesNotThrow(() -> profile.getTopUpButton(),
                "Top up button is not present.");
        profile.logOut();
    }


    // Parametrised data methods:
    static Stream<HashMap<String, String>> getIndividualPersonData() throws IOException {
        List<HashMap<String, String>> data = getJsonData(System.getProperty("user.dir") + "\\src\\test\\testData\\IndividualPersonCredentials.json");
        return data.stream();
    }
    static Stream<HashMap<String, String>> getCompanyData() throws IOException {
        List<HashMap<String, String>> data = getJsonData(System.getProperty("user.dir") + "\\src\\test\\testData\\CompanyCredentials.json");
        return data.stream();
    }

}


