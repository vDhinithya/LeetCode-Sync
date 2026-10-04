class Solution {
    public boolean checkValidString(String s) {
        Deque<Integer> openStack = new ArrayDeque<>();
        Deque<Integer> starStack = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                openStack.push(i);
            } else if (c == '*') {
                starStack.push(i);
            } else { // c == ')'
                if (!openStack.isEmpty()) {
                    openStack.pop();
                } else if (!starStack.isEmpty()) {
                    starStack.pop();
                } else {
                    return false; // Unmatched ')'
                }
            }
        }

        // Match remaining '(' with '*' that appear after them
        while (!openStack.isEmpty() && !starStack.isEmpty()) {
            if (openStack.pop() > starStack.pop()) {
                return false; // '(' appeared after '*'
            }
        }

        return openStack.isEmpty();
    }
}