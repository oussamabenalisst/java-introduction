
public class Ex1 {
    public static void main(String[] args) {
        Point p = new Point(3, 4);
        Point q = new Point();
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
        Point ip = p.symetrie();
        Point iq = q.symetrie();
        System.out.println("----------------------");
        System.out.print("p = ");
        p.affiche();
        System.out.print("\nsymetrie = ");
        ip.affiche();
        System.out.println("----------------------");
        System.out.print("q = ");
        q.affiche();
        System.out.print("\nsymetrie = ");
        iq.affiche();
        System.out.println();
    }
}
