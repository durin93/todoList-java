package View;

import domain.References;
import domain.Todo;
import domain.Todos;

public class Printer {


    public static void showList(){
        System.out.println("1. 할일 추가");
        System.out.println("2. 참조 설정");
        System.out.println("3. 할일 수정");
        System.out.println("4. 할일 목록 조회");
        System.out.println("5. 할일 완료 처리");
    }


    public static void showAll(Todos todos) {
        System.out.println("id | 할일 | 작성일시 | 최종수정일시 | 완료처리");
        for (Todo todo:todos.todos()) {
            System.out.print(todo.getId());
            System.out.print(" | ");
            System.out.print(todo.getWork());
            showReferences(todo.references());
            System.out.print(" | ");
            System.out.print(todo.getCreateDate());
            System.out.print(" | ");
            System.out.print(todo.getModifiedDate());
            System.out.print(" | ");
            System.out.print(todo.isComplete());
            System.out.print(" | ");
            System.out.println();
        }
    }

    public static void showReferences(References references){
        for (Todo todo: references.references()) {
            System.out.print("@");
            System.out.print(todo.getId());
            System.out.print(" ");
        }
    }

}
