
public class ex4 {
    public static void main(String[] args) {
        double[] c = new double[3];
        c[0] = 15.2;
        c[1] = 135.2;
        c[2] = 995.2;
        Personne p = new Personne("oussama", 22, c);
        Personne p2 = new Personne("benali", 2);
        System.out.println(p.toString());
        System.out.println(p2.toString());
    }
}
