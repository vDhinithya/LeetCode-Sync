class Solution {
    public boolean canReach(int[] arr, int start) {
        boolean [] visited = new boolean[arr.length];
        return dfs(arr,start, visited);
    }
    private boolean dfs(int[] arr, int curIndx, boolean[] visited){
        if(curIndx<0|| curIndx >= arr.length||visited[curIndx]){
            return false;
        }
        if(arr[curIndx]==0){
            return true;
        }
        visited[curIndx]=true;

        int jumpDist = arr[curIndx];
        return dfs(arr,curIndx+jumpDist, visited)||dfs(arr,curIndx-jumpDist,visited);
    }
}