class Solution {
    public int trap(int[] height) {
        Stack<Integer> stack = new Stack<>();
        int water =0;
        for(int i =0; i < height.length;i++){
            while(!stack.isEmpty() && height[i]>height[stack.peek()]){
             int add = stack.pop();
             if(stack.isEmpty()){
                break;
             }  
             int leftWall = stack.peek();
             int coveredHeight = Math.min(height[leftWall], height[i])-height[add];
             int width = i - leftWall -1;

             water += coveredHeight*width; 
            }
            stack.push(i);
        }
        return water;
    }
}