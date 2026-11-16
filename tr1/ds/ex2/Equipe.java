package tr1.ds.ex2;

public class Equipe {
    private int numEquipe, nbJoueurs;
    private static int nomberEquipe;
    private String nomEquipe;

    Equipe(String nomEquipe) {
        this.nomEquipe = nomEquipe;
        nbJoueurs = 0;
        this.nomberEquipe++;

    }

}
