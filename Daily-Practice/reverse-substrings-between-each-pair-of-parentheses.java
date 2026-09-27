class Solution {
    public String reverseParentheses(String s) {
        Stack<Character> stack = new Stack<>();
        for(char ch : s.toCharArray()){
            if(ch == ')'){
                StringBuilder sb = new StringBuilder();
                while(!stack.isEmpty()&& stack.peek()!= '('){
                    sb.append(stack.pop());
                }
                if(!stack.isEmpty()){
                    stack.pop();
                }
                for(char c : sb.toString().toCharArray()){
                    stack.push(c);
                }
            }
            else{
                stack.push(ch);
            }
        }
        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }
        return result.reverse().toString();
    }
}