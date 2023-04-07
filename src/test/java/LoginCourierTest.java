import courier.*;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LoginCourierTest {
    private CourierOptions options = new CourierOptions();
    private CourierApplication check;
    private CourierSteps step;
    private Courier courier;
    private CourierData data;
    int id;

    @Before
    @Step("create objects for tests")
    public void setUp() {
        step = new CourierSteps();
        courier = options.random();
        step.create(courier);
        data = CourierData.from(courier);
        check = new CourierApplication();

    }
    @Test
    @Step("login with correct values")
    public void loginSuccessFullyTest() {
        ValidatableResponse responseLogin = step.login(data);
        check.loginSuccess(responseLogin);
        id = responseLogin.extract().path("id");
    }
    @Test
    @Step("login without login and check message error")
    public void loginWithotLoginTest() {
        CourierData courierDataWithoutLogin = new CourierData("", courier.getPassword()); //c null всё виснет.
        ValidatableResponse responseMessageError = step.login(courierDataWithoutLogin);
        check.loginCourierWithoutLogin(responseMessageError);

    }
    @Test
    @Step("login without password and check message error")
    public void loginWithoutPasswordTest() {
        CourierData courierDataWithoutPassword = new CourierData(courier.getLogin(), "");
        ValidatableResponse responseMessageError = step.login(courierDataWithoutPassword);
        check.loginCourierWithoutLogin(responseMessageError);
    }
    @Test
    @Step ("Check massage after input incorrect login")
    public void loginWithNotCorrectLogin() {
        CourierData courierDataWithNotCorrectLogin = new CourierData("qwerty", data.getPassword());
        ValidatableResponse responseMessageError = step.login(courierDataWithNotCorrectLogin);
        check.loginCourierWithFalseValues(responseMessageError); // баг
        id = responseMessageError.extract().path("id");
    }
    @Test
    @Step ("Check message after login courier with incorrect password")
    public void loginWithNotCorrectPassword() {
        CourierData courierDataHere = new CourierData(data.getLogin(), "0000");
        ValidatableResponse responseMessageError = step.login(courierDataHere);
        check.loginCourierWithFalseValues(responseMessageError);
    }
    @After
    @Step ("delete test couriers")
    public void deleteCourier(){
        if (id != 0) {
            step.deleteCourier(id);
        }
    }
}
