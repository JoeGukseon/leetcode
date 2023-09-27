class Solution {
    public boolean isSubsequence(String s, String t) {
        int tIdx = 0;
        for (int i = 0;i<s.length();i++) {
            boolean answer = false;
            for (int j = tIdx; j < t.length(); j++) {
                if (s.charAt(i) == t.charAt(j)) {
                    tIdx = j + 1;
                    answer = true;
                    break;
                }
            }
            if (!answer || t.length() < tIdx) {
                return false;
            }
        }

        return true;
    }
}