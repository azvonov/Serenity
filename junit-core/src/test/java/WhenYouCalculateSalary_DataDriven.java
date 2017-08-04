import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.ManagedPages;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.pages.Pages;
import net.thucydides.junit.annotations.TestData;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import steps.EndUserSteps;
import steps.SystemSteps;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

@RunWith(SerenityParameterizedRunner.class)
public class WhenYouCalculateSalary_DataDriven {

    @TestData
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][]{
                {"06/16/2014", "California", "20000", "$303.65"},
                {"06/16/2014", "California", "20000", "$303.65"}
        });
    }

    private final String checkDate;
    private final String state;
    private final String grossPay;
    private final String expectedNetPay;


    public WhenYouCalculateSalary_DataDriven(String checkDate, String state, String grossPay, String expectedNexPay) {
        this.checkDate = checkDate;
        this.state = state;
        this.grossPay = grossPay;
        this.expectedNetPay = expectedNexPay;
    }

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
                .entersBasicSalaryValues(checkDate, state, grossPay)
                .clicks_calculate_button()
                .assert_netPayValue(expectedNetPay);
    }
}
