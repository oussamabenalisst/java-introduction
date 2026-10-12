enum jour {
    lundi, mardi, mercredi, jeudi, vendredi
}

public class testenum {
    public static void main(String[] args) {
        jour[] TousLesJours = jour.values();
        for (jour j : TousLesJours) {
            System.out.println(j);
        }
    }
}