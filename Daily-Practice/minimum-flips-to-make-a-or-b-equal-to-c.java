class Solution {
    public int minFlips(int a, int b, int c) {
        int flips = (a|b)^c;
        int extra = a&b&~c;
        return Integer.bitCount(flips)+ Integer.bitCount(extra);
    }
}