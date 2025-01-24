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

        // Сравнение номера телефона в toolbar с номером, использованным при входе
        Assertions.assertEquals(
                StringUtils.deleteWhitespace(profile.getPhoneToolbar().getText()),
                input.get("phoneNumber")
        );
        // Ассерт на наличие кнопки топ апа
        try {
            // Попытка найти кнопку топ апа
            profile.getTopUpButton();
            // Если элемент найден, тест проходит успешно
            Assertions.assertTrue(true);
        } catch (NoSuchElementException e) {
            // Если элемент не найден, тест проваливается
            Assertions.fail("Top up button is not present.");
        }
        profile.logOut();
    }

    @ParameterizedTest
    @MethodSource("getCompanyData")
    public void successCompanyLogin(HashMap<String, String> input) {
        profile.pressLoginBtn();
        profile.loginCompany(input.get("email"), input.get("password"));

        // Сравнение емейла в toolbar с емейлом, использованным при входе
        Assertions.assertEquals(
                StringUtils.deleteWhitespace(profile.getPhoneToolbar().getText()),
                input.get("email")
        );
        // Ассерт на наличие кнопки топ апа
        try {
            // Попытка найти кнопку топ апа
            profile.getTopUpButton();
            // Если элемент найден, тест проходит успешно
            Assertions.assertTrue(true);
        } catch (NoSuchElementException e) {
            // Если элемент не найден, тест проваливается
            Assertions.fail("Top up button is not present.");
        }
        profile.logOut();
    }


    // Методы для параметризации данных:

    static Stream<HashMap<String, String>> getIndividualPersonData() throws IOException {
        List<HashMap<String, String>> data = getJsonData(System.getProperty("user.dir") + "\\src\\test\\testData\\IndividualPersonCredentials.json");
        return data.stream();
    }
    static Stream<HashMap<String, String>> getCompanyData() throws IOException {
        List<HashMap<String, String>> data = getJsonData(System.getProperty("user.dir") + "\\src\\test\\testData\\CompanyCredentials.json");
        return data.stream();
    }

}


