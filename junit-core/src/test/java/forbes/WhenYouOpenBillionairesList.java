package forbes;

import forbes.requirements.ForbesApp;
import forbes.steps.ForbesUserSteps;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.ManagedPages;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.pages.Pages;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static net.thucydides.core.matchers.BeanMatcherAsserts.shouldMatch;
import static net.thucydides.core.matchers.BeanMatchers.the;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Story(ForbesApp.Billionaires.ListChecks.class)
@RunWith(SerenityRunner.class)
public class WhenYouOpenBillionairesList {

    @Managed(uniqueSession = true)
    public WebDriver webdriver;

    @Steps
    public ForbesUserSteps user;

    @ManagedPages
    public Pages pages;

    @Before
    public void setUp() throws IOException {
        webdriver.manage().window().maximize();
    }

    @Test
    public void billGatesShouldBeFromMicrosoftCompany() {
        List<Map<Object, String>> tableRows = user.at_the_billionariesListPage()
                .getTableRows();
        assertThat(tableRows.get(0), allOf(hasEntry("Name", "Bill Gates"),hasEntry("Source","Microsoft")));
        shouldMatch(tableRows.get(0),the("Name",is("Bill Gates")));
//   inTable(pages.getPage(BillionairesPage.class).forbesList)
//                .shouldHaveRowElementsWhere(the("Name", is("Bill Gates")));
    }


}