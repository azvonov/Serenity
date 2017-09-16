package payCheckCity.steps;

import net.thucydides.core.annotations.Screenshots;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import payCheckCity.pages.SalaryCalculatorResultPage;
import payCheckCity.pages.SalaryPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class PayCheckUserSteps extends ScenarioSteps {

    SalaryPage salaryPage;
    SalaryCalculatorResultPage salaryCalculatorResultPage;

    @Step
    @Screenshots(onlyOnFailures=true)
    public PayCheckUserSteps entersBasicSalaryValues(String calcDate, String state, String grossPayAmount) {
        //checks the page loaded properly
        getPages().currentPageAt(SalaryPage.class);
        salaryPage.setCalcDate(calcDate);
        salaryPage.setState(state);
        salaryPage.setGrossPayInput(grossPayAmount);
        return this;
    }

    @Step
    public PayCheckUserSteps clicks_calculate_button() {
        salaryPage.clickCalculateButton();
        return this;
    }

    @Step
    public PayCheckUserSteps at_the_SalaryPage() {
        salaryPage.open();
        return this;
    }

    @Step
    public PayCheckUserSteps assert_netPayValue(String expectedValue) {
        assertThat("NePay's actual value differs from the gieven",
                salaryCalculatorResultPage.getNetPayValue(),
                is(expectedValue));
        return this;
    }
}