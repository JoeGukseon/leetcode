class Solution {
    public boolean wordPattern(String pattern, String s) {
        String[] sArr = s.split(" ");
        if (pattern.length() != sArr.length) {
            return false;
        }
        Map<Character, String> map = new HashMap<>();
        for (int i = 0; i < sArr.length; i++) {
            char ch = pattern.charAt(i);
            if (!map.containsKey(ch)) {
                if (map.containsValue(sArr[i])) return false;
                map.put(ch, sArr[i]);
            } else {
                if (!map.get(ch).equals(sArr[i])) {
                    return false;
                }
            }
        }
        return true;
    }
}