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

@Story(ForbesApp.Billionaires.ListChecks.class)
@RunWith(SerenityRunner.class)
public class WhenYouOpenBillionairesList {

    @Managed(uniqueSession = true)
    public WebDriver webdriver;

    @ManagedPages
    public Pages pages;

    @Steps
    public ForbesUserSteps user;

    @Before
    public void setUp() throws IOException {
        webdriver.manage().window().maximize();
    }

    @Test
    public void billGatesShouldBeFromMicrosoftCompany() {
        user.at_the_billionariesListPage().findPersonByName("Bill Gates");
    }
}
