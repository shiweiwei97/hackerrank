package hackerrank;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/***
 * https://www.hackerrank.com/challenges/big-sorting
 * 
 * @author weiwei
 *
 */
public class BigSorting {

    public static void main(String[] args) throws FileNotFoundException {
        // Scanner in = new Scanner(System.in);
        Scanner in = new Scanner(new File("input/BigSorting.txt"));

        int n = in.nextInt();
        String[] unsorted = new String[n];
        for (int unsorted_i = 0; unsorted_i < n; unsorted_i++) {
            unsorted[unsorted_i] = in.next();
        }

        in.close();

        Arrays.sort(unsorted, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() != o2.length()) return Integer.compare(o1.length(), o2.length());
                return o1.compareTo(o2);
            }
        });

        for (String s : unsorted)
            System.out.println(s);
    }

}
