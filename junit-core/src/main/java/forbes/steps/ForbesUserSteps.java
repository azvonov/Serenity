package forbes.steps;

import forbes.pages.BillionairesPage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

import java.util.List;
import java.util.Map;

public class ForbesUserSteps extends ScenarioSteps {

    BillionairesPage billionairesPage;

    @Step
    public ForbesUserSteps at_the_billionariesListPage(){
        billionairesPage.open();
        return  this;
    }

    @Step
    public List<Map<Object, String>> getTableRows(){
        return billionairesPage.getTableRows();
    }
}
