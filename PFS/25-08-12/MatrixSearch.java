/*Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
    Integers in each row are sorted in ascending from left to right.
    Integers in each column are sorted in ascending from top to bottom.
Example:

Consider the following matrix:
[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]

Given target = 5, return true.
Given target = 20, return false.

input format:
input =5 5
1 4 7 11 15
2 5 8 12 19
3 6 9 16 22
10 13 14 17 24
18 21 23 26 30
5
output =true
*/
import java.util.*;
class MatrixSearch{
    public static boolean Foundnum(int[][] nums, int target, int m, int n){
        if(m>=nums.length || n<0) return false;
        if(nums[m][n]==target) return true;
        else if(nums[m][n]>target) return Foundnum(nums, target, m, n-1);
        else return Foundnum(nums, target, m+1, n);
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
        int target=sc.nextInt();
        System.out.println(Foundnum(nums,target,0,n-1));
    }
}