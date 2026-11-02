
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
            comptes[i] = (int) Math.random() * 1000 + 1;
        }
    }

    public void diviserParDeux() {
        for (int i = 0; i < this.comptes.length; i++) {
            this.comptes[i] = (double) this.comptes[i] / 2;
        }
    }

    public String toString() {
        return ("nom : " + this.nom +
                "\nage : " + this.age +
                "\ncompte 1 :" + this.comptes[0] +
                "\ncompte 2 :" + this.comptes[1] +
                "\ncompte 3 :" + this.comptes[2]);
    }

}
