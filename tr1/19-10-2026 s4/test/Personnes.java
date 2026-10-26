/**
 * Personnes
 */
public class Personnes {
    private String nom, prenom, ecole;
    private int age;

    public Personnes(String n, String p, int a, String e) {
        this.nom = n;
        this.prenom = p;
        this.age = a;
        this.ecole = e;
    }

    public static float moy(int note1, int note2) {
        return (float) ((note1 + note2) / 2);
    }

    public void afficher() {
        System.out.println("nom :" + nom + " prenom : " + prenom + " age : " + age + " ecole : " + ecole);
    }

}