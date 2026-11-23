public class Personne {
    String nom;
    int age;

    Personne(String nom, int age) {
        this.nom = nom;
        this.age = age;
    }

    public void affiche() {
        System.out.print(
                "Nom: " + nom + ", Age: " + age);
    }
}
