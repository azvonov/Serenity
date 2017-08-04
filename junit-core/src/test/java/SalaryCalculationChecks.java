import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.*;
import net.thucydides.core.pages.Pages;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import requirements.Application;
import steps.EndUserSteps;
import steps.SystemSteps;

import java.io.IOException;

@Story(Application.Salary.CalculationChecks.class)
@RunWith(SerenityRunner.class)
public class SalaryCalculationChecks {

    //выполнять все тесты в одном окне
    @Managed(uniqueSession = true)
    public WebDriver webdriver;

    @ManagedPages(defaultUrl = "http://www.paycheckcity.com/calculator/salary/")
    public Pages pages;

    @Steps
    public EndUserSteps endUser;

    @Steps
    public SystemSteps systemSteps;

    @Before
    public void setUp() throws IOException {
        webdriver.manage().window().maximize();
    }

    //TODO jira requirement provier
    @Title("Override name if method name is not briliant enonugh")
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
    public void calculate_social_security(){
    }

    @Test
    public void exchageRatesShouldBetheSameAsInTheSourceSystem(){
    }
} 