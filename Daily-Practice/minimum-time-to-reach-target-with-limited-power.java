import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

class Solution {
    public long[] minTimeMaxPower(int n, int[][] edges, int power, int[] cost, int source, int target) {
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] e : edges) {
            adj.get(e[0]).add(new int[]{e[1], e[2]});
        }
        
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) {
                return Long.compare(a[0], b[0]);
            }
            return Long.compare(b[2], a[2]);
        });
        
        int[] maxPowerAt = new int[n];
        Arrays.fill(maxPowerAt, -1);
        
        pq.offer(new long[]{0, source, power});
        
        while (!pq.isEmpty()) {
            long[] curr = pq.poll();
            long currTime = curr[0];
            int u = (int) curr[1];
            int currPower = (int) curr[2];
            
            if (u == target) {
                return new long[]{currTime, currPower};
            }
            
            if (currPower <= maxPowerAt[u]) {
                continue;
            }
            maxPowerAt[u] = currPower;
            
            if (currPower < cost[u]) {
                continue;
            }
            
            int nextPower = currPower - cost[u];
            
            for (int[] edge : adj.get(u)) {
                int v = edge[0];
                long nextTime = currTime + edge[1];
                pq.offer(new long[]{nextTime, v, nextPower});
            }
        }
        
        return new long[]{-1, -1};
    }
}