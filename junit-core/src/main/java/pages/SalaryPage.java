package pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.At;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.annotations.WhenPageOpens;

//@At("#HOST/calculator/salary/") will match both
//http://www.paycheckcity.com/calculator/salary/
//http://localhost:8080/calculator/salary/
@At("http://www.paycheckcity.com/calculator/salary/")

//@NamedUrls(
//  {
//      @NamedUrl(name = "open.issue", url = "http://jira.mycompany.org/issues/{1}")
//      @NamedUrl(name = "close.issue", url = "/issues/close/{1}")       will take begining of the link from @DefaultUrl
//  }
//)
//page.open("open.issue", withParameters("ISSUE-1"));      how to use from the code
@DefaultUrl("http://www.paycheckcity.com/calculator/salary/")
public class SalaryPage extends PageObject {

    @FindBy(id = "calcDate")
    private WebElementFacade calcDate;
    //initialising elements with overrided timeout
    @FindBy(id = "state", timeoutInSeconds="5")
    private WebElementFacade state;

    @FindBy(xpath = "id(\"generalInformation.grossPayAmount\")")
    private WebElementFacade grossPayInput;

    public SalaryPage setCalcDate(String date) {
        //The typeInto method is a shorthand that simply clears a field and enters the specified text
        typeInto(calcDate, date);
        return this;
    }

    @WhenPageOpens
    public void waitUntilCalcDateUppearAppear() {
        element(calcDate).waitUntilVisible();  //Example of @WhenPageOpens.  not really needed here
    }

    public SalaryPage setState(String state) {
        enter(state).into(this.state);
        return this;
    }

    public SalaryPage setGrossPayInput(String value) {
        grossPayInput.clear();
        grossPayInput.sendKeys(value);
        return this;
    }

    public void clickCalculateButton() {
        $("#calculate").click();
        //The findBy method lets you pass the css or xpath selector directly
        //findBy("#calculate").click();
    }
}