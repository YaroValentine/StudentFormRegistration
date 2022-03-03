package qa.pages;

import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import qa.app.AppManager;

import java.util.ArrayList;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PracticeForm extends AppManager {

    static final String URL = "https://demoqa.com/automation-practice-form";

    //region Locators
    final SelenideElement
            firstName = $("#firstName"),
            lastName = $("#lastName"),
            email = $("#userEmail"),
            genderMale = $(byText("Male")),
            genderFemale = $(byText("Female")),
            genderOther = $(byText("Other")),
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
            submit = $("#submit"),
            tableStudentName = $x("//td[text()='Student Name']/.."),
            tableStudentEmail = $x("//td[text()='Student Email']/.."),
            tableGender = $x("//td[text()='Gender']/.."),
            tableMobile = $x("//td[text()='Mobile']/.."),
            tableDateOfBirth = $x("//td[text()='Date of Birth']/.."),
            tableSubjects = $x("//td[text()='Subjects']/.."),
            tableHobbies = $x("//td[text()='Hobbies']/.."),
            tablePictures = $x("//td[text()='Picture']/.."),
            tableAddress = $x("//td[text()='Address']/.."),
            tableStateAndCity = $x("//td[text()='State and City']/..");
    //endregion


    public PracticeForm openPracticeForm() {
        open(PracticeForm.URL);

        return this;
    }

    public PracticeForm fillPracticeForm() {
        setFirstName(tD.getFirstName());
        setLastName(tD.getLastName());
        setEmail(tD.getEmail());
        chooseGender(tD.getGender());
        setMobileNumber(tD.getMobile());
        setDateOfBirth(tD.getDateOfBirth());
        setSubjects(tD.getSubjects());
        chooseHobbie(tD.getHobbies());
        setCurrentAddress(tD.getAddress());
        selectState(tD.getState());
        selectCity(tD.getCity());
        clickSubmit();
        return this;
    }

    public void verifyForm() {
        String[] dateOfBirthValues = tD.getDateOfBirth().replace(",", "").split(" ");
        String dateOfBirth = dateOfBirthValues[2] + " " + dateOfBirthValues[1] + "," + dateOfBirthValues[3];

        tableStudentName.shouldHave(text(tD.getFirstName() + " " + tD.getLastName()));
        tableStudentEmail.shouldHave(text(tD.getEmail()));
        tableGender.shouldHave(text(tD.getGender()));
        tableMobile.shouldHave(text(tD.getMobile()));
        tableDateOfBirth.shouldHave(text(dateOfBirth));
        tD.getSubjects().forEach(subject -> tableSubjects.shouldHave(text(subject)));
        tD.getHobbies().forEach(hobbie -> tableHobbies.shouldHave(text(hobbie)));
        tableAddress.shouldHave(text(tD.getAddress()));
        tableStateAndCity.shouldHave(text(tD.getState()));
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
        String[] formattedDate = date.replace(",", "").split(" ");
        SelenideElement datePicker = $(String.format(".react-datepicker__day--0%s:not(.react-datepicker__day--outside-month)"
                , formattedDate[2]));
        dateOfBirth.click();
        monthPicker.selectOption(formattedDate[1]);
        yearPicker.selectOption(formattedDate[3]);
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

    private void chooseHobbie(ArrayList<String> hobbies) {
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

    private void selectState(String _state) {
        state.scrollTo().click();
        $(Selectors.byText(_state)).click();
    }

    private void selectCity(String _city) {
        city.scrollTo().click();
        $(Selectors.byText(_city)).click();
    }

    private void clickSubmit() {
        submit.scrollIntoView(true).click();
    }

}
