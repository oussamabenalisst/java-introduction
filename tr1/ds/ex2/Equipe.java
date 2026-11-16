public class Equipe {
    private int numEquipe, nbJoueurs;
    private static int nomberEquipe;
    private String nomEquipe;

    Equipe(String nomEquipe) {
        this.nomEquipe = nomEquipe;
        nbJoueurs = 0;
        nomberEquipe++;
        numEquipe = nomberEquipe;
    }

    public String toString() {
        return ("|" + numEquipe + "|" + nomEquipe + " avec " + nbJoueurs + " joueurs.");
    }

}
