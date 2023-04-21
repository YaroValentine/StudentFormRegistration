package qa;

import io.qameta.allure.AllureId;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import qa.model.TestData;

public class QuickTest extends TestBase{
    @ParameterizedTest
    @MethodSource("qa.app.DataSources#getData")
    @AllureId("1")
    void xlsTest(TestData tD) {
        System.out.println(tD.getFirstName());
        System.out.println(tD.getLastName());
        System.out.println(tD.getCity());
    }
}
