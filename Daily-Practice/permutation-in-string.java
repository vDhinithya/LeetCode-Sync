class Solution {
    public boolean checkInclusion(String s1, String s2) {
        // using the approach that is being discussed 
        // basic check wheter s1 is present is s2 or not 
        // make the tabale of s1 having the frequeny of each letters
        // s2's first window will be compared to s1 table, if not move the window by 1

        if(s1.length()>s2.length()){
            return false;
        } 
        //frequency table of s1
        int count1[] = new int [26]; 
        for(int i=0; i<s1.length();i++){
            char ch = s1.charAt(i);
            int index = ch - 'a';
            count1[index]++;
        }

        
        int windowLength = s1.length();
        int count2[]= new int[26];

        // frequency table of first window
        for(int i=0;i<windowLength;i++){
            char ch = s2.charAt(i);
            int index = ch-'a';
            count2[index]++;
        }

        if(compareFreq(count1, count2)==true){
            return true;
        } else{
            int i = windowLength;
            while(i<s2.length()){
                //while moving to new window, add character to freq table
                char newChar = s2.charAt(i);
                int newIndex = newChar - 'a';
                count2[newIndex]++;
                // remove the entry of old charater from frequency table
                int oldCharIndex = i- windowLength;
                char oldChar = s2.charAt(oldCharIndex);
                int freqTableIndexOfOldChar = oldChar-'a';
                count2[freqTableIndexOfOldChar]--;
                //compare the updated table with s1 refrence table
                if(compareFreq(count1,count2)){
                    return true;
                }
                // do not forget to increment i
                i++;
            }
        }
        return false;
    }

    static boolean compareFreq(int[] count1,int [] count2){
        for(int i=0; i<26; i++){
            if(count1[i]!= count2[i]){
                return false;
            }
        }
        return true;
    }
}