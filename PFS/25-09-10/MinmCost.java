/*
You are given an array of integers sticks where sticks[i] is the length of the i-th stick (or rope).

You want to connect all the sticks into one stick. The cost of connecting two sticks is equal to the sum of their lengths.
Return the minimum cost to connect all the sticks into one.

If there is only one stick, the cost is 0.

Constraints
1 <= sticks.length <= 10^5
0 <= sticks[i] <= 10^9

Input:4 
4 3 2 6
Output: 29
Explanation: First connect 2 and 3 to get [4, 5, 6] with a cost of 5, then connect 4 and 5 to get [9, 6] with a cost of 9, and finally connect 9 and 6 to get one rope with a cost of 15, giving a total minimum cost of 29. Any other order, such as connecting 4 and 6 first, results in a higher total cost of 38.


Input: arr[] = [4, 2, 7, 6, 9]
Output: 62 
Explanation: First, connect ropes 4 and 2, which makes the array [6, 7, 6, 9]. Cost of this operation 4 + 2 = 6. Next, add ropes 6 and 6, which results in [12, 7, 9]. Cost of this operation 6 + 6 = 12. Then, add 7 and 9, which makes the array [12,16]. Cost of this operation 7 + 9 = 16. And finally, add these two which gives [28]. Hence, the total cost is 6 + 12 + 16 + 28 = 62.

Input: arr[] = [10]
Output: 0
Explanation: Since there is only one rope, no connections are needed, so the cost is 0.

*/
import java.util.*;
class MinmCost{
    public static void main (String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        Queue<Long> pq=new PriorityQueue<>();
        for(int i=0;i<n;i++){
            pq.add(sc.nextLong());
        }
        Long min_cost=0l;
        while(pq.size()>1){
            Long cost=pq.poll()+pq.poll();
            min_cost+=cost;
            pq.add(cost);
        }
        System.out.println(min_cost);
    }
}