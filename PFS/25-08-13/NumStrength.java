/*
The code strength of a number is defined as the sum of its digits.

You are given a number N.
Your task is to find the next number that is strictly greater than N whose code strength is exactly double the code strength of N.
Input: 14

Code strength = 1 + 4 = 5
Required strength = 2 * 5 = 10
The smallest number > 14 with strength 10 is 19.

Input: 10
Code strength = 1 + 0 = 1
Required strength = 2 * 1 = 2
The smallest number > 10 with strength 2 is 11.

Input: 99
Code strength = 9 + 9 = 18
Required strength = 36
The smallest number with strength 36 is 9999.
*/
import java.util.*;
class NumStrength{
    public static int doubles(int num, int sum, int doublenum, int count){
        while(num>0){
            int r=num%10;
            if(sum>=9 || r+sum>9){
                sum-=(9-r);
                r=9;
            }
            else{
                r+=sum;
                sum=0;
            }
            doublenum+=r*(Math.pow(10, count));
            count++;
            num=num/10;
        }
        while(sum>0){
            int add=Math.min(9, sum);
            doublenum+=add*(int)Math.pow(10, count);
            sum-=add;
            count++;
        }
        return doublenum;
    }
    public static void main (String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int num=n;
        int sum=0;
        while(n>0){
            int r=n%10;
            sum+=r;
            n=n/10;
        }
        System.out.println(doubles(num, sum, 0, 0));
    }
}