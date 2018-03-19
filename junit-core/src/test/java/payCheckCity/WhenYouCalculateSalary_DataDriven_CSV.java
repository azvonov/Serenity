package payCheckCity;

import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.pages.Pages;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import payCheckCity.steps.PayCheckUserSteps;
import payCheckCity.steps.SystemSteps;

import java.io.IOException;

@RunWith(SerenityParameterizedRunner.class)
@UseTestDataFrom(value = "salaryCalculation.csv")
public class WhenYouCalculateSalary_DataDriven_CSV {

    @Managed(uniqueSession = true)
    public WebDriver webdriver;
    public Pages pages;
    @Steps
    public PayCheckUserSteps endUser;
    @Steps
    public SystemSteps systemSteps;
    //variables should be named as columns in CSV but in camelCase
    private String checkDate;
    private String state;
    private String grossPay;
    private String expectedNetPay;

    //for variables initialisation in CSV approach setters will be required
    public void setCheckDate(String checkDate) {
        this.checkDate = checkDate;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setGrossPay(String grossPay) {
        this.grossPay = grossPay;
    }

    public void setExpectedNetPay(String expectedNetPay) {
        this.expectedNetPay = expectedNetPay;
    }

    @Before
    public void setUp() throws IOException {
        webdriver.manage().window().maximize();
    }

    @Test
    public void calculate_netPay_Salary_California() {
        endUser.at_the_SalaryPage()
                .entersBasicSalaryValues(checkDate, state, grossPay)
                .clicks_calculate_button()
                .assert_netPayValue(expectedNetPay);
    }
}