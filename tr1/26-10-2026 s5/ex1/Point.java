
public class Point {
    int abscisse, ordonnee;

    Point() {
        abscisse = 0;
        ordonnee = 0;
    }

    Point(int u, int v) {
        abscisse = u;
        ordonnee = v;
    }

    public Point symetrie() {
        return new Point(-abscisse, -ordonnee);
    }

    public void set(Point p) {
        abscisse = p.abscisse;
        ordonnee = p.ordonnee;
    }

    public void translate(int u, int v) {
        abscisse = abscisse + u;
        ordonnee = ordonnee + v;
    }

    public void affiche() {
        System.out.println("abscisse = " + abscisse + " ordonne = " + ordonnee);
    }

    public boolean egale(Point q) {
        return (this.abscisse == q.abscisse && this.ordonnee == q.ordonnee);
    }

    public boolean origine() {
        return (abscisse == 0 && ordonnee == 0);
    }

}
