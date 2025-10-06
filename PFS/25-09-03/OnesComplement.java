/*

write a program to read a string, which is a binary string 
and print the 1's complement of the string 

1's complement of a binary string is obtained by converting '1' to '0' and '0' to '1' 

Assume the input string consists of only '1' and '0'

Input - The input consists of only one binary string from the user
Output - For the given input binary string convert it to 1's complement 


Sample Test Case-1 

input = 1001
output = 0110


Explanation - 
From the input string when we convert '1' to '0' and '0' to '1' we have 0110 as output 


Sample Test Case-2

input = 1000
output = 0111

Explanation - 
From the input string when we convert '1' to '0' and '0' to '1' we have 0111 as output 


The code below reads the string from the user and 
calls a method printOnesComplement(String s );
Just implement this method


User Interface Help - Click on the Save option to save your code and 
Run option to Compile your code on the client Side
Evaluate option to submit your code to the server
*/
import java.util.*;
class OnesComplement{
    public static void main (String[] args) {
        Scanner sc=new Scanner(System.in);
        String input=sc.next();
        StringBuilder result=new StringBuilder();
        for(int i=0;i<input.length();i++){
            char ch=input.charAt(i);
            if(ch=='1') result.append('0');
            else result.append('1');
        }
        System.out.println(result.toString());
    }
}