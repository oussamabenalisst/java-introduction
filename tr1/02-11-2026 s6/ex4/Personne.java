
public class Personne {
    private String nom;
    private int age;
    double[] comptes = new double[3];

    public Personne(String n, int a, double[] c) {
        nom = n;
        age = a;
        comptes = c;
    }

    public Personne(String n, int a) {
        nom = n;
        age = a;
        for (int i = 0; i < 3; i++) {
            comptes[i] = Math.random() * 1000 + 1;
        }
    }

    public String toString() {
        return (nom + "|" + age);
    }

}
