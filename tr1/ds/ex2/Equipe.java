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

    public int getNumJoueur() {
        return nbJoueurs;
    }

    public void setJoueur(int nbJoueurs) {
        this.nbJoueurs = nbJoueurs;
    }

    public String toString() {
        return ("|" + numEquipe + "|" + nomEquipe + " avec " + nbJoueurs + " joueurs." + "| All " + nomberEquipe);
    }

}
