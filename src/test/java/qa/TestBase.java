package qa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import qa.app.AppManager;

import static qa.app.AppManager.errorList;

public class TestBase {
    private static AppManager app;


    @BeforeAll
    static void setUp() {
        Configuration.browserSize = "1980x1200";
        app = new AppManager();
        app.init();
    }

    @AfterAll
    static void tearDown() {
        if (!errorList.isEmpty()) {
            System.out.println("Error List: ");
            errorList.forEach(s -> System.out.println("FAIL " + s));
        }
    }

}
