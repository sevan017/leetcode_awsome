package daily_question._2026._0531;

public class Rand10UsingRand7 {
//    class Solution extends SolBase {
//        public int rand10() {
//            // rand10 (int) (Math.random) * 10 + 1
//            // rand10 (int) (Math.random) * 7 + 1
//        }
//    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            System.out.print((int) (Math.random() * 10) + 1 + " ");
        }

        System.out.println();
//        for (int i = 0; i < 20; i++) {
//            System.out.print((int) (Math.random() * 7) + 1 + " ");
//        }

    }

    int rand10() {
        return rand7() * 1000000 / 7;
    }

    int rand7() {
        return (int) (Math.random() * 7) + 1;
    }
}
