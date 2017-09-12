package forbes.pages;

import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import net.thucydides.core.pages.components.HtmlTable;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static net.thucydides.core.matchers.BeanMatchers.the;
import static org.hamcrest.Matchers.is;

@DefaultUrl("https://www.forbes.com/billionaires/list/")
public class BillionairesPage extends PageObject {

    @FindBy(id="the_list")
    HtmlTable table;

    public List<WebElement> getRowByPersonName(String name){
       return table.getRowElementsWhere(the("Name", is(name)));
    }
}
