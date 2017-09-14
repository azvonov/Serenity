package forbes.pages;

import forbes.componentns.HtmlTable;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.annotations.WhenPageOpens;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Map;

@DefaultUrl("https://www.forbes.com/billionaires/list/")
public class BillionairesPage extends PageObject {

    @FindBy(id = "the_list")
    public WebElement forbesList;

    @FindBy(xpath = "//*[@id='the_list']/tbody")
    WebElement forbesList_withNoHeadings;

    public List<Map<Object, String>> getTableRows() {
        return HtmlTable.withColumn("Img","Rank", "Name", "Net Worth", "Age", "Source", "Country")
                .readRowsFrom(forbesList_withNoHeadings);
    }
    @WhenPageOpens
    private void closeBanner() {
        $(".continue-button").click();
        waitFor("#the_list");
    }
}
