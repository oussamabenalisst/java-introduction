
import java.util.*;

public class table {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = 4;
        int tab[] = new int[n];
        for (int i = 0; i < n; i++) {
            System.out.printf("Donne tab[%d] : ", i);
            tab[i] = sc.nextInt();
        }
        for (int i = 0; i < n; i++) {
            System.out.print(tab[i] + "|");
        }

    }
}
