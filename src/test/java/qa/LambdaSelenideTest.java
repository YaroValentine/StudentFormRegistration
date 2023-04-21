package qa;

import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class LambdaSelenideTest {

    @Disabled
    @Feature("Task in repo")
    @Story("Creating of a new Task")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("yaro")
    @Description("This is a demo test that illustrates allure steps")
    @Link(value = "QA", url = "https://gihub.com")
    @DisplayName("Лямбда шаги через step (name, () -> {})")
    @Test
    public void testIssueSearch() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        Allure.parameter("Name", "Value");
        step("Open GitHub", () -> {
            open("https://github.com/");
        });

        step("Search eroshenkoam/allure-example", () -> {
            $(".header-search-input").click();
            $(".header-search-input").sendKeys("eroshenkoam/allure-example");
            $(".header-search-input").submit();
        });

        step("Open link 'eroshenkoam/allure-example'", () -> {
            $(By.linkText("eroshenkoam/allure-example")).click();
        });

        step("Navigate to Issues", () -> {
            $(By.partialLinkText("Issues")).click();
            Allure.addAttachment("Page Source", "text/html", WebDriverRunner.source(), "html");
        });

        step("Verify Issue #68 exists", () -> {
            $(withText("#681")).should(exist);
        });
    }
}
