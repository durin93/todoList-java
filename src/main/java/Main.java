import domain.Printer;
import domain.Todos;

import javax.naming.CannotProceedException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Todos todos = new Todos();
        Scanner sc = new Scanner(System.in);

        for (; ; ) {
            Printer.showList();
            switch (sc.nextInt()) {

                case 1:
                    System.out.println("할일을 입력해주세요");
                    todos.add(sc.next());
                    System.out.println(todos.search(1).toString());
                    break;
                case 2:
                    System.out.println("참조할 id를 입력해주세요 ,로 두개");
                    List<String> numbers = Arrays.asList(sc.next().split(","));
                    try {
                        todos.addReference(Integer.parseInt(numbers.get(0)), Integer.parseInt(numbers.get(1)));
                    } catch (IndexOutOfBoundsException | CannotProceedException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    System.out.println("수정할 id와 할일을 입력해주세요.");
                    try {
                        todos.update(sc.nextInt(), sc.next());
                    } catch (IndexOutOfBoundsException | CannotProceedException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    Printer.showAll(todos);
                    break;
                case 5:
                    System.out.println("완료할 id를 입력해주세요");
                    try {
                        todos.complete(sc.nextInt());
                    } catch (IndexOutOfBoundsException | CannotProceedException e) {
                        System.out.println(e.getMessage());
                    }
            }
        }


    }
}
