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
import todomvc.questions.CurrentFilter;
import todomvc.questions.TheItems;
import todomvc.tasks.CompleteItem;
import todomvc.tasks.FilterItems;
import todomvc.tasks.Start;

import static net.serenitybdd.screenplay.GivenWhenThen.*;
import static org.hamcrest.Matchers.*;
import static todomvc.model.TodoStatusFilter.*;

@RunWith(SerenityRunner.class)
@WithTags({
        @WithTag("Screenplay pattern"),
        @WithTag("version:RELEASE-2"),
})
public class FilteringTodos {

    private Actor james = Actor.named("James");
    @Managed
    private WebDriver hisBrowser;
    @Before public void jamesCanBrowseTheWeb() {
        james.can(BrowseTheWeb.with(hisBrowser));
    }

    @Test
    public void should_be_able_to_view_only_completed_todos() {

        givenThat(james).wasAbleTo(Start.withATodoListContaining("Walk the dog", "Put out the garbage"));

        when(james).attemptsTo(
            CompleteItem.called("Walk the dog"),
            FilterItems.toShow(Completed)
        );

        then(james).should(seeThat(TheItems.displayed(), contains("Walk the dog")));
        and(james).should(seeThat(TheItems.displayed(), not(contains("Put out the garbage"))));
        and(james).should(seeThat(CurrentFilter.selected(), is(Completed)));
    }

    @Test
    public void should_be_able_to_view_only_incomplete_todos() {

        givenThat(james).wasAbleTo(Start.withATodoListContaining("Walk the dog", "Put out the garbage"));

        when(james).attemptsTo(
            CompleteItem.called("Walk the dog"),
            FilterItems.toShow(Active)
        );

        then(james).should(seeThat(TheItems.displayed(), contains("Put out the garbage")));
        and(james).should(seeThat(TheItems.displayed(), not(contains("Walk the dog"))));
        and(james).should(seeThat(CurrentFilter.selected(), is(Active)));
    }

    @Test
    public void should_be_able_to_view_both_complete_and_incomplete_todos() {

        givenThat(james).wasAbleTo(Start.withATodoListContaining("Walk the dog", "Put out the garbage"));

        when(james).attemptsTo(
            CompleteItem.called("Walk the dog"),
            FilterItems.toShow(Active),
            FilterItems.toShow(All)
        );

        then(james).should(seeThat(TheItems.displayed(), contains("Walk the dog", "Put out the garbage")));
        and(james).should(seeThat(CurrentFilter.selected(), is(All)));
    }
}