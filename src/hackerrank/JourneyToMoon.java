package hackerrank;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

/***
 * https://www.hackerrank.com/challenges/journey-to-the-moon
 * 
 * @author weiwei
 *
 */
public class JourneyToMoon {

    public static void main(String[] args) throws FileNotFoundException {
        // Scanner in = new Scanner(System.in);
        Scanner in = new Scanner(new File("input/JourneyToMoon.txt"));

        int n = in.nextInt(), p = in.nextInt();
        int[][] arr = new int[p][2];

        for (int i = 0; i < p; i++) {
            in.nextLine();
            arr[i][0] = in.nextInt();
            arr[i][1] = in.nextInt();
        }

        in.close();
        System.out.println(solve(arr, n, p));
    }

    private static long solve(int[][] arr, int n, int p) {

        Map<Integer, Set<Integer>> map = new HashMap<>();

        for (int i = 0; i < p; i++) {
            int a = arr[i][0], b = arr[i][1];
            insertMap(map, a, b);
            insertMap(map, b, a);
        }

        List<Integer> sizes = new ArrayList<>();
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            int size = 0;

            Stack<Integer> stack = new Stack<>();
            stack.push(i);

            while (!stack.isEmpty()) {
                int val = stack.pop();
                if (!visited[val]) {
                    size++;
                    visited[val] = true;
                    if (map.containsKey(val)) stack.addAll(map.get(val));
                }
            }

            if (size > 0) sizes.add(size);
        }

        return calculatePairs(n, sizes);
    }

    private static void insertMap(Map<Integer, Set<Integer>> map, int a, int b) {
        if (map.containsKey(a)) {
            Set<Integer> set = map.get(a);
            set.add(b);
            map.put(a, set);
        } else {
            Set<Integer> set = new HashSet<>();
            set.add(b);
            map.put(a, set);
        }
    }

    private static long calculatePairs(int n, List<Integer> sizes) {
        long res = 0L;
        long left = n;

        for (int size : sizes) {
            left -= size;
            res += size * left;
        }

        return res;
    }
}