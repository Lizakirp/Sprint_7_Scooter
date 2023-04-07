import Order.OrderApplication;
import Order.OrderSteps;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;


public class GetOrderTest {
    private OrderSteps step = new OrderSteps();
    private OrderApplication check = new OrderApplication();
    @Test
    public void checkBody() {
        ValidatableResponse response = step.getOrderList();
        check.checkBodyList(response);
    }
}
