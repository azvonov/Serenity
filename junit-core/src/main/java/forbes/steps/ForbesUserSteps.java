package forbes.steps;

import forbes.pages.BillionairesPage;
import net.thucydides.core.annotations.Step;

public class ForbesUserSteps {
    BillionairesPage billionairesPage;

    @Step
    public ForbesUserSteps at_the_billionariesListPage(){
        billionairesPage.open();
        return  this;
    }

    @Step
    public ForbesUserSteps findPersonByName(String name){
        billionairesPage.getRowByPersonName(name);
        return  this;
    }

}
