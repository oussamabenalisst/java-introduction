import java.util.StringTokenizer;

public class teststring {

    public static void main(String[] args) {
        String ch = "Hello,World!";
        StringTokenizer st = new StringTokenizer(ch, ",");
        while (st.hasMoreTokens()) {
            System.out.println(st.nextToken());
        }
    }

}
