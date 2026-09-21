import java.util.*;

class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();
        int startX = -1, startY = -1;
        List<int[]> litterList = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char ch = classroom[i].charAt(j);
                if (ch == 'S') {
                    startX = i;
                    startY = j;
                } else if (ch == 'L') {
                    litterList.add(new int[]{i, j});
                }
            }
        }

        int totalLitter = litterList.size();
        int fullMask = (1 << totalLitter) - 1;

        // Queue holds arrays: {r, c, mask, currentEnergy, steps}
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startX, startY, 0, energy, 0});

        // maxEnergy[r][c][mask] stores max remaining energy seen for that state
        int[][][] maxEnergy = new int[m][n][1 << totalLitter];
        for (int[][] mat : maxEnergy) {
            for (int[] row : mat) {
                Arrays.fill(row, -1);
            }
        }
        maxEnergy[startX][startY][0] = energy;

        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0], c = curr[1], mask = curr[2], e = curr[3], steps = curr[4];

            if (mask == fullMask) {
                return steps;
            }

            for (int[] dir : dirs) {
                int nr = r + dir[0];
                int nc = c + dir[1];

                if (nr >= 0 && nr < m && nc >= 0 && nc < n && classroom[nr].charAt(nc) != 'X') {
                    if (e - 1 < 0) continue; // Out of energy before reaching cell

                    int nextEnergy = e - 1;
                    int nextMask = mask;
                    char cell = classroom[nr].charAt(nc);

                    if (cell == 'R') {
                        nextEnergy = energy; // Reset energy to max capacity
                    } else if (cell == 'L') {
                        for (int i = 0; i < totalLitter; i++) {
                            if (litterList.get(i)[0] == nr && litterList.get(i)[1] == nc) {
                                nextMask |= (1 << i);
                                break;
                            }
                        }
                    }

                    if (nextEnergy > maxEnergy[nr][nc][nextMask]) {
                        maxEnergy[nr][nc][nextMask] = nextEnergy;
                        queue.add(new int[]{nr, nc, nextMask, nextEnergy, steps + 1});
                    }
                }
            }
        }

        return -1;
    }
}