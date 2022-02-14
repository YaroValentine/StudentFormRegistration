package qa.app;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.SneakyThrows;
import org.junit.Assert;
import qa.pages.MainPage;
import qa.pages.PracticeForm;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static com.codeborne.selenide.Selenide.$$;

public class AppManager {

    public static ArrayList<String> errorList;
    private Navigation navigation;
    private MainPage mainPage;
    private PracticeForm practiceForm;

    public void init() {
        errorList = new ArrayList<>();
        mainPage().openMainPage();
    }

    //region Lazy Page Initialization
    public Navigation navigation() {
        return navigation == null ? new Navigation() : navigation;
    }

    public MainPage mainPage() {
        return mainPage == null ? new MainPage() : mainPage;
    }

    public PracticeForm practiceForm() {
        return practiceForm == null ? new PracticeForm() : practiceForm;
    }
    //endregion

    @SneakyThrows
    protected void sleep(int timeout) {
        Thread.sleep(timeout);
    }

    public boolean attributeExists(SelenideElement locator, String attrName) {
        try {
            String value = locator.attr(attrName);
            if (value != null) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Attribute " + attrName + " not found");
        }
        return false;
    }

    public String currentDate() {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        return df.format(new Date());
    }

    public void checkElements() {
        List<String> listOfElementsToCheck = new ArrayList<>();
        ElementsCollection listOfQualifiedElements = $$("");
        for (SelenideElement element : listOfQualifiedElements) {
            boolean qualifierExists = Objects.equals(element.getAttribute("AttributeName"), "State Of Attribute");
            if (qualifierExists) {
                //Action. For example if we are looking for all fields that are having error message, we can extract it.
                String errorText = element.$x("subXpath").getText();
                listOfElementsToCheck.add(errorText);
            }
        }
        //Put errors into one list to throw them out upon test completion
        StringBuilder error = new StringBuilder();
        if (!listOfElementsToCheck.isEmpty()) {
            int i = 0;
            for (String elementsText : listOfElementsToCheck) {
                i++;
                error.append(i).append(") ").append(elementsText).append(" is required. ");
            }
            Assert.fail(error.toString());
        }
    }
}
