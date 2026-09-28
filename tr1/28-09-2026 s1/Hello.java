class Hello {
    public static void main(String arg[]) {
        System.out.println("hello world");
        System.out.println("Bonjour " + arg[0] + " et " + arg[1]);
        System.out.printf("Bonjour %s et %s \n", arg[0], arg[1]);
        int a = 5;
        if (a > 0) {
            System.out.println("possitif");
        } else {
            if (a < 0) {
                System.out.println("negatif");
            } else {
                System.out.println("null");
            }

        }

    }

}
