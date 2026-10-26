
public class test {
    public static void main(String[] args) {
        Personnes p = new Personnes("oussama", "benali", 22, "iteam");
        Personnes p1 = new Personnes("test", "bentest", 22, "ISAMM");
        System.out.println((Personnes.moy(15, 5)));
        p.afficher();
        p1.afficher();
    }
}
