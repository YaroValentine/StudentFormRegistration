package qa.pages;

import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import qa.model.PracticeFormData;

import java.util.ArrayList;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static java.lang.String.format;

public class PracticeForm {

    static final String URL = "https://demoqa.com/automation-practice-form";

    //region Locators
    final SelenideElement
            firstName = $("#firstName"),
            lastName = $("#lastName"),
            email = $("#userEmail"),
            genderMale = $x("//label[@for='gender-radio-1']"),
            genderFemale = $x("//label[@for='gender-radio-2']"),
            genderOther = $x("//label[@for='gender-radio-3']"),
            userNumber = $("#userNumber"),
            dateOfBirth = $("#dateOfBirthInput"),
            monthPicker = $(".react-datepicker__month-select"),
            yearPicker = $(".react-datepicker__year-select"),
            subjects = $("#subjectsInput"),
            hobbiesSports = $x("//label[@for='hobbies-checkbox-1']"),
            hobbiesReading = $x("//label[@for='hobbies-checkbox-2']"),
            hobbiesMusic = $x("//label[@for='hobbies-checkbox-3']"),
            currentAddress = $("#currentAddress"),
            state = $("#state"),
            city = $("#city"),
            submit = $("#submit");
    //endregion


    public PracticeForm openPracticeForm() {
        open(PracticeForm.URL);

        return this;
    }

    public PracticeForm fillPracticeForm(PracticeFormData tD) {
        setFirstName(tD.getFirstName());
        setLastName(tD.getLastName());
        setEmail(tD.getEmail());
        chooseGender(tD.getGender());
        setMobileNumber(tD.getNumber());
        setDateOfBirth(tD.getDateOfBirth());
        setSubjects(tD.getSubjects());
        chooseHobbie(tD.getHobbies());
        setCurrentAddress(tD.getAddress());
        selectState(tD.getState());
        selectCity(tD.getCity());
        clickSubmit();
        return this;
    }

    public void verifyForm(PracticeFormData tD) {
        String locator = "//td[contains(text(), '%s')]/../child::td[2]";
        String[] dateOfBirthValues = tD.getDateOfBirth().replace(",", "").split(" ");
        String dateOfBirth = dateOfBirthValues[2] + " " + dateOfBirthValues[1] + "," + dateOfBirthValues[3];
        $x(format(locator, "Student Name")).scrollTo().shouldHave(text(tD.getFirstName() + " " + tD.getLastName()));
        $x(format(locator, "Student Email")).scrollTo().shouldHave(text(tD.getEmail()));
        $x(format(locator, "Gender")).scrollTo().shouldHave(text(tD.getGender()));
        $x(format(locator, "Mobile")).scrollTo().shouldHave(text(tD.getNumber()));
        $x(format(locator, "Date of Birth")).scrollTo().shouldHave(text(dateOfBirth));
        tD.getSubjects().forEach(subject -> $x(format(locator, "Subjects")).scrollTo().shouldHave(text(subject)));
        tD.getHobbies().forEach(hobbie -> $x(format(locator, "Hobbies")).scrollTo().shouldHave(text(hobbie)));
        $x(format(locator, "Address")).scrollTo().shouldHave(text(tD.getAddress()));
        $x(format(locator, "State and City")).scrollTo().shouldHave(text(tD.getState()));
        $x(format(locator, "State and City")).scrollTo().shouldHave(text(tD.getState()));
    }


    private void setFirstName(String value) {
        firstName.setValue(value);
    }

    private void setLastName(String value) {
        lastName.setValue(value);
    }

    private void setEmail(String value) {
        email.setValue(value);
    }

    private void setMobileNumber(String value) {
        userNumber.setValue(value);
    }

    private void setDateOfBirth(String date) {
        String[] dateOfBirthValues = date.replace(",", "").split(" ");
        SelenideElement datePicker = $(String.format(".react-datepicker__day--0%s:not(.react-datepicker__day--outside-month)"
                , dateOfBirthValues[2]));
        dateOfBirth.click();
        monthPicker.selectOption(dateOfBirthValues[1]);
        yearPicker.selectOption(dateOfBirthValues[3]);
        datePicker.click();
    }

    private void setSubjects(ArrayList<String> subjects) {
        if (subjects != null) {
            for (String subject : subjects) {
                this.subjects
                        .setValue(subject)
                        .pressEnter();
            }
        }
    }

    private void setCurrentAddress(String address) {
        currentAddress.setValue(address);
    }

    private void chooseGender(String gender) {
        if (gender != null) {
            switch (gender.toLowerCase()) {
                case "male":
                    genderMale.click();
                    break;
                case "female":
                    genderFemale.click();
                    break;
                case "other":
                    genderOther.click();
                    break;
            }
        }
    }

    private void chooseHobbie(ArrayList<String> hobbies) {
        if (hobbies != null) {
            for (String hobbie : hobbies) {
                switch (hobbie.toLowerCase()) {
                    case "sports":
                        hobbiesSports.scrollIntoView(true).click();
                        break;
                    case "reading":
                        hobbiesReading.scrollIntoView(true).click();
                        break;
                    case "music":
                        hobbiesMusic.scrollIntoView(true).click();
                        break;
                }
            }
        }
    }

    private void selectState(String _state) {
        state.scrollTo().click();
        $(Selectors.byText(_state)).click();
    }

    private void selectCity(String _city) {
        city.scrollTo().click();
        $(Selectors.byText(_city)).click();
    }

    private void clickSubmit() {
        submit.scrollTo();
        submit.click();
    }

}
