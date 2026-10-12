
import java.util.Scanner;

public class Ex3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int number = sc.nextInt();
        System.out.println("---------");
        if (number == 0) {
            System.out.print("The number is zero");
        } else {
            if (number > 0) {
                System.out.print("The number is positive");
            } else {
                System.out.print("The number is negative");
            }
        }
        if (number % 2 == 0) {
            System.out.println(" et paire");
        } else
            System.out.println(" et impair");
    }
}
