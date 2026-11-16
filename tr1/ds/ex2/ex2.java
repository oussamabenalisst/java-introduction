public class ex2 {
    public static void main(String[] args) {
        Equipe e1 = new Equipe("iteam");
        e1.setJoueur(20);
        e1.setJoueur(e1.getNumJoueur() - 1);
        Equipe e2 = new Equipe("ISI");
        e2.setJoueur(18);
        e1.setJoueur(e2.getNumJoueur() + 1);
        System.out.println(e1);
        System.out.println(e2);
    }
}
