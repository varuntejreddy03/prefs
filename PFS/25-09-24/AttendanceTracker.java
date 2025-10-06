/*
A teacher keeps track of student names for each day of class. Some students attend multiple days, so their names appear multiple times in the attendance list.

Your task is to count how many times each student attended class and display the results in alphabetical order of names.

Input Format
------------
First line: an integer n — number of attendance entries (1 ≤ n ≤ 1000).
Second line: n space-separated strings — names of students.

Output Format
-------------
For each student, print:
StudentName:Count
Names should be printed in alphabetical order.

Example 1
---------
Input=
6
Alice Bob Alice Charlie Bob Alice

Output=
Alice:3
Bob:2
Charlie:1

Example 2
---------
Input=
5
David Eve David Eve Frank


Output=
David:2
Eve:2
Frank:1
*/
import java.util.*;
class AttendanceTracker{
    public static void main (String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        Map<String, Integer> map=new TreeMap<>();
        for(int i=0;i<n;i++){
            String name=sc.next();
            map.put(name, map.getOrDefault(name,0)+1);
        }
        for(Map.Entry<String, Integer> entry: map.entrySet()){
            String s=entry.getKey();
            int v=entry.getValue();
            System.out.println(s+":"+v);
        }
    }
}