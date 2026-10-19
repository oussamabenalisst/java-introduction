/**
 * Personne
 */
public class Personne {
    private String nom;
    private String prenom;
    private int age;
    private String ecole;

    public void afficher() {
        System.out.println(
                "nom :" + nom +
                        " prenom : " + prenom +
                        " age : " + age +
                        " ecole : " + ecole);
    }

}