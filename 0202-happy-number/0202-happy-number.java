class Solution {
    public boolean isHappy(int n) {
        while (true) {
            if (n == 1 || n==7) {
                return true;
            } else if (n < 10) {
                return false;
            }
            String str = Integer.toString(n);
            n = 0;
            for (int i = 0; i < str.length(); i++) {
                char digitCh = str.charAt(i);
                int digit = Character.getNumericValue(digitCh);
                n += digit * digit;
            }
        }
    }
}