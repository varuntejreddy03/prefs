/*Write a Java program to compute the Fibonacci number for a given n
ex:

input = 2
output =1

first two elements 0 1. so print last element ie nth fibonacci number is 1

input = 3
output =2
first three elements 0 1 1 Write a Java program to compute the Fibonacci number for a given 1

input  =50
output =12586269025
*/
import java.util.*;
import java.math.*;
class FibonacciRecursion{
    /*
    public static BigInteger fibonacci(int n){
        if(n==0 || n==1) return n;
        else return fibonacci(n-1)+fibonacci(n-2);
    }
    */
    public static BigInteger fibonacci(int n){
        if(n==0 ||n==1) return BigInteger.valueOf(n);
        BigInteger a= BigInteger.ZERO, b=BigInteger.ONE, result=BigInteger.ZERO;
        for(int i=2;i<=n;i++){
            result=a.add(b);
            a=b;
            b=result;
        }
        return result;
    }
    public static void main(String[]args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        System.out.println(fibonacci(n));
    }
}
