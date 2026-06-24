class Solution {
    public int largestRectangleArea(int[] height) {
        Stack<Integer> stack = new Stack<>();
        int max = 0;

        stack.push(0);
        for(int i = 0; i<height.length;i++){
            while(!stack.isEmpty() && height[i] < height[stack.peek()]){
                max = getMax(height, stack, max, i);
            }
            stack.push(i);
        }
        int i = height.length;
        while(!stack.isEmpty()){
            max = getMax(height, stack, max , i);
        }
        return max;
    }

    private static int getMax(int[] arr, Stack<Integer> stack, int max, int i) {
        int area;
        int popped = stack.pop();
        if (stack.isEmpty()) {
            area = arr[popped] * i;
        } else {
            area = arr[popped] * (i - 1 - stack.peek());
        }
        return Math.max(max, area);
    }

}