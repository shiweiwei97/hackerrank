package hackerrank;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Host {

    static String[] paginate(int num, String[] results) {

        int len = results.length;
        if (results == null || len <= num) return results;

        boolean[] used = new boolean[len];
        List<String> res = new ArrayList<>();

        int start = 0;
        while (start < len) {

            List<String> curPage = new ArrayList<>();
            Set<Integer> hostIds = new HashSet<>();

            // first pass, try to find unique host
            for (int i = start; curPage.size() < num && i < len; i++) {
                int hostId = Integer.parseInt(results[i].split(",")[0]);
                if (!hostIds.contains(hostId) && !used[i]) {
                    start = pick(i, start, results[i], used, curPage);
                    hostIds.add(hostId);
                }
            }

            // second pass, cannot avoid same host, fill the page with what I have
            for (int i = start; curPage.size() < num && i < len; i++)
                if (!used[i]) start = pick(i, start, results[i], used, curPage);

            res.addAll(curPage);
            if (start < len) res.add("");
        }

        return res.toArray(new String[] {});
    }

    // pick i-th item and return the new start position
    private static int pick(int i, int start, String result, boolean[] used, List<String> curPage) {
        used[i] = true;
        curPage.add(result);

        // picked current start, need to move it to next unused
        if (i == start) {
            while (i < used.length && used[i])
                i++;
            return i;
        }
        return start;
    }

    public static void main(String[] args) throws IOException {
        // Scanner in = new Scanner(System.in);
        Scanner in = new Scanner(new File("input/Host.txt"));
        final String fileName = System.getenv("OUTPUT_PATH");
        BufferedWriter bw = null;
        if (fileName != null) {
            bw = new BufferedWriter(new FileWriter(fileName));
        } else {
            bw = new BufferedWriter(new OutputStreamWriter(System.out));
        }

        String[] res;
        int num;
        num = Integer.parseInt(in.nextLine().trim());

        int results_size = 0;
        results_size = Integer.parseInt(in.nextLine().trim());

        String[] results = new String[results_size];
        for (int i = 0; i < results_size; i++) {
            String results_item;
            try {
                results_item = in.nextLine();
            } catch (Exception e) {
                results_item = null;
            }
            results[i] = results_item;
        }

        in.close();

        res = paginate(num, results);
        for (int res_i = 0; res_i < res.length; res_i++) {
            bw.write(String.valueOf(res[res_i]));
            bw.newLine();
        }

        bw.close();
    }

}
