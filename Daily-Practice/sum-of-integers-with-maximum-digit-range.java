class Solution {
    public int maxDigitRange(int[] nums) {
        int sum = 0;
        int range = -1;

        for(int num :nums){
            int curRange= getRange(num);
            if(curRange> range){
                range = curRange;
                sum = num;
            }else if(curRange == range){
                sum += num;
            }
        }
        return sum;
    }

    private int getRange(int num) {
        int maxDigit = 0;
        int minDigit = 9;
        
        while (num > 0) {
            int digit = num % 10;
            
            if (digit > maxDigit) {
                maxDigit = digit;
            }
            if (digit < minDigit) {
                minDigit = digit;
            }
            
            num /= 10;
        }
        
        return maxDigit - minDigit;
    }
}