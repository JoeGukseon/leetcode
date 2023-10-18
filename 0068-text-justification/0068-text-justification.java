class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        int start = 0;

        // 각 줄에 대해 반복
        while (start < words.length) {
            int end = findLineEnd(words, start, maxWidth); // 현재 줄의 끝 인덱스 찾기
            result.add(justifyLine(words, start, end, maxWidth)); // 현재 줄 형식화하여 결과에 추가
            start = end + 1; // 다음 줄의 시작 인덱스 갱신
        }

        return result;
    }

    private int findLineEnd(String[] words, int start, int maxWidth) {
        int end = start;
        int lineLength = 0;

        // 길이 누적으로 끝 인덱스 찾기
        while (end < words.length && lineLength + words[end].length() <= maxWidth) {
            lineLength += words[end].length() + 1; // 1은 단어 사이의 공백
            end++; // 인덱스 증가
        }

        return end - 1; // 실제 끝 인덱스 반환
    }

    private String justifyLine(String[] words, int start, int end, int maxWidth) {
        int totalSpaces = maxWidth - wordLength(words, start, end); // 남은 공백 계산
        int numGaps = end - start; // 공백 슬롯의 개수 계산

        StringBuilder line = new StringBuilder(words[start]);

        if (numGaps == 0 || end == words.length - 1) {
            // 특별한 경우: 한 단어만 있는 줄이거나, 마지막 줄인 경우 왼쪽 정렬
            for (int i = start + 1; i <= end; i++) {
                line.append(" ").append(words[i]);
            }
            while (line.length() < maxWidth) {
                line.append(" ");
            }
        } else {
            for (int i = start + 1; i <= end; i++) {
                int spaces = (int) Math.ceil((double) totalSpaces / numGaps);
                line.append(" ".repeat(spaces)).append(words[i]);
                totalSpaces -= spaces;
                numGaps--;
            }
        }

        return line.toString(); // 결과 문자열 반환
    }
    
    // 
    private int wordLength(String[] words, int start, int end) {
        int length = 0;

        for (int i = start; i <= end; i++) {
            length += words[i].length();
        }

        return length;
    }
}