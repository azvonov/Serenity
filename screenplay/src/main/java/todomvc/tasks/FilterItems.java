package todomvc.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.thucydides.core.annotations.Step;
import todomvc.model.TodoStatusFilter;
import todomvc.user_interface.TodoList;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class FilterItems implements Task {

    private final TodoStatusFilter filter;

    public FilterItems(TodoStatusFilter filter) { this.filter = filter; }

    public static FilterItems toShow(TodoStatusFilter status) {
        return instrumented(FilterItems.class, status);
    }

    @Step("{0} filters items by #filter")
    public <T extends Actor> void performAs(T theActor) {
        theActor.attemptsTo(
                Click.on(TodoList.FILTER.of(filter.name()).called("filter by "+ filter))
        );
    }
}