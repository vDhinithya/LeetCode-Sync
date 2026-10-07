class Solution {
    public List<String> removeInvalidParentheses(String s) {
        Stack <Character> stack = new Stack<>();

        for(char c :s.toCharArray()){
            if(c == '('){
                stack.push(c);
            }else if(c == ')'){
                if(!stack.isEmpty() && stack.peek()=='('){
                    stack.pop();
                }else{
                    stack.push(c);
                }
            }
        }
        int leftRem = 0;
        int rightRem = 0;
        while (!stack.isEmpty()) {
            if (stack.pop() == '(') {
                leftRem++;
            } else {
                rightRem++;
            }
        }
        
        Set<String> validExpressions = new HashSet<>();
        
        dfs(s, 0, leftRem, rightRem, validExpressions);
        
        return new ArrayList<>(validExpressions);
    }
    
    private void dfs(String s, int index, int leftRem, int rightRem, Set<String> validExpressions) {
        if (leftRem == 0 && rightRem == 0) {
            if (isValidUsingStack(s)) {
                validExpressions.add(s);
            }
            return;
        }
        
        for (int i = index; i < s.length(); i++) {
            if (i != index && s.charAt(i) == s.charAt(i - 1)) {
                continue;
            }
            
            char c = s.charAt(i);
            if (c == '(' || c == ')') {
                String nextStr = s.substring(0, i) + s.substring(i + 1);
                
                if (rightRem > 0 && c == ')') {
                    dfs(nextStr, i, leftRem, rightRem - 1, validExpressions);
                } else if (leftRem > 0 && c == '(') {
                    dfs(nextStr, i, leftRem - 1, rightRem, validExpressions);
                }
            }
        }
    }
    
    private boolean isValidUsingStack(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                if (stack.isEmpty() || stack.peek() != '(') {
                    return false;
                }
                stack.pop();
            }
        }
        return stack.isEmpty();
    }
}