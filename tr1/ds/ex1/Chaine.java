package tr1.ds.ex1;

import java.util.*;

public class Chaine {
    String ch;
    public void SaisireChaine() {
        Scanner sc = new Scanner(System.in);
        ch = sc.nextLine();
    }

    public void AfficheChaine(String ch) {
        StringTokenizer st = new StringTokenizer(ch, "/s+");
        while (st.hasMoreTokens())
            System.out.println(st.nextToken());

    }



}
