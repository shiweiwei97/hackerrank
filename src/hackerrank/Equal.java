package hackerrank;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

/***
 * https://www.hackerrank.com/challenges/equal/problem
 * 
 * @author weiwei
 *
 */
public class Equal {

    private final static int[] table = new int[] { 0, 1, 1, 2, 2 };

    public static void main(String[] args) throws Exception {
        // Scanner in = new Scanner(System.in);
        Scanner in = new Scanner(new File("input/Equal.txt"));

        int T = in.nextInt();

        for (int i = 0; i < T; i++) {
            in.nextLine();
            int N = in.nextInt();

            in.nextLine();
            int[] arr = new int[N];
            for (int j = 0; j < N; j++) {
                arr[j] = in.nextInt();
            }

            System.out.println(solve(arr, N));
        }

        in.close();
    }

    private static int solve(int[] arr, int n) {

        System.out.println(Arrays.toString(arr));

        if (n <= 1 || arr.length <= 1) return 0;

        int min = Integer.MAX_VALUE;
        for (int val : arr)
            min = Math.min(min, val);

        int res = Integer.MAX_VALUE;
        for (int target = 0; target < 5; target++)
            res = Math.min(res, minStep(arr, n, min - target));
        return res;
    }

    private static int minStep(int[] arr, int n, int target) {

        int steps = 0;
        for (int val : arr)
            steps += getStep(val, target);

        return steps;
    }

    private static int getStep(int val, int target) {
        int diff = val - target;
        return (diff / 5) + table[diff % 5];
    }
}
