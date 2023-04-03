package qa;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;
import qa.app.AppManager;
import qa.helpers.Attach;

import static qa.app.AppManager.errorList;

public class TestBase {

    @BeforeAll
    static void setUp() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        Configuration.browserSize = "1980x1200";
//        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;

        new AppManager().init();
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last Screenshot");
        Attach.addVideo();
        Attach.pageSource();
        Attach.browserConsoleLogs();
    }

    @AfterAll
    static void tearDown() {
        if (!errorList.isEmpty()) {
            System.out.println("Error List: ");
            errorList.forEach(s -> System.out.println("FAIL " + s));
        }
    }

}
