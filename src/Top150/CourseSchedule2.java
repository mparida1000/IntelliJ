package Top150;
/*There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.

For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
Return the ordering of courses you should take to finish all courses. If there are many valid answers,
return any of them. If it is impossible to finish all courses, return an empty array.
1 <= numCourses <= 2000
0 <= prerequisites.length <= numCourses * (numCourses - 1)
prerequisites[i].length == 2
0 <= ai, bi < numCourses
ai != bi
All the pairs [ai, bi] are distinct.
TC O(V+E)

--Kahn's Algorithm
*/

import java.util.*;

public class CourseSchedule2 {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // graph[u] = list of courses that depend on course u
        List < List < Integer >> graph = new ArrayList < > ();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList < > ());
        }

        // inDegree[v] = number of prerequisites still needed for course v
        int[] inDegree = new int[numCourses];

        // Build graph and in-degree
        for (int[] edge: prerequisites) {
            int course = edge[0];
            int prereq = edge[1];

            graph.get(prereq).add(course); // prereq -> course
            inDegree[course]++;
        }

        Queue< Integer > queue = new LinkedList < > ();

        // Add all courses with no prerequisites
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        int[] result = new int[numCourses];
        int index = 0;

        while (!queue.isEmpty()) {
            int current = queue.poll();
            result[index++] = current;

            for (int neighbor: graph.get(current)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        // If all courses were not processed, cycle exists
        if (index != numCourses) {
            return new int[0];
        }

        return result;
    }

    public static void main(String[] args) {
        int n = 4;
        int arr [][] = {
                {2,0},
                {1,0},
                {2,1},
                {2,3},
                {1,3}
        };

        System.out.println();
        int[] res = new CourseSchedule2().findOrder(n, arr);
        for(int i : res){
            System.out.print(i + " ");
        }
    }
}
