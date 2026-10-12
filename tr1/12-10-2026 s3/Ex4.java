
import java.util.Scanner;

public class Ex4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N;
        do {
            System.out.print("Donne taile de table : ");
            N = sc.nextInt();

        } while (N < 0 && N > 10);
        int T[] = new int[N];
        for (int i = 0; i < N; i++) {
            System.out.println("Done T[" + i + "]:");
            T[i] = sc.nextInt();
        }
        int s = 0;
        for (int i = 0; i < N; i++) {
            s += T[i];
            System.out.print(T[i] + "|");
        }
        System.out.println("la somme et " + s);
    }
}