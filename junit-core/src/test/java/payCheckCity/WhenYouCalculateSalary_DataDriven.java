package payCheckCity;

import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.pages.Pages;
import net.thucydides.junit.annotations.TestData;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import payCheckCity.steps.PayCheckUserSteps;
import payCheckCity.steps.SystemSteps;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

@RunWith(SerenityParameterizedRunner.class)
public class WhenYouCalculateSalary_DataDriven extends BaseTest {

    private final String checkDate;
    private final String state;
    private final String grossPay;
    private final String expectedNetPay;

    public Pages pages;
    @Steps
    public PayCheckUserSteps endUser;
    @Steps
    public SystemSteps systemSteps;

    public WhenYouCalculateSalary_DataDriven(String checkDate, String state, String grossPay, String expectedNexPay) {
        this.checkDate = checkDate;
        this.state = state;
        this.grossPay = grossPay;
        this.expectedNetPay = expectedNexPay;
    }

    @TestData
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][]{
                {"06/16/2014", "California", "20000", "$303.65"},
                {"06/16/2014", "California", "20000", "$303.65"}
        });
    }

    @Before
    public void setUp() throws IOException {
        webdriver.manage().window().maximize();
    }

    //TODO jira requirement provier
    @Test
    public void calculate_netPay_Salary_California() {
        endUser.at_the_SalaryPage()
                .entersBasicSalaryValues(checkDate, state, grossPay)
                .clicks_calculate_button()
                .assert_netPayValue(expectedNetPay);
    }
}
