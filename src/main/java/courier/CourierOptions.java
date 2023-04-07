package courier;

import org.apache.commons.lang3.RandomStringUtils;

public class CourierOptions {
    public Courier courierDefault() {

        return new Courier("lizakirp1", "1234", "testkirp");
    }
    public Courier random() {

        return new Courier(RandomStringUtils.randomAlphanumeric(5), "1234", "testkirp");
    }
}
