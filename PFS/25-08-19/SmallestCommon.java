/*
You are given an m x n matrix mat where each row is sorted in non-decreasing order.

Your task is to find the smallest common element in all rows. If there is no common element, return -1.

Input Format:
--------------
Line-1: Two integers m and n, representing the number of rows and columns, respectively.
Line-2: The next m lines contain n space-separated integers each, representing the elements of the matrix. Each row of the matrix is sorted in non-decreasing order.

Output Format:
---------------
Line-1: Print a single integer, the smallest common element in all rows. If no common element exists, print -1.

Constraints:
-------------
1 ≤ m, n ≤ 500
1 ≤ mat[i][j] ≤ 10^4
Each row is sorted in non-decreasing order.

Sample Input-1:
----------------
3 4
1 2 3 4
2 3 5 7
3 5 6 7

Sample Output-1:
-----------------
3

Explanation: The number 3 is the smallest common element in all rows.


Sample Input-2:
---------------
2 3
1 2 3
4 5 6

Sample Output-2:
----------------
-1

Explanation: There is no common element in all rows, so the output is -1.
*/
import java.util.*;
class SmallestCommon{
    public static int smallestnum(int[][] nums,int m, int n){
        HashMap<Integer, Integer> hmap=new HashMap<>();
        for(int num:nums[0]){
            if(!hmap.containsKey(num)) hmap.put(num,1);
        }
        for(int i=1;i<m;i++){
            for(int j=0;j<n;j++){
                
            }
        }
        //unfinished
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(hmap.containsKey(nums[i][j])){
                    int count=hmap.get(nums[i][j]);
                    hmap.replace(nums[i][j],count+1);
                }
                else hmap.put(nums[i][j], 1);
            }
        }
        int res=Integer.MAX_VALUE;
        for(Map.Entry<Integer, Integer> entry:hmap.entrySet()){
            if(entry.getValue()==m){
                res=Math.min(res, entry.getKey());
            }
        }
        return (res==Integer.MAX_VALUE)?-1:res;
    }
    public static void main (String[] args) {
        Scanner sc=new Scanner(System.in);
        int m=sc.nextInt();
        int n=sc.nextInt();
        int[][] nums=new int[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                nums[i][j]=sc.nextInt();
            }
        }
        System.out.println(smallestnum(nums,m,n));
    }
}