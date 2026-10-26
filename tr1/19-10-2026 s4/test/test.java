
public class test {
    public static void main(String[] args) {
        Personnes p = new Personnes("oussama", "benali", 22, "iteam");
        Personnes p1 = new Personnes("test", "bentest", 22, "ISAMM");
        Personnes p3 = new Personnes(null, null, 0, null);
        System.out.println((Personnes.moy(15, 5)));
        p3.setNom("hamadi");
        p3.setPrenom("marzo9i");
        p3.setAge(30);
        p3.setEcole("ULG");
        System.out.printf("le %s %s la age et %d \n", p3.getNom(), p3.getPrenom(), p3.getAge());
        System.out.println(p.getAge());
        p.afficher();
        p1.afficher();
    }
}
