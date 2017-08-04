package pages;

import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SalaryCalculatorResultPage extends PageObject {

    private final String NET_PAY_RESULT_LOCATOR
                = "id(\"dijit_TitlePane_1_pane\")/ul[1]/li[1]/div[1]/span[2]";
    @FindBy(how = How.XPATH, using = NET_PAY_RESULT_LOCATOR)
    private WebElement netPayInput;

    public SalaryCalculatorResultPage(WebDriver driver) {
        super(driver);
    }

    public String getNetPayValue() {
        waitFor(ExpectedConditions
                .visibilityOfElementLocated(By.xpath(NET_PAY_RESULT_LOCATOR)));
        return netPayInput.getText();
    }
}
