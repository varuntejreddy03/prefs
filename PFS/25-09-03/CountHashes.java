/*
Twitter Hashtag 

Imagine you are analyzing tweets.
You need to count how many times each hashtag appears in a given tweet text.
Write a program in Java that:
Reads a tweet containing hashtags (words starting with #).
Counts the frequency of each hashtag using a HashMap.
Prints the result.

Test Case 1
Input = #java #python #java #c #java
Output =
{#java=3, #python=1, #c=1}

Test Case 2
Input =#ai #ml #ai #datascience #ml #ai
Output ={#ai=3, #ml=2, #datascience=1}

Test Case 3
Input = #hello #world #hello
Output ={#hello=2, #world=1}

Test Case 4
Input =#openai #chatgpt #openai #gpt
Output={#openai=2, #chatgpt=1, #gpt=1}

Test Case 5 (Single Hashtag)
Input=#banana
Output ={#banana=1}
*/
import java.util.*;
class CountHashes{
    public static void main (String[] args) {
        Map<String, Integer> hmap=new LinkedHashMap<>();
        Scanner sc=new Scanner(System.in);
        String hashes=sc.nextLine();
        String[] hashtag=hashes.trim().split("\\s+");
        for(String hash: hashtag){
            if(hash.startsWith("#"))
                hmap.put(hash, hmap.getOrDefault(hash, 0)+1);
        }
        System.out.println(hmap);
    }
}