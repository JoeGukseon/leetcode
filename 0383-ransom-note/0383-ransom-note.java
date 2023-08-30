class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : magazine.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (char c : ransomNote.toCharArray()) {
            if (map.containsKey(c)) {
                int cnt = map.get(c);
                cnt--;
                if (cnt == 0) {
                    map.remove(c);
                } else {
                    map.put(c, cnt);
                }
            } else {
                return false;
            }
        }
        return true;
    }
}