/*
A group of kids are playing a game in their school playground. They have n marbles with them, and they decide to arrange the marbles into a 
perfect square formation on the ground.

A perfect square formation means the marbles must be arranged into a square grid, where the number of rows equals the number of columns 
(for example, 25 marbles can form a 5×5 square).

Now, the kids want to quickly check if the number of marbles they have can form such a perfect square arrangement. 
Since they don’t want to waste time placing all marbles, they decide to use a smart strategy: applying the binary search method to test 
if a square grid is possible.

Your task is to help the kids determine whether the given number of marbles n can form a perfect square.

Input Format:
-------------
Line-1: A single integer n.

Output Format:
---------------
Line-1: Print true if the number n is a perfect square, otherwise print false.


Constraints:
-------------
The integer n will always be a positive integer (1 ≤ n ≤ 10^9).
The solution must have a time complexity of O(log(n)).


Sample Input-1:
---------------
25

Sample Output-1:
----------------
true

Explanation:
5 * 5 = 25, so 25 is a perfect square.


Sample Input-2:
---------------
9

Sample Output-2:
----------------
true

Explanation:
3 * 3 = 9, so 9 is a perfect square.


Sample Input-3:
---------------
8

Sample Output-3:
----------------
false

Explanation:
There is no integer x such that x * x = 8.


Sample Input-4:
---------------
125

Sample Output-4:
----------------
false

Explanation:
There is no integer x such that x * x = 125.
*/
import java.util.*;
class PerfectSquares{
    public static void main (String[] args) {
        Scanner sc=new Scanner(System.in);
        int marbles=sc.nextInt();
        long low=0, high=marbles;
        boolean perfect=false;
        if(marbles==1) perfect=true;
        while(low<=high){
            long mid=low+(high-low)/2;
            long square=mid*mid;
            if(square==marbles){
                perfect=true;
                break;
            }
            else if(square<=marbles) low=mid+1;
            else high=mid-1;
        }
        System.out.println(perfect);
    }
}