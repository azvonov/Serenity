package pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.At;

@At("http://www.paycheckcity.com/calculator/salary/")
public class SalaryPage extends PageObject {

    @FindBy(id = "calcDate")
    private WebElementFacade calcDate;

    @FindBy(id = "state")
    private WebElementFacade state;

    @FindBy(xpath = "id(\"generalInformation.grossPayAmount\")")
    private WebElementFacade grossPayInput;

    @FindBy(id = "")
    private WebElementFacade exemptFederal;

    @FindBy(id = "calculate")
    private WebElementFacade calculateButton;

    public SalaryPage setCalcDate(String date) {
        calcDate.clear();
        calcDate.sendKeys(date);
        return this;
    }

    public SalaryPage setState(String state) {
        this.state.clear();
        this.state.sendKeys(state);
        return this;
    }

    public SalaryPage setGrossPayInput(String value) {
        grossPayInput.clear();
        grossPayInput.sendKeys(value);
        return this;
    }

    public SalaryPage clickExemptFederal() {
        exemptFederal.click();
        return this;
    }

    public void clickCalculateButton(){
        calculateButton.click();
    }
}