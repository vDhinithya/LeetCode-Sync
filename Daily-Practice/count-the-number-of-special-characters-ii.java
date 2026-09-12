class Solution {
    public int numberOfSpecialChars(String word) {
        int result =0;
        for(char ch = 'a'; ch<='z';ch++){
            int i = word.lastIndexOf(ch);
            int j = word.indexOf(Character.toUpperCase(ch));
            if(i!= -1 && j!= -1&& i<j) result++;
        }
        return result;
        
    }
}