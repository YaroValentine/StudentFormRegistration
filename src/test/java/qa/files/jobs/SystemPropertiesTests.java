package qa.files.jobs;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;

@Tag("properties")
public class SystemPropertiesTests {
    @Test
    void test1() {
        String browser = System.getProperty("browser");
        System.out.println(browser);
    }

    @Test
    void test2() {
        System.setProperty("browser2", "Safari");
        String browser = System.getProperty("browser2");
        System.out.println(browser);
    }

    @Test
    void test3() {
        String browser = System.getProperty("browser", "Opera");
        System.out.println(browser);
    }

    @Test
    void test4() {
        System.out.println("I'd like to say " + System.getProperty("say"));
    }
}

