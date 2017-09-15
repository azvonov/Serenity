package todomvc.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;
import net.serenitybdd.screenplay.questions.Attribute;
import todomvc.user_interface.TodoList;

@Subject("the ToDo placeholder text")
public class Placeholder implements Question<String> {

    public static Placeholder text() {
        return new Placeholder();
    }

    @Override
    public String answeredBy(Actor actor) {
        return Attribute.of(TodoList.WHAT_NEEDS_TO_BE_DONE).named("placeholder")
                .viewedBy(actor)
                .asString();
    }
}