package qa;

import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Allure;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CleanSelenideWithListener {

//    @Disabled
    @DisplayName("Чистый Selenide (с Listener)")
    @Test
    public void cleanSelenideWithListener() {
//        SelenideLogger.addListener("allure", new AllureSelenide());
            open("https://github.com/");

            $(".header-search-input").click();
            $(".header-search-input").sendKeys("eroshenkoam/allure-example");
            $(".header-search-input").submit();

            $(By.linkText("eroshenkoam/allure-example")).click();

            $(By.partialLinkText("Issues")).click();
            Allure.addAttachment("Page Source", "text/html", WebDriverRunner.source(), "html");

            $(withText("#68")).should(exist);
    }
}
