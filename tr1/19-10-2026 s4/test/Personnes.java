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

    public static float moy(int note1, int note2) {
        return (float) ((note1 + note2) / 2);
    }

    public void afficher() {
        System.out.println(
                "nom :" + nom +
                        " prenom : " + prenom +
                        " age : " + age +
                        " ecole : " + ecole);
    }

}