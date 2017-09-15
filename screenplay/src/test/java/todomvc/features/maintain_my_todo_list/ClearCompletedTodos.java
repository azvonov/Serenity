package todomvc.features.maintain_my_todo_list;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import todomvc.questions.ClearCompletedItems;
import todomvc.questions.TheItems;
import todomvc.tasks.Clear;
import todomvc.tasks.CompleteItem;
import todomvc.tasks.Start;

import static net.serenitybdd.screenplay.GivenWhenThen.*;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static todomvc.questions.ElementAvailability.Unavailable;

@RunWith(SerenityRunner.class)
@WithTags({
        @WithTag("Screenplay pattern"),
        @WithTag("version:RELEASE-2"),
})
public class ClearCompletedTodos {

    @Managed
    private WebDriver hisBrowser;
    private Actor james = Actor.named("James");
    @Before public void jamesCanBrowseTheWeb() {
        james.can(BrowseTheWeb.with(hisBrowser));
    }

    @Test
    public void should_be_able_to_clear_completed_todos() {

        givenThat(james).wasAbleTo(Start.withATodoListContaining("Walk the dog", "Put out the garbage"));

        when(james).attemptsTo(
                CompleteItem.called("Walk the dog"),
                Clear.completedItems()
        );

        then(james).should(seeThat(TheItems.displayed(), contains("Put out the garbage")));
    }

    @Test
    public void should_not_be_able_to_clear_completed_todos_if_none_are_complete() {

        givenThat(james).wasAbleTo(Start.withATodoListContaining("Walk the dog", "Put out the garbage"));

        then(james).should(seeThat(ClearCompletedItems.option(), is(Unavailable)));
    }
}