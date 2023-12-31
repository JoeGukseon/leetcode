class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(result, "", 0, 0, n);
        return result;
    }

    private static void backtrack(List<String> result, String s, int left, int right, int n) {
        if (s.length() == 2 * n) {
            result.add(s);
            return;
        }

        if (left < n) {
            backtrack(result, s + '(', left + 1, right, n);
        }

        if (right < left) {
            backtrack(result, s + ')', left, right + 1, n);
        }
    }
}