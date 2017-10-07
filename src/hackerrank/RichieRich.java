package hackerrank;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RichieRich {
    static String richieRich(String s, int n, int k) {

        if (s == null || s.length() == 0) return "";

        char[] arr = s.toCharArray();

        // n <= k, return all 9s
        if (n <= k) {
            for (int i = 0; i < n; i++)
                arr[i] = '9';
            return new String(arr);
        }

        // use minimal steps first
        int count = 0;
        boolean[] changed = new boolean[n];
        for (int i = 0; i < n >> 1; i++) {
            if (arr[i] != arr[n - 1 - i]) {

                if (arr[i] > arr[n - 1 - i]) arr[n - 1 - i] = arr[i];
                else arr[i] = arr[n - 1 - i];

                changed[i] = changed[n - 1 - i] = true;
                count++;
            }
        }

        // impossible
        if (count > k) return "-1";

        // further optimize
        k -= count;
        for (int i = 0; i < n >> 1; i++) {
            if (arr[i] == '9') continue;

            if ((changed[i] && k >= 1) || (!changed[i] && k >= 2)) {
                arr[i] = arr[n - 1 - i] = '9';
                k -= changed[i] ? 1 : 2;
            }
        }

        if (k > 0 && (n & 1) == 1) arr[n >> 1] = '9';

        return new String(arr);
    }

    public static void main(String[] args) throws FileNotFoundException {
        // Scanner in = new Scanner(System.in);
        Scanner in = new Scanner(new File("input/RichieRich.txt"));
        int n = in.nextInt();
        int k = in.nextInt();
        String s = in.next();
        in.close();

        String result = richieRich(s, n, k);
        System.out.println(result);
    }
}
