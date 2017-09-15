package todomvc.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.thucydides.core.annotations.Step;
import todomvc.user_interface.TodoList;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class Clear implements Task {

    public static Clear completedItems() {
        return instrumented(Clear.class);
    }

    @Step("{0} clears all the completed items")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Click.on(TodoList.CLEAR_COMPLETED));
    }
}