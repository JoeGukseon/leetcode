class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();

        if (digits == null || digits.length() == 0) {
            return result;
        }

        Map<Character, String> digitMapping = new HashMap<>();
        digitMapping.put('2', "abc");
        digitMapping.put('3', "def");
        digitMapping.put('4', "ghi");
        digitMapping.put('5', "jkl");
        digitMapping.put('6', "mno");
        digitMapping.put('7', "pqrs");
        digitMapping.put('8', "tuv");
        digitMapping.put('9', "wxyz");

        generateCombinations(result, "", digits, 0, digitMapping);

        return result;
    }

    private void generateCombinations(List<String> result, String current, String digits, int index, Map<Character, String> digitMapping) {
        if (index == digits.length()) {
            result.add(current);
            return;
        }

        char digit = digits.charAt(index);
        String letters = digitMapping.get(digit);

        for (char letter : letters.toCharArray()) {
            generateCombinations(result, current + letter, digits, index + 1, digitMapping);
        }
    }
}