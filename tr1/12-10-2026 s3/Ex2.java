import java.util.*;

public class Ex2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N;
        if (args.length > 0) {
            N = Integer.parseInt(args[0]);
        } else {
            do {
                System.out.print("Entrez un nombre : ");
                N = sc.nextInt();
            } while (N < 0);
        }
        int F = 1;
        for (int i = 1; i <= N; i++)
            F *= i;

        System.out.println("Factorielle de " + N + " est : " + F);

    }
}
