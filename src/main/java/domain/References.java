package domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.CannotProceedException;
import java.util.ArrayList;
import java.util.List;

public class References {

    private static final Logger log = LoggerFactory.getLogger(References.class);
    private List<Todo> references;

    public References() {
        references = new ArrayList();
    }

    public void add(Todo todo) {
        references.add(todo);
    }

    public int size() {
        return references.size();
    }


    public boolean allComplete() {
        for (Todo todo : references) {
            if (!todo.isComplete()) {
                return false;
            }
        }
        return true;
    }

    public boolean existReference(Todo todo) {
        if (references.contains(todo)) {
            return true;
        }
        return false;
    }

    public List<Todo> references(){
        return references;
    }



}
