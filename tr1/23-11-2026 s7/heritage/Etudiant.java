final public class Etudiant extends Personne {
    float Moyenne;

    Etudiant(String nom, int age, float moyenne) {
        super(nom, age);
        this.Moyenne = moyenne;

    }

    public void moy(float n1, float n2) {
        Moyenne = (n1 + n2) / 2;
    }

    public void affiche() {
        super.affiche();
        System.out.println(
                ", moy: " + Moyenne);
    }
}
