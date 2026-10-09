class Solution {
    public int minInsertions(String s) {
        int o = 0;
        int ans = 0;
        int i = 0;
        int n = s.length();
        while(i<n){
            char c = s.charAt(i);
            if(c=='('){
                o++;
                i++;
            }else{
                if(i+1<n && s.charAt(i+1) ==')'){
                    i+=2;
                }else{
                    ans++;
                    i++;
                }
                if(o>0){
                    o--;
                }else{
                    ans++;
                }
            }
        }
        ans += 2*o;
        return ans;
    }
}