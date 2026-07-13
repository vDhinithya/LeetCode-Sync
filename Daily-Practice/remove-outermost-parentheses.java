class Solution {
    public String removeOuterParentheses(String s) {
        StringBuilder result = new StringBuilder();
        int balance = 0;
        
        for (char c : s.toCharArray()) {
            if (c == '(') {
                if (balance > 0) { // not outermost
                    result.append(c);
                }
                balance++;
            } else {
                balance--;
                if (balance > 0) { // not outermost
                    result.append(c);
                }
            }
        }
        
        return result.toString();
    }
}
