/*
You are tasked with calculating how many complete rows a mason can build while 
constructing a staircase. 
The staircase consists of k rows, where the i-th row contains exactly i bricks. 
The mason has n bricks in total, and the last row of the staircase may be incomplete.

Your goal is to determine the number of complete rows that can be fully built using the given n bricks. 
Solve this problem in O(log n) time complexity.

Input Format:
--------------
Line-1: A single integer n, representing the total number of bricks.

Output Format:
--------------
Line-1: Return a single integer representing the number of complete rows that 
can be fully constructed with the available bricks.

Constraints:
------------
0 ≤ n ≤ 10^9

Sample Input-1:
----------------
5

Sample Output-1:
----------------
2

Explanation:
The staircase built with 5 bricks looks like this:
Row 1: B1
Row 2: B2 B3
Row 3: B4 B5 (incomplete)
Rows 1 and 2 are complete, but row 3 is incomplete. Therefore, the number of complete rows is 2.


Sample Input-2:
---------------
10

Sample Output-2:
----------------
4

Explanation:
The staircase built with 10 bricks looks like this:
Row 1: B1
Row 2: B2 B3
Row 3: B4 B5 B6
Row 4: B7 B8 B9 B10
All rows are fully constructed, so the output is 4.


Sample Input-3:
---------------
19

Sample Output-3:
-----------------
5

Explanation:
The staircase built with 19 bricks looks like this:
Row 1: B1
Row 2: B2 B3
Row 3: B4 B5 B6
Row 4: B7 B8 B9 B10
Row 5: B11 B12 B13 B14 B15 (incomplete)
Rows 1 through 4 are complete, but row 5 is incomplete. The number of complete rows is 5.

*/
//BruteForce approach
/*import java.util.*;
class ArrangeBricks{
    public static void main (String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int sum=0;
        if(n<=0){
            System.out.println(n);
            return;
        }
        for(int i=1;i<=n;i++){
            sum+=i;
            if(sum>n){
                System.out.println(i-1);
                return;
            }
            else if(sum==n){
                System.out.println(i);
                return;
            }
        }
        
    }
}
*/
//Binary Search
import java.util.*;
class ArrangeBricks{
    public static void main (String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        long low=0, high=n, rows=0;
        while(low<=high){
            long mid=low+(high-low)/2;
            long Bricks=mid*(mid+1)/2;
            if(Bricks==n){
                rows=mid;
                break;
            }
            else if(Bricks<n){
                rows=mid;
                low=mid+1;
            }
            else high=mid-1;
        }
        System.out.println(rows);
    }
}