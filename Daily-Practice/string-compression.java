class Solution {
    public int compress(char[] chars) {
        int readIndx = 0;
        int writeIndx = 0;

        while (readIndx < chars.length) {
            char currChar = chars[readIndx];
            int count = 0;

            // counts the duplicate characters frequency
            while (readIndx < chars.length && currChar == chars[readIndx]) {
                readIndx++;
                count++;
            }

            // both current character eand its count are available
            chars[writeIndx] = currChar;
            writeIndx++;
            if (count > 1) {
                String countStr = String.valueOf(count);
                for (char digit : countStr.toCharArray()) {
                    chars[writeIndx] = digit;
                    writeIndx++;
                }
            }
        }

        //return the lenght of compressed string 
        return writeIndx;
    }
}