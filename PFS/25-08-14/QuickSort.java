/*
You are given an unsorted array of integers. 
Your task is to implement the QuickSort algorithm to sort the array in ascending order.

QuickSort is a divide-and-conquer algorithm that:
	Picks an element as a pivot and partitions the given array around the pivot.
	Recursively applies the same process to the subarrays on the left and right of the pivot.
	You need to implement QuickSort using the Lomuto partition scheme (where the pivot is chosen as the last element of the array).

Input Format:
-------------
Line-1: An integer n (the size of the array).
Line-2: n space-separated integers representing the elements of the array.

Output Format:
---------------
Line-1: Output the sorted array in a single line, with each element separated by a space.

Constraints:
------------
1 ≤ n ≤ 10^5
-10^9 ≤ arr[i] ≤ 10^9


Sample Input-1:
---------------
6
10 7 8 9 1 5

Sample Output-1:
----------------
1 5 7 8 9 10


Sample Input-2:
---------------
5
3 2 1 5 4

Sample Output-2:
----------------
1 2 3 4 5
*/
import java.util.*;
public class QuickSort{
    public static void quicksort(int[] arr, int low, int high){
        if(low<high){
            int pivotIndex=partition(arr, low, high);
            quicksort(arr, low, pivotIndex-1);
            quicksort(arr, pivotIndex+1, high);
        }
    }
    public static int partition(int[] arr, int low, int high){
        int pivot=arr[high];
        int i=low-1;
        for(int j=low;j<high;j++){
            if(arr[j]<=pivot){
                i++;
                int temp=arr[i];
                arr[i]=arr[j];
                arr[j]=temp;
            }
        }
        int temp=arr[i+1];
        arr[i+1]=arr[high];
        arr[high]=temp;
        return i+1;
    }
    public static void main (String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int[] arr=new int[n];
        for(int i=0;i<n;i++){
            arr[i]=sc.nextInt();
        }
        quicksort(arr, 0, n-1);
        for(int num:arr){
            System.out.print(num+" ");
        }
    }
}