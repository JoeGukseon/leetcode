class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();

        //예외처리 입력값이 없는경우
        if (s == null || s.length() == 0 || words == null || words.length == 0) {
            return result;
        }


        int wordLength = words[0].length(); //주어진 words 길이
        int totalLength = wordLength * words.length; //총길이

        Map<String, Integer> wordCountMap = new HashMap<>(); //word카운트 맵을 만들고
        for (String word : words) {
            wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1); //맵에 단어별 카운트 넣기
        }

        for (int i = 0; i <= s.length() - totalLength; i++) { //0부터 체크하려는 총길이를 뺀만큼만
            String substring = s.substring(i, i + totalLength); //인덱스를 총길이만큼씩 잘라 체크
            if (isConcatenated(substring, wordLength, wordCountMap)) { //자른 문자열과 워드 길이 워드가 담긴 맵을 전달
                result.add(i); //인덱스를 추가
            }
        }

        return result;
    }

    private boolean isConcatenated(String substring, int wordLength, Map<String, Integer> wordCountMap) {
        Map<String, Integer> substringCountMap = new HashMap<>(); //부분문자열의 카운트맵을 만들고

        for (int i = 0; i < substring.length(); i += wordLength) {
            String word = substring.substring(i, i + wordLength);//문자를 하나씩 뽑아
            substringCountMap.put(word, substringCountMap.getOrDefault(word, 0) + 1);//맵에 넣고
        }

        return substringCountMap.equals(wordCountMap);//비교
    }
}