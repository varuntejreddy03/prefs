/*
In a high-security research facility, all temporary access passcodes must follow a unique standard:
They must look identical when read forwards and backwards, ignoring any symbols, spaces, or case differences.

You are given a string code that represents a scanned passcode. Your task is to validate the code to ensure it adheres to the standard.

A passcode is valid if, after:

Removing all non-alphanumeric characters, and

Converting all uppercase letters to lowercase,

…it reads the same forwards and backwards.
 
Example 1:

Input: s = "A man, a plan, a canal: Panama"
Output: true
Explanation: "amanaplanacanalpanama" is a palindrome.
Example 2:

Input: s = "race a car"
Output: false
Explanation: "raceacar" is not a palindrome.
Example 3:

Input: s = " "
Output: true
Explanation: s is an empty string "" after removing non-alphanumeric characters.
Since an empty string reads the same forward and backward, it is a palindrome.
*/
import java.util.*;
class palindromedp{
    public static boolean isPalindrome(String str){
        /*
        if(str.length()<=1) return true;
        if(str.charAt(0)!=str.charAt(str.length()-1)) return false;
        return isPalindrome(str.substring(1, str.length()-1));
        */
        int left=0, right=str.length()-1;
        while(left<right){
            if(str.charAt(left)!=str.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }
    public static void main(String[]args){
        Scanner sc=new Scanner(System.in);
        String str=sc.nextLine();
        StringBuilder cleanStr=new StringBuilder();
        for(char c: str.toLowerCase().toCharArray()){
            if(Character.isLetterOrDigit(c)) cleanStr.append(c);
        }
        System.out.println(isPalindrome(cleanStr.toString()));
        /*
        str=str.toLowerCase();
        String s="";
        for(char c:str.toCharArray()){
            if(Character.isLetter(c)) s+=c;
        }
        System.out.println(isPalindrome(s));
        */
    }
}