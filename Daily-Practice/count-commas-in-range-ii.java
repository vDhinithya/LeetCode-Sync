class Solution {
    public long countCommas(long n) {
        long comma = 0;
        long limit = 1000L;

        while(n>=limit){
            comma += (n-limit+1);
            if(limit>Long.MAX_VALUE/1000){
                break;
            }
            limit *= 1000L;
        }
        return comma;
    }
}