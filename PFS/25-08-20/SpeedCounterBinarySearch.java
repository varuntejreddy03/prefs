/*
Koko loves to eat bananas. There are n piles of bananas, where the i-th pile has piles[i] bananas. 
The guards have gone, and Koko can decide her own banana eating speed k (bananas per hour). 
Each hour, she chooses any pile of bananas and eats k bananas from that pile. 
If the pile has less than k bananas, she eats all of them instead and does not 
eat any more bananas during that hour.

Koko likes to finish eating all the bananas within h hours. 

Your task is to find the minimum integer value of k such that she can eat all 
the bananas within h hours.

Input Format:
-------------
Line-1: Two integers n and h, where n is the number of piles and h is the maximum number of hours Koko has to eat all the bananas.
Line-2: n space-separated integers, representing the number of bananas in each pile.

Output Format:
--------------
Return the minimum eating speed k such that Koko can eat all the bananas within 
h hours.

Constraints:
------------
1 ≤ n ≤ 10^4
1 ≤ piles[i] ≤ 10^9
1 ≤ h ≤ 10^9


Sample Input-1:
---------------
4 8
3 6 7 11

Sample Output-1:
----------------
4

Explanation:

Suppose Koko eats at k = 4.
Hour 1: eats 3 from pile1 (done).
Hour 2: eats 4 from pile2 (2 left).
Hour 3: eats 2 from pile2 (done).
Hour 4: eats 4 from pile3 (3 left).
Hour 5: eats 3 from pile3 (done).
Hour 6: eats 4 from pile4 (7 left).
Hour 7: eats 4 from pile4 (3 left).
Hour 8: eats 3 from pile4 (done).
Finished exactly in 8 hours .

So, minimum k = 4.




Sample Input-2:
---------------
5 7
30 11 23 4 20

Sample Output-2:
----------------
30

Explanation:
Koko can finish all bananas at a speed of k = 30. She eats each pile in an hour.


Sample Input-3:
---------------
5 6
30 11 23 4 20

Sample Output-3:
23

Explanation:
Koko can eat all bananas in 6 hours with a speed of k = 23.
*/
import java.util.*;
public class SpeedCounterBinarySearch{
    public static int Speed(int[] piles, int n, int h){
        int left=1, right=piles[n-1];
        int ans=right;
        while(left<=right){
            int mid=left+(right-left)/2;
            long hrs=0;
            for(int pile:piles){
                hrs+=(pile+mid-1)/mid;
            }
            if(hrs<=h){
                ans=mid;
                right=mid-1;
            }
            else left=mid+1;
        }
        return ans;
    }
    public static void main (String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int h=sc.nextInt();
        int[] piles=new int[n];
        for(int i=0;i<n;i++){
            piles[i]=sc.nextInt();
        }
        Arrays.sort(piles);
        System.out.println(Speed(piles, n, h));
    }
}