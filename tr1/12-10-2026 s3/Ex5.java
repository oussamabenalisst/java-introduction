import java.util.Scanner;

public class Ex5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N;
        do {
            System.out.print("Donne la taile de table : ");
            N = sc.nextInt();
        } while (N < 0 || N > 50);
        int T[] = new int[N];
        int TPOS[] = new int[N];
        int TNEG[] = new int[N];
        int j = 0;
        int k = 0;
        for (int i = 0; i < N; i++) {
            System.out.print("donne T[" + i + "] : ");
            T[i] = sc.nextInt();
            if (T[i] >= 0) {
                TPOS[j] = T[i];
                j++;
            } else {

                TNEG[k] = T[i];
                k++;
            }
        }
        System.out.print("T=");
        for (int i : T) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.print("TPOS=");
        for (int i = 0; i < j; i++) {
            System.out.print(TPOS[i] + " ");
        }
        System.out.println();
        System.out.print("TNEG=");
        for (int i = 0; i < k; i++) {
            System.out.print(TNEG[i] + " ");
        }
        System.out.println();

    }
}
