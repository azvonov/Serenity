package payCheckCity;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.*;
import net.thucydides.core.pages.Pages;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import payCheckCity.requirements.PayCheckCityApp;
import payCheckCity.steps.PayCheckUserSteps;

@Narrative(text={"In order to calculate my salary after taxation",
        "As a user",
        "I want to be able to calculate my met salary"})

@Story(PayCheckCityApp.Salary.CalculationChecks.class)
@RunWith(SerenityRunner.class)
public class WhenYouCalculateSalary extends BaseTest {

    //выполнять все тесты в одном окне

    //   @ManagedPages(defaultUrl = "http://www.paycheckcity.com/calculator/salary/")
    //URL страницы может быть указан как в PageObject так и на уровне тестов
    @ManagedPages
    public Pages pages;

    @Steps
    public PayCheckUserSteps endUser;

    //TODO jira requirement provier
    @Test
    public void calculate_netPay_Salary_California() {

        endUser.at_the_SalaryPage()
                .entersBasicSalaryValues("06/16/2014", "California", "20000")
                .clicks_calculate_button()
                .assert_netPayValue("$303.65");
    }

    @Ignore
    @Issue("#49")
    @Test
    public void calculate_netPay_Salary_Arizona() {
        endUser.at_the_SalaryPage()
                .entersBasicSalaryValues("06/16/2014", "California", "20000")
                .clicks_calculate_button()
                .assert_netPayValue("$302.33");
    }

    @Pending
    @Test
    public void calculate_social_security() {
    }

    @Test
    public void exchageRatesShouldBetheSameAsInTheSourceSystem() {
    }

}