/*
In an ancient temple, there's a mystical staircase with n steps. A codebreaker needs to reach the top of the staircase by making jumps of at most m steps at a time. 
That means from any step, they can move to the next 1, 2, ..., up to m steps ahead — if possible.

Write a function countWays(n: int, m: int) -> int that returns the total number of distinct ways the codebreaker can climb from step 0 to step n.

Assume:

The codebreaker starts from step 0 (ground).

They can only move upward.

You need to compute the number of different sequences of jumps that sum up to exactly n steps, where each jump is between 1 and m.

Examples:
Example 1:
Input: n = 4, m = 2
Output: 5
Explanation: The codebreaker can jump in these 5 ways:

1 + 1 + 1 + 1

1 + 1 + 2

1 + 2 + 1

2 + 1 + 1

2 + 2

Example 2:
Input: n = 3, m = 3
Output: 4
Explanation:

1 + 1 + 1

1 + 2

2 + 1

3
*/
import java.util.*;
class StaircaseWays{
    public static int ways(int n,int m, HashMap<Integer, Integer> hmap){
        if(hmap.containsKey(n)) return hmap.get(n);
        int count=0;
        for(int i=1;i<=m;i++){
            count+=countWays(n-i,m);
        }
        return count;
    }
    public static int countWays(int n, int m){
        if(n==0) return 1;
        if(n<0) return 0;
        HashMap<Integer, Integer> hmap=new HashMap<>();
        int count=ways(n,m,hmap);
        hmap.put(n,count);
        return count;
        
    }
    public static void main(String[]args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int m=sc.nextInt();
        System.out.println(countWays(n,m));
    }
}