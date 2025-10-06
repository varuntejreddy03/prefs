/*
write a program to read the number and print the factorial of the givem number

ex:
input=1
output=1

input=3
output=6
*/
import java.util.*;
import java.math.*;
class FactorialRecursion{
    /*
    public static int factorial(int n){
        if(n==0 || n==1) return 1;
        return n*factorial(n-1);
    }
    */
    public static BigInteger factorial(int n){
        BigInteger fact=new BigInteger("1");
        for(int i=2;i<=n;i++){
            fact=fact.multiply(new BigInteger(i+""));
        }
        return fact;
    }
    
    public static void main(String[]args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        System.out.println(factorial(n));
    }
    
}