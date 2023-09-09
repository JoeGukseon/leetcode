class Solution {
    public int lengthOfLastWord(String s) {
        String newstr = s.trim();
        s=s.trim();
       int count=0;
        int i = s.length()-1;
        while(i >= 0 && newstr.charAt(i)!=' '){
            count++;
            i--;
        } return count;
    }
}