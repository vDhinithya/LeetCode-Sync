class SnapshotArray {

    public SnapshotArray(int length) {
        history = new ArrayList<>(length);
        currentSnapId =0;
        for (int i = 0; i < length; i++) {
            history.add(new ArrayList<>());
            history.get(i).add(new Pair(0, 0));
        }

    }
    
    public void set(int index, int val) {
        List<Pair> list = history.get(index);
        Pair last = list.get(list.size() - 1);
        if (last.snapId == currentSnapId) {
            last.val = val;
        } else {
            list.add(new Pair(currentSnapId, val));
        }
    }
    
    public int snap() {
        return currentSnapId++;
    }
    
    public int get(int index, int snap_id) {
        List<Pair> list = history.get(index);
        int low = 0;
        int high = list.size() - 1;
        int ans = 0;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (list.get(mid).snapId <= snap_id) {
                ans = list.get(mid).val;
                low = mid + 1; 
            } else {
                high = mid - 1;
            }
        }

        return ans;
    }

    private static class Pair {
        int snapId;
        int val;

        Pair(int snapId, int val) {
            this.snapId = snapId;
            this.val = val;
        }
    }
    private List<List<Pair>> history;
    private int currentSnapId;
}

/**
 * Your SnapshotArray object will be instantiated and called as such:
 * SnapshotArray obj = new SnapshotArray(length);
 * obj.set(index,val);
 * int param_2 = obj.snap();
 * int param_3 = obj.get(index,snap_id);
 */