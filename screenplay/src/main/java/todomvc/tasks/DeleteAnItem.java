package todomvc.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.thucydides.core.annotations.Step;
import todomvc.actions.JSClick;
import todomvc.user_interface.TodoListItem;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class DeleteAnItem implements Task {

    private final String itemName;

    public DeleteAnItem(String itemName) { this.itemName = itemName; }

    public static DeleteAnItem called(String itemName) {
        return instrumented(DeleteAnItem.class, itemName);
    }

    @Step("{0} deletes the item '#itemName'")
    public <T extends Actor> void performAs(T theActor) {
        theActor.attemptsTo(JSClick.on(TodoListItem.DELETE_ITEM.of(itemName)));
    }
}