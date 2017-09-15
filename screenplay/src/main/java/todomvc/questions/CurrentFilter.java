package todomvc.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;
import net.serenitybdd.screenplay.questions.Text;
import todomvc.model.TodoStatusFilter;
import todomvc.user_interface.TodoList;

@Subject("the displayed todo items")
public class CurrentFilter implements Question<TodoStatusFilter> {

    public static CurrentFilter selected() {
        return new CurrentFilter();
    }

    @Override
    public TodoStatusFilter answeredBy(Actor actor) {
        return Text.of(TodoList.SELECTED_FILTER)
                .viewedBy(actor)
                .asEnum(TodoStatusFilter.class);
    }
}