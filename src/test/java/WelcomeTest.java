import TestUtils.Base;
import org.junit.jupiter.api.Test;


public class WelcomeTest extends Base {

    @Test
    public void successWelcome() {
        welcomePage.chooseCountry("India");
        welcomePage.selectCity("Mumbai");
        welcomePage.skipSurvey();
        welcomePage.declineNotifications();
        

    }
}
