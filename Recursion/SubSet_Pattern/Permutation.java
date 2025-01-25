package Recursion.SubSet_Pattern;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Permutation {
    public static void main(String[] args) {
        int[] arr = { 1, 2, 2 };
        // findPermutations(new ArrayList<>(), arr);
        Arrays.sort(arr);
        System.out.println(backTrackPermutation(new ArrayList<>(), arr, new boolean[arr.length], 5));

    }

    static void findPermutations(List<Integer> p, int[] arr) {
        if (p.size() == arr.length) {
            System.out.println(p);
            return;
        }
        for (int i = 0; i <= p.size(); i++) {
            List<Integer> temp = new ArrayList<>(p);
            temp.add(i, arr[p.size()]);
            findPermutations(temp, arr);
        }
    }

    // for duplicates we need backtracking
    static List<List<Integer>> findPermutationsList(List<Integer> p, List<Integer> up) {
        if (up.isEmpty()) {
            List<List<Integer>> res = new ArrayList<>();
            res.add(new ArrayList<>(p));
            return res;
        }
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < up.size(); i++) {
            if (i > 0 && up.get(i).equals(up.get(i - 1)))
                continue;
            int current = up.get(i);
            p.add(current);
            List<Integer> newUp = new ArrayList<>(up);
            newUp.remove(i);
            result.addAll(findPermutationsList(p, newUp));
            p.remove(p.size() - 1);
        }

        return result;
    }

    static List<List<Integer>> backTrackPermutation(List<Integer> p, int[] up, boolean[] used, int k) {
        if (p.size() == up.length) {
            List<List<Integer>> res = new ArrayList<>();
            // int sum = p.stream().mapToInt(Integer::intValue).sum();
            // // if(sum==k)
            res.add(new ArrayList<>(p));
            return res;
        }
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < up.length; i++) {
            System.out.println(i);
            if (used[i])
                continue;
            if (i > 0 && up[i] == up[i - 1] && !used[i - 1])
                continue;
            p.add(up[i]);
            used[i] = true;
            result.addAll(backTrackPermutation(p, up, used, k));
            p.remove(p.size() - 1);
            used[i] = false;
        }
        return result;
    }
}
