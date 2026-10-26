
public class Ex1 {
    public static void main(String[] args) {
        Point p = new Point();
        Point q = new Point();
        p.Point(5, 7);
        p.affiche();
        if (p.origine()) {
            System.out.println("le point et origine");
        } else {
            System.out.println("le point et no origine");
        }
        q.set(p);
        q.affiche();
        if (p.egale(q)) {
            System.out.println("sont egale ");

        } else {
            System.out.println("non egale ");
        }
    }
}
