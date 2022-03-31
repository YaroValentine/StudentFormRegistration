package qa.docs;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;

public class AddOns {

    public void clearBrowser() {
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
    }

    public String currentUrl() {
        return WebDriverRunner.getWebDriver().getCurrentUrl();
    }


}
