class Solution {
    public int reverseDegree(String s) {
        int totalSum = 0;
        int n = s.length();
        for(int i =0;i<n;i++){
            int revPos= 26-(s.charAt(i)-'a');
            int stringPos = i+1;
            totalSum += revPos*stringPos;
        }
        return totalSum;
    }
}