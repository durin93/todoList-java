import domain.Todo;
import domain.Todos;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.CannotProceedException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TodosTest {

    private static final Logger log = LoggerFactory.getLogger(TodosTest.class);
    private Todos todos;


    @Before
    public void setUp(){
        todos = new Todos();
    }

    @Test
    public void add(){
        todos.add("집안일");
        assertThat(todos.search(1).getWork(), is("집안일"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void addReferenceFail() throws CannotProceedException {
        todos.add("집안일");
        todos.addReference(1,2);
    }

    @Test
    public void addReference() throws CannotProceedException {
        todos.add("집안일");
        todos.add("리팩토링");
        todos.addReference(1,2);
        assertThat(todos.search(1).canComplete(), is(false));
    }

    @Test
    public void update() throws CannotProceedException {
        todos.add("집안일");
        todos.update(1,"설거지");
        assertThat(todos.search(1).getWork(), is("설거지"));
    }


    @Test
    public void search() throws CannotProceedException {
        todos.add("집안일");
        assertThat(todos.search(1), is(todos.search(1)));
        log.debug(todos.search(1).toString());
        todos.update(1,"코딩");
        log.debug(todos.search(1).toString());
    }


    @Test(expected = CannotProceedException.class)
    public void completeFail2() throws CannotProceedException {
        todos.add("집안일");
        todos.add("빨래");
        todos.addReference(1,2);
        todos.complete(1);
    }

    @Test
    public void complete() throws CannotProceedException {
        todos.add("집안일");
        todos.add("빨래");
        todos.addReference(1,2);
        todos.complete(2);
        todos.complete(1);
    }


}
