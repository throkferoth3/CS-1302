public class Test {
    public static int count = 0;
    public static void main(String[] args) {
        int custom = 5;

        int[] x = {1, 2, 3, 4, 5};

        for(int i = 1; i <= x.length; i *= 2) {
            for(int j = 0; j < x.length; j++) {
                // System.out.println(j);
                count += 1;
            } // for
        } // for

        System.out.println("final: " + count);
    }

}
