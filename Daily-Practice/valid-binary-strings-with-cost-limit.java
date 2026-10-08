
class Solution {
    private List<String> result;
    private int n;
    private int k;

    public List<String> generateValidStrings(int n, int k) {
        this.result = new ArrayList<>();
        this.n = n;
        this.k = k;
        
        char[] path = new char[n];
        
        dfs(0, 0, path);
        
        return result;
    }

    private void dfs(int index, int cost, char[] path) {
        if (index == n) {
            result.add(new String(path));
            return;
        }

        path[index] = '0';
        dfs(index + 1, cost, path);

        boolean canPlaceOne = (index == 0 || path[index - 1] != '1') && (cost + index <= k);
        
        if (canPlaceOne) {
            path[index] = '1';
            dfs(index + 1, cost + index, path);
            
        }
    }
}