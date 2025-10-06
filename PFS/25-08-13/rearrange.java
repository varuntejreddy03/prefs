/*The academic council is analyzing a sequence of activity scores for students, 
where each score can be positive (indicating extra credit) or negative (indicating penalty).
To streamline their analysis, the council wants to rearrange the list so that 
all penalty scores are grouped at the beginning, followed by all extra credit scores. 
The order of penalties among themselves and extra credits among themselves 
must remain the same as in the original list.

Write a program to assist the council in generating this rearranged list, 
ensuring that no scores relative order within its category is changed.

Input Format:
Line-1: An integer n, the number of recorded activity scores
Line-2: n integers separated by spaces (both positive and negative values possible)

Output Format:
Line-1: List of integers, with all penalty (negative) scores first in original order, followed by all extra credit (positive) scores in original order


Sample Input-1: 
---------------
8  
9 -3 5 -2 -8 -6 1 3

Sample Output-1: 
---------------
[-3, -2, -8, -6, 9, 5, 1, 3]

Sample Input-2: 
---------------
8
5 -4 3 -4 2 1 -5 -6

Sample Output-2:
---------------
[-4, -4, -5, -6, 5, 3, 2, 1]
*/
import java.util.*;
public class rearrange{
    public static void main (String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int[] nums=new int[n];
        for(int i=0;i<n;i++){
            nums[i]=sc.nextInt();
        }
        
        ArrayList<Integer> negative=new ArrayList<>();
        ArrayList<Integer> positive=new ArrayList<>();
        
        for(int num:nums){
            if(num<0) negative.add(num);
            else positive.add(num);
        }
        negative.addAll(positive);
        System.out.println(negative);
    }
}