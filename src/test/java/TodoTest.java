import domain.Todo;
import domain.Todos;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.naming.CannotProceedException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TodoTest {

    private static final Logger log = LoggerFactory.getLogger(TodoTest.class);
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
        todos.add("빨래");
        todos.addReference(1,2);
    }

    @Test
    public void update() throws CannotProceedException {
        add();
        todos.update(1,"설거지");
        assertThat(todos.search(1).getWork(), is("설거지"));
    }


    @Test
    public void search() throws CannotProceedException {
        add();
        assertThat(todos.search(1), is(todos.search(1)));
        log.debug(todos.search(1).toString());
        todos.update(1,"코딩");
        log.debug(todos.search(1).toString());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void completeFail1() throws CannotProceedException {
        todos.complete(1);
    }

    @Test(expected = CannotProceedException.class)
    public void completeFail2() throws CannotProceedException {
        todos.add("집안일");
        todos.add("빨래");
        todos.addReference(1,2);
        todos.complete(1);
        todos.complete(2);
    }

    @Test(expected = CannotProceedException.class)
    public void completeFail3() throws CannotProceedException {
        todos.add("집안일");
        todos.add("빨래");
        todos.add("설거지");
        todos.addReference(1,2);
        todos.addReference(1,3);
        todos.addReference(2,3);
        todos.complete(2);
        todos.complete(1);
        todos.complete(3);
    }

    @Test
    public void complete() throws CannotProceedException {
        todos.add("집안일");
        todos.add("빨래");
        todos.addReference(1,2);
        todos.complete(2);
        todos.complete(1);
    }


    @Test
    public void complete2() throws CannotProceedException {
        todos.add("집안일");
        todos.add("빨래");
        todos.add("설거지");
        todos.addReference(1,2);
        todos.addReference(1,3);
        todos.addReference(2,3);
        todos.complete(3);
        todos.complete(2);
        todos.complete(1);
    }


}
