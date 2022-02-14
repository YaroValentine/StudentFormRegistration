package qa;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import qa.model.PracticeFormData;
import qa.pages.PracticeForm;


public class PracticeFormTest extends TestBase {

    @ParameterizedTest
    @MethodSource("qa.app.DataSources#practiceFormTestData")
    public void studentRegistrationForm(PracticeFormData testData) {
        new PracticeForm()
                .openPracticeForm()
                .fillPracticeForm(testData)
                .verifyForm(testData)
        ;
    }
}
