package domain;

import javax.naming.CannotProceedException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Todo {


    private Long id;

    private String work;

    private String createDate;

    private String modifiedDate;

    private Todos references;

    private Boolean complete;

    public Todo() {
    }

    public Todo(String work, int id) {
        this.work = work;
        this.id = (long) id;
        this.createDate = currentTime();
        references = new Todos();
        complete = false;
    }

    public static Todo of(String work, int id) {
        return new Todo(work, id);
    }


    public int getId() {
        return Integer.valueOf(id.intValue());
    }

    public String getWork() {
        return work;
    }

    public String getCreateDate() {
        return createDate;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }


    public Boolean isComplete() {
        return complete;
    }

    public Todos references() {
        return references;
    }


    public void update(String work) {
        this.work = work;
        this.modifiedDate = currentTime();
    }

    public String currentTime() {
        return new SimpleDateFormat("yyyy/MM/dd hh:mm:ss").format(new Date());
    }

    public void addReference(Todo todo) throws CannotProceedException {

        if (references.existReference(todo)) {
            throw new CannotProceedException("이미 추가된 선행 할일입니다.");
        }

        references.add(todo);
    }

    public boolean canComplete() {
        if (references.allComplete() || references.size() == 0) {
            return true;
        }
        return false;
    }

    public void complete() throws CannotProceedException {
        if (this.complete) {
            throw new CannotProceedException("이미 완료된 일입니다.");
        }
        this.complete = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Todo todo = (Todo) o;

        if (id != null ? !id.equals(todo.id) : todo.id != null) return false;
        if (work != null ? !work.equals(todo.work) : todo.work != null) return false;
        if (createDate != null ? !createDate.equals(todo.createDate) : todo.createDate != null) return false;
        if (modifiedDate != null ? !modifiedDate.equals(todo.modifiedDate) : todo.modifiedDate != null) return false;
        if (references != null ? !references.equals(todo.references) : todo.references != null) return false;
        return complete != null ? complete.equals(todo.complete) : todo.complete == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (work != null ? work.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (modifiedDate != null ? modifiedDate.hashCode() : 0);
        result = 31 * result + (references != null ? references.hashCode() : 0);
        result = 31 * result + (complete != null ? complete.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", work='" + work + '\'' +
                ", createDate='" + createDate + '\'' +
                ", modifiedDate='" + modifiedDate + '\'' +
                ", references=" + references +
                ", complete=" + complete +
                '}';
    }

}
