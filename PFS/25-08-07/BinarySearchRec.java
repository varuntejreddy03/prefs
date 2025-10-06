/*Given an array of integers nums which is sorted in ascending order, and an integer target, write a function to search target in nums. If target exists, then return its index. Otherwise, return -1.

You must write an algorithm with O(log n) runtime complexity.

Example 1:

Input: nums = [-1,0,3,5,9,12], target = 9
Output: 4
Explanation: 9 exists in nums and its index is 4.


Example 2:

Input: nums = [-1,0,3,5,9,12], target = 2
Output: -1
Explanation: 2 does not exist in nums so return -1
*/
import java.util.*;
class BinarySearchRec{
    public static int BinarySearch(int[] arr, int target, int low, int high){
        int mid=low+(high-low)/2;
        while(high>=low){
            if(arr[mid]==target) return mid;
            if(arr[mid]>target) return BinarySearch(arr, target, low, mid-1);
            else return BinarySearch(arr, target, mid+1, high);
        }
        return -1;
    }
    public static void main (String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int[] arr=new int[n];
        for(int i=0;i<n;i++){
            arr[i]=sc.nextInt();
        }
        int target=sc.nextInt();
        Arrays.sort(arr);
        
        System.out.println(BinarySearch(arr, target, 0, n-1));
    }
}