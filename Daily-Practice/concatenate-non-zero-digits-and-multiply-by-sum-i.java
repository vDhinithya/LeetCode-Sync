class Solution {
    public long sumAndMultiply(int n) {
        long x = 0;
        long sum = 0;
        long place = 1;
        
        // Process digits from right to left
        while (n > 0) {
            int digit = n % 10;
            if (digit != 0) {
                // Add to the digit sum
                sum += digit;
                // Build x from right to left using the current place value
                x = digit * place + x;
                // Shift the place value for the next non-zero digit
                place *= 10;
            }
            n /= 10;
        }
        
        return x * sum;
    }
}