class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        Map<String, String> map = new HashMap<>();
        for (List<String> pair : knowledge) {
            map.put(pair.get(0), pair.get(1));
        }
        Deque<StringBuilder> stack = new ArrayDeque<>();
        stack.push(new StringBuilder());
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(new StringBuilder());
            } else if (c == ')') {
                String key = stack.pop().toString();
                
                stack.peek().append(map.getOrDefault(key, "?"));
            } else {
                stack.peek().append(c);
            }
        }
        return stack.pop().toString();
    }
}