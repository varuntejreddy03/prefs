/*
Given an array nums of size n, return the majority element.

The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority element always exists in the array.

Example 1:
Input: nums = [3,2,3]    >n/2
Output: 3


Example 2:

Input: nums = [2,2,1,1,1,2,2]   1 1 1 2 2 2 2 
Output: 2
*/
import java.util.*;
class MajorityElem{
    public static void main (String[] args) {
        Scanner sc=new Scanner(System.in);
        HashMap<Integer, Integer> hmap=new HashMap<>();
        int n=sc.nextInt();
        int[] nums=new int[n];
        
        for(int i=0;i<n;i++){
            nums[i]=sc.nextInt();
            if(hmap.containsKey(nums[i])){
                int count=hmap.get(nums[i]);
                hmap.replace(nums[i],count+1);
            }
            else hmap.put(nums[i],1);
        }
        for(int num:nums){
            if(hmap.get(num)>n/2){
                System.out.println(num);
                break;
            }
        }
    }
    /*
    public static void main (String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int[] nums=new int[n];
        for(int i=0;i<n;i++){
            nums[i]=sc.nextInt();
        }
        Arrays.sort(nums);
        int count=1;
        int maxcount=count;
        int maxelem=nums[0];
        for(int i=1;i<n;i++){
            if(nums[i-1]==nums[i])count++;
            else {
                if(count>maxcount){
                    maxcount=count;
                    maxelem=nums[i-1];
                }
                count=1;
            }
        }
        if(count>maxcount){
            maxcount=count;
            maxelem=nums[n-1];
        }
        System.out.println(maxelem);
    } 
    */
}