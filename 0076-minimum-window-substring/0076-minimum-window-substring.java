class Solution {
    public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() == 0 || t.length() == 0) {
            return "";
        }

        //t의 문자 저장
        Map<Character, Integer> targetMap = new HashMap<>();
        for (char c : t.toCharArray()) {
            targetMap.put(c, targetMap.getOrDefault(c, 0) + 1);
        }

        int left = 0;
        int right = 0;
        int requiredChars = targetMap.size(); // t의 문자수
        int formedChars = 0; //현재창의 t의 문자 포함 수

        Map<Character, Integer> windowMap = new HashMap<>();
        int[] result = {-1, 0, 0};

        while (right < s.length()) {
            char currentChar = s.charAt(right);
            windowMap.put(currentChar, windowMap.getOrDefault(currentChar, 0) + 1);

            //t에 s의 현재 문자가 있고, s의개수가 t의 갯수가 같으면 현재문자열의 갯수 증가
            if (targetMap.containsKey(currentChar) && windowMap.get(currentChar).equals(targetMap.get(currentChar))) {
                formedChars++;
            }

            //타겟맵에 타겟이 다 있으면
            while (left <= right && formedChars == requiredChars) {
                char leftChar = s.charAt(left);

                //길이가 -1 이거나 길이가 기존 길이보다 짧으면 (min 계산)
                if (result[0] == -1 || right - left + 1 < result[0]) {
                    result[0] = right - left + 1; //길이 업데이트
                    result[1] = left; //인덱스업데이트
                    result[2] = right + 1;
                }

                //왼쪽부터 다시 문자제거
                windowMap.put(leftChar, windowMap.get(leftChar) - 1);
                if (targetMap.containsKey(leftChar) && windowMap.get(leftChar) < targetMap.get(leftChar)) {
                    formedChars--;
                }

                left++;
            }

            right++;
        }

        return result[0] == -1 ? "" : s.substring(result[1], result[2]);
    }
}