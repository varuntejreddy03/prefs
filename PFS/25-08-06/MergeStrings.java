/*
In the ancient library of Lexiconia, two magical scrolls — Scroll Alpha and Scroll Beta — contain sequences of enchanted runes. These runes must be woven together in a 
precise pattern to unlock a hidden door to the Chamber of Secrets.

The rules for weaving are as follows:

Start with the first rune from Scroll Alpha, then alternate with a rune from Scroll Beta.

Continue this alternating weave — one from Alpha, one from Beta — until one of the scrolls runs out.

Once one scroll is exhausted, append the remaining runes from the longer scroll in the same order to the woven result.

Write a function that, given two rune strings scroll_alpha and scroll_beta, returns the woven scroll according to the rules of Lexiconia.

In the ancient library of Lexiconia, two magical scrolls — Scroll Alpha and Scroll Beta — contain sequences of enchanted runes. These runes must be woven together in a
precise pattern to unlock a hidden door to the Chamber of Secrets.

The rules for weaving are as follows:

Start with the first rune from Scroll Alpha, then alternate with a rune from Scroll Beta.

Continue this alternating weave — one from Alpha, one from Beta — until one of the scrolls runs out.

Once one scroll is exhausted, append the remaining runes from the longer scroll in the same order to the woven result.

Write a function that, given two rune strings scroll_alpha and scroll_beta, returns the woven scroll according to the rules of Lexiconia.

input =abc
pqr
output =apbqcr

Explanation: The merged string will be merged as so:
word1:  a   b   c
word2:    p   q   r
merged: a p b q c r

input =ab
pqrs
output =apbqrs
Explanation: Notice that as word2 is longer, "rs" is appended to the end.
word1:  a   b 
word2:    p   q   r   s
merged: a p b q   r   s

input =abcd
pq
output =apbqcd
Explanation: Notice that as word1 is longer, "cd" is appended to the end.
word1:  a   b   c  d
word2:    p   q 
merged: a p b q c  d
*/
import java.util.*;
class MergeStrings{
    public static String mergestrs(String str1, String str2){
        StringBuilder merge=new StringBuilder();
        int i=0;
        while(i<str1.length() || i<str2.length()){
            if(i<str1.length()) merge.append(str1.charAt(i));
            if(i<str2.length()) merge.append(str2.charAt(i));
            i++;
        }
        return merge.toString();
    }
    public static void main(String[]args){
        Scanner sc=new Scanner(System.in);
        String str1=sc.next();
        String str2=sc.next();
        System.out.println(mergestrs(str1, str2));
    }
}