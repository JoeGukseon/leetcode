class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String,List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] temp = str.toCharArray();
            Arrays.sort(temp);
            String strTemp = new String(temp);

            if (map.containsKey(strTemp)) {
                List<String> list = map.get(strTemp);
                list.add(str);
                map.put(strTemp, list);
            } else {
                List<String> list = new ArrayList<>();
                list.add(str);
                map.put(strTemp, list);
            }


        }

        return new ArrayList<>(map.values());
    }
}