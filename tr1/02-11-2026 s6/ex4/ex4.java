
public class ex4 {
    public static void main(String[] args) {
        float[] c = new float[3];
        c[0] = 15.2f;
        c[1] = 135.2f;
        c[2] = 995.2f;
        Personne p1 = new Personne("oussama", 22, c);
        Personne p2 = new Personne("benali", 2);
        System.out.println("---------------p1----------------");
        System.out.println(p1.toString());
        System.out.println("---------------p2----------------");
        System.out.println(p2.toString());
    }
}
