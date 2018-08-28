import domain.Todo;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.naming.CannotProceedException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TodoTest {

    private static final Logger log = LoggerFactory.getLogger(TodoTest.class);


    @Test
    public void addReference() throws CannotProceedException {
        Todo todo = Todo.of("집안일",1);
        Todo todo2 = Todo.of("리팩토링",2);
        todo.addReference(todo2);
        assertThat(todo.canComplete(), is(false));
    }

    @Test
    public void update() {
        Todo todo = Todo.of("집안일",1);
        todo.update("설거지");
        assertThat(todo.getWork(), is("설거지"));
    }


    @Test
    public void completeFail2() throws CannotProceedException {
        Todo todo = Todo.of("집안일",1);
        Todo todo2 = Todo.of("빨래",2);
        todo.addReference(todo2);
        assertThat(todo.canComplete(), is(false));
    }

    @Test
    public void complete() throws CannotProceedException {
        Todo todo = Todo.of("집안일",1);
        Todo todo2 = Todo.of("빨래",2);
        todo.addReference(todo2);
        todo2.complete();
        assertThat(todo.canComplete(), is(true));
    }


}
