package hackerrank;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReducedString {

    static String super_reduced_string(String s) {

        if (s == null || s.length() < 2) return s;

        char[] arr = s.toCharArray();
        int len = arr.length, p = -1;
        char[] res = new char[len];

        for (int i = 0; i < len; i++) {
            res[++p] = arr[i];

            while (p > 0 && res[p] == res[p - 1])
                p -= 2;
        }

        return p == -1 ? "Empty String" : new String(res).substring(0, p + 1);
    }

    public static void main(String[] args) throws FileNotFoundException {
        // Scanner in = new Scanner(System.in);
        Scanner in = new Scanner(new File("input/ReducedString.txt"));
        String s = in.next();
        in.close();

        String result = super_reduced_string(s);
        System.out.println(result);
    }
}
