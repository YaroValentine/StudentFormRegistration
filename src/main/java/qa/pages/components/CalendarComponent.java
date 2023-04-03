package qa.pages.components;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class CalendarComponent {

    @Step("Set Date")
    public void setDate(String day, String month, String year) {
        $(".react-datepicker__month-select").selectOption(month);
        $(".react-datepicker__year-select").selectOption(year);
        $(".react-datepicker__day--0" + day +
                ":not(.react-datepicker__day--outside-month)").click();
    }
}