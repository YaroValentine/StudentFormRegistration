package qa;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import qa.app.AppManager;
import qa.model.TestData;


public class PracticeFormTest extends TestBase {

    @ParameterizedTest
    @MethodSource("qa.app.DataSources#practiceFormTestData")
    public void studentRegistrationForm(TestData tD) {
        new AppManager(tD)
                .practiceForm().openPracticeForm()
                .fillPracticeForm()
                .verifyForm()
        ;
    }
}
