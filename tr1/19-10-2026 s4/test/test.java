
public class test {
    public static void main(String[] args) {
        Personnes p = new Personnes("oussama", "benali", 22, "iteam");
        Personnes p1 = new Personnes("test", "bentest", 22, "ISAMM");
        p.ecole = "test";
        p.afficher();
        p1.afficher();
    }
}
