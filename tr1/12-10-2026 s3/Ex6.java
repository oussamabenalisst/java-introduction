import java.util.Scanner;

public class Ex6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int L, C;
        do {
            System.out.print("Donne nomber de ligines : ");
            L = sc.nextInt();
        } while ((L < 1) || (L > 50));
        do {
            System.out.print("Donne nomber de C : ");
            C = sc.nextInt();
        } while ((C < 1) || (C > 50));
        int[][] tab = new int[L][C];
        int s = 0;
        for (int i = 0; i < L; i++) {
            for (int j = 0; j < C; j++) {
                System.out.print("Donne TAB[" + i + "," + j + "] : ");
                tab[i][j] = sc.nextInt();
                s += tab[i][j];
            }
        }
        for (int i = 0; i < L; i++) {
            for (int j = 0; j < C; j++) {
                System.out.print(tab[i][j] + "|");
            }
        }
        System.out.println();
        System.out.println("somme = " + s);

    }
}
