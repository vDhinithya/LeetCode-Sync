class Solution {
    public int totalNumbers(int[] digits) {
        Set<Integer> uniNum = new HashSet<>();
        int n = digits.length;
        for(int i =0;i<n;i++){
            if(digits[i]==0) continue;
            for(int j=0;j<n;j++){
                if(i==j)continue;
                for(int k =0; k<n;k++){
                    if(i==k||j==k)continue;
                    int lastDigit = digits[k];
                    if(lastDigit % 2 ==0){
                        int num = digits[i]*100+digits[j]*10+digits[k];
                        uniNum.add(num);
                    }
                }
            }
        }
        return uniNum.size();
    }
}