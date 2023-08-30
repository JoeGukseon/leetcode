class Solution {
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        Map<Character, Character> map = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (!map.containsKey(ch)) {
                if(map.containsValue(t.charAt(i))) return false;
                map.put(ch, t.charAt(i));
            } else {
                if (!map.get(ch).equals(t.charAt(i))) {
                    return false;
                }
            }
        }

        return true;
    }
}