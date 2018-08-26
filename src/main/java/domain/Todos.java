package domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.CannotProceedException;
import java.util.ArrayList;
import java.util.List;

public class Todos {

    private static final Logger log = LoggerFactory.getLogger(Todos.class);
    private List<Todo> todos;

    public Todos() {
        todos = new ArrayList();
    }

    public Todo add(String work) {
        Todo todo = Todo.of(work,todos.size() + 1);
        todos.add(todo);
        return todo;
    }

    public void add(Todo todo) {
        todos.add(todo);
    }

    public void update(int id, String work) {
        todos.get(id - 1).update(work);
    }

    public Todo search(int id) {
        if (todos.size() < id) {
            throw new IndexOutOfBoundsException("없는 할일이다.");
        }
        return todos.get(id - 1);
    }

    public void addReference(int mainId, int subId) throws CannotProceedException {
        Todo todo = search(mainId);
        todo.addReference(search(subId));
    }

    public void complete(int id) throws CannotProceedException {
        Todo todo = search(id);
        log.debug("Todos complete beforeCheck {}", todo.toString());
        if (!todo.canComplete()) {
            throw new CannotProceedException("참조걸린 녀석이있다.");
        }
        todo.complete();
        log.debug("Todos complete afterCheck {}", todo.toString());
    }

    public int size() {
        return todos.size();
    }

    public boolean allComplete() {
        for (Todo todo : todos) {
            if (!todo.isComplete()) {
                return false;
            }
        }
        return true;
    }

    public boolean existReference(Todo todo) {
        if (todos.contains(todo)) {
            return true;
        }
        return false;
    }

}
