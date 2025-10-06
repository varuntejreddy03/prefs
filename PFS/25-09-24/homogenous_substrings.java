/*
Given a string s, return the number of homogenous substrings of s. Since the answer may be too large, return it modulo 109 + 7.

A string is homogenous if all the characters of the string are the same.

A substring is a contiguous sequence of characters within a string.

 

Example 1:
input =abbcccaa
output =13

Explanation: The homogenous substrings are listed as below:
"a"   appears 3 times.
"aa"  appears 1 time.
"b"   appears 2 times.
"bb"  appears 1 time.
"c"   appears 3 times.
"cc"  appears 2 times.
"ccc" appears 1 time.
3 + 1 + 2 + 1 + 3 + 2 + 1 = 13.


Input: s = "xy"
Output: 2
Explanation: The homogenous substrings are "x" and "y".
Example 3:

Input: s = "zzzzz"
Output: 15
 

Constraints:

1 <= s.length <= 105
s consists of lowercase letters.
*/
import java.util.*;
class homogenous_substrings{
    public static int count_substrings(String s){
        long result=0;
        int n=s.length();
        int count=1;
        for(int i=1;i<n;i++){
            if(s.charAt(i)==s.charAt(i-1)) count++;
            else{
                result+=(long)count*(count+1)/2;
                count=1;
            }
        }
        result+=(long)count*(count+1)/2;
        return (int)result;
    }
    public static void main (String[] args) {
        Scanner sc=new Scanner(System.in);
        String inp=sc.next();
        System.out.println(count_substrings(inp));
    }
}