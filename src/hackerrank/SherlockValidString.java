package hackerrank;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SherlockValidString {

    static String isValid(String s) {

        if (s == null | s.length() < 5) return "YES";

        int[] counts = new int[26];
        char[] arr = s.toCharArray();
        int total = arr.length;

        for (char c : arr)
            counts[c - 'a']++;

        int letterCnt = 0, oneCnt = 0;
        for (int cnt : counts) {
            if (cnt > 0) letterCnt++;
            if (cnt == 1) oneCnt++;
        }

        if (oneCnt == 1) {
            int newTotal = total - 1;
            int newLetterCnt = letterCnt - 1;
            if (newTotal % newLetterCnt == 0) {
                boolean allEqual = true;
                for (int cnt : counts) {
                    if (cnt == 0) continue;
                    if (cnt != newTotal / newLetterCnt && cnt != 1)
                        allEqual = false;
                }

                if (allEqual) return "YES";
            }
        }

        if (total % letterCnt == 0) {
            boolean allEqual = true;
            for (int cnt : counts) {
                if (cnt == 0) continue;
                if (cnt != total / letterCnt) {
                    allEqual = false;
                    break;
                }
            }

            if (allEqual) return "YES";
        } else if (total % letterCnt == 1
                || total % letterCnt == (letterCnt - 1)) {
            int diffCnt = 0;
            for (int cnt : counts) {
                if (cnt == 0) continue;
                if (cnt != total / letterCnt) diffCnt++;
            }

            if (diffCnt == 1) return "YES";
        }

        return "NO";
    }

    public static void main(String[] args) throws FileNotFoundException {
        // Scanner in = new Scanner(System.in);
        Scanner in = new Scanner(new File("input/SherlockValidString.txt"));
        String s = in.next();
        in.close();
        String result = isValid(s);
        System.out.println(result);
    }

}
