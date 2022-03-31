package qa;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Allure;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import qa.app.AppManager;
import qa.model.TestData;


public class AnnotationsPracticeFormTest extends TestBase {

//    @Disabled
    @DisplayName("Шаги с аннотацией @Step внутри страницы PracticeForm")
    @ParameterizedTest
    @MethodSource("qa.app.DataSources#practiceFormTestData")
    public void studentRegistrationForm(TestData tD) {
        SelenideLogger.addListener("allure", new AllureSelenide());
        Allure.parameter("Parameters", tD);

        new AppManager(tD)
                .practiceForm().open()
                .fillPracticeForm()
                .verifyForm()
        ;
    }
}
