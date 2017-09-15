package todomvc.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;
import net.serenitybdd.screenplay.questions.Visibility;
import todomvc.user_interface.TodoList;

import static net.serenitybdd.screenplay.questions.ValueOf.the;

@Subject("the 'Clear Completed' option")
public class ClearCompletedItems implements Question<ElementAvailability> {

    public static ClearCompletedItems option() {
        return new ClearCompletedItems();
    }

    @Override
    public ElementAvailability answeredBy(Actor actor) {
        return ElementAvailability.from(
                the(Visibility.of(TodoList.CLEAR_COMPLETED).viewedBy(actor))
        );
    }
}