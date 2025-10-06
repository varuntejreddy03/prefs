/*
Mr.Sathya is solving a puzzle, the problem is given a sorted array which contains 
only 0s and 1s. 

He has to find total number of 1s in the given list, Help him in writing a program 
to do this task in an efficient way.

Input Format:
-------------
Line-1: An integer number, n
Line-2: n spaced integers (contains 0s and 1s only in a sorted order)

Output Format:
--------------
Line-1: An integer

Sample Input-1
---------------
5
0 0 1 1 1

Sample Output-1:
----------------
3

Sample Input-2
---------------
10
1 1 1 1 1 1 1 1 1 1

Sample Output-2:
----------------
10

*/
import java.util.*;
public class CountOnes{
    public static void main (String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int[] nums=new int[n];
        for(int i=0;i<n;i++){
            nums[i]=sc.nextInt();
        }
        int count=0;
        for(int i=0;i<n;i++){
            if(nums[i]==1){
                break;
            }
            count++;
        }
        System.out.println(n-count);
    }
}