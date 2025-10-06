/*
Write a program to print the count of lower case and upper case letters in the given string 

Input - the input should consists of only one word 
Output - for the given input sting print the number of lowercase letters count followed by uppercase letters count 




Sample Test Case - 1
input = abcdEFghiJKL
output = 
7
5

Explanation 
in the above test case we have 7 lower case letters and 5 upper case letters


Sample Test Case -2 
input = 
abcdef
output = 
6
0


Explanation - 

In the above test case we have 6 lower case letters and no upper case letters.
since no upper case letters print 0 

Sample Test Case - 3 
input = 
WELCOME
output = 
0
7


Explanation - 

In the above test case we have 0 lower case letters and 7 upper case letters.
so the output consists of 0 and 7 


Here we have given the code to read  string from the user and calling a method countLowerUpperCount(String ) 
Just implement this method 

*/
import java.util.*;
class CountCases{
    public static void main (String[] args) {
        Scanner sc=new Scanner(System.in);
        String str=sc.next();
        int lower=0, upper=0;
        for(char c:str.toCharArray()){
            if(c>='a' && c<='z') lower++;
            else upper++;
        }
        System.out.println(lower);
        System.out.println(upper);
    }
}