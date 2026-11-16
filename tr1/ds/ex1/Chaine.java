import java.util.*;

public class Chaine {
    String ch;

    public void SaisireChaine() {
        Scanner sc = new Scanner(System.in);
        ch = sc.nextLine();
    }

    public void AfficheChaine(String ch) {
        StringTokenizer st = new StringTokenizer(ch, " ");
        while (st.hasMoreTokens())
            System.out.println(st.nextToken());

    }

    public int nomberOccurence(String ch, String mot) {

        int count = 0;
        StringTokenizer st = new StringTokenizer(ch, " ");
        while (st.hasMoreTokens()) {
            if (st.nextToken().equals(mot)) {
                count++;
            }
        }
        return count;
    }

    public String[] extractMots(String ch) {
        return ch.split("\s+");
    }

    public static void main(String[] args) {
        Chaine c = new Chaine();
        c.SaisireChaine();
        c.AfficheChaine(c.ch);
        System.out.println("le nombre d'occurence de 'java' est : " + c.nomberOccurence(c.ch, "java"));
        String[] mots = c.extractMots(c.ch);
        System.out.println("les mots sont : ");
        for (String mot : mots) {
            System.out.print("|" + mot);
        }
        System.out.println();
    }

}
