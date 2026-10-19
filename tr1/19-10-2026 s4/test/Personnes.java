/**
 * Personnes
 */
public class Personnes {
    private String nom, prenom;
    static String ecole;
    private int age;

    public Personnes(String n, String p, int a, String e) {
        nom = n;
        prenom = p;
        age = a;
        ecole = e;
    }

    public void afficher() {
        System.out.println(
                "nom :" + nom +
                        " prenom : " + prenom +
                        " age : " + age +
                        " ecole : " + ecole);
    }

}