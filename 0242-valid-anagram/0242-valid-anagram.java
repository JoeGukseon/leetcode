class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        Map<Character, Integer> map = new HashMap<>();

        for (char c : t.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        for (char c : s.toCharArray()) {
            if (map.containsKey(c)) {
                int cnt = map.get(c);
                cnt--;
                if (cnt == 0) {
                    map.remove(c);
                } else {
                    map.put(c, cnt);
                }
            } else {
                return false; //s가 t에 없을때
            }
        }
        return map.isEmpty();
    }
}