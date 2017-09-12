package payCheckCity.pages;

import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.support.FindBy;

public class SalaryCalculatorResultPage extends PageObject {

    @FindBy(xpath="id(\"dijit_TitlePane_1_pane\")/ul[1]/li[1]/div[1]/span[2]")
    private WebElementFacade netPayInput;

    public String getNetPayValue() {
        return netPayInput.getText();
    }
   // @WhenPageOpens  на этой странице не используется
    private void waitTillPageLoaded(){
        netPayInput.waitUntilVisible();
    }
}
