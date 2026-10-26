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

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setEcole(String ecole) {
        this.ecole = ecole;
    }

    public String getEcole() {
        return ecole;
    }

    public static float moy(int note1, int note2) {
        return (float) ((note1 + note2) / 2);
    }

    public void afficher() {
        System.out.println("nom :" + nom + " prenom : " + prenom + " age : " + age + " ecole : " + ecole);
    }

}