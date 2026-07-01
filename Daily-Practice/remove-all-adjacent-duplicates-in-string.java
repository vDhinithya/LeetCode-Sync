class Solution {
    public String removeDuplicates(String s) {
        StringBuilder str = new StringBuilder();
        for (char ch : s.toCharArray()) {
            int size = str.length();
            if (size > 0 && str.charAt(size - 1) == ch) {
                str.deleteCharAt(size - 1);
            } else {
                str.append(ch);
            }
        }
        return str.toString();
    }
}