class Solution {
    public String reverseWords(String s) {
        // String builder will be used in this case to track record of the words in the sentence
        /* 
        as String are IMMUTABLE in Java so new string creration will take extra space increasing space conplexity
        so String Builder will be used which can be used to apped the characters/string

        INTUTION
        * ignore the trailing empty spaces
        * find starting point of word :- i is pointer on the last charater now, and a new pointer j will be used to get the start of the word
        "j" will eventully search for the blank space and return the j+1 index which will be the starting point for word going upto i 
        * substring found from j+1 to i
        * add this substring into StringBuilder
        * add space after adding the word except for first word and j--
        * i=j now
         */
        
        StringBuilder ans = new StringBuilder();
        int i = s.length()-1;

        while(i>=0){
            //remove empty spaces
            while(i>=0 && s.charAt(i)==' '){
                i--;
            }
            //check value of i
            if(i<0){
                break;
            }
            int j = i;
            //finding the start index of the word
             while(j>=0 && s.charAt(j)!=' '){
                j--;
            }
            //stop when j reaches to a space and add/append the word from the next index to i in the substring
            ans.append(s.substring(j+1, i+1));
            // remove space where j is standing and add a space in the ans string

             while(j>=0 && s.charAt(j)==' '){
                j--;
            }
            if(j>=0){
                ans.append(' ');
            }
            //place i at the last index of the remainig string
            i=j;

        }
        return ans.toString();
    }
}