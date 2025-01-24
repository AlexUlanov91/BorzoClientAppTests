import PageObjects.OrderPage;
import TestUtils.Base;
import org.junit.jupiter.api.Test;

public class OrdersTest extends Base {
    OrderPage orderPage = welcomePage.skipWelcomeAndGoToOrderPage();

    @Test
    public void guestOrderCreation() {
        orderPage.choosePickupAddress("Mumbai Central Railway Station Building");
        orderPage.enterPickupPointPhone("953214234321");
        orderPage.chooseDropoffAddress("Mumbai Central Railway Station Building");
        orderPage.enterDropoffPointPhone("953214234322");
    }
}
