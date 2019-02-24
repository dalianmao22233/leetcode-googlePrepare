The time complexity is O(mnlog(mn)) and space complexity is O(mn).
每个格子都要进出queue, mn个格子。 由于涉及到pq， log(mn). 

class Solution {
    class Cell {
        int row;
        int col;
        int height;
        public Cell(int row, int col, int height) {
            this.row = row;
            this.col = col;
            this.height = height;
        }
    }
    public int trapRainWater(int[][] heightMap) {
        if (heightMap == null || heightMap.length == 0 || heightMap[0].length == 0) {
            return 0;
        }
        int sum = 0;
        PriorityQueue<Cell> queue = new PriorityQueue<>(1, new Comparator<Cell>() {
            @Override
            public int compare(Cell a, Cell b) {
                return a.height-b.height;
            }
        });
        int m = heightMap.length, n = heightMap[0].length;
        boolean[][] visited = new boolean[m][n];
        
        // 四条边界上的cell全都加入到pq中
        
        for (int i = 0; i < m; i++) {
            visited[i][0] = true;
            visited[i][n-1] = true;
            queue.offer(new Cell(i, 0, heightMap[i][0]));
            queue.offer(new Cell(i, n-1, heightMap[i][n-1]));
        }
        
        for (int i = 0; i < n; i++) {
            visited[0][i] = true;
            visited[m-1][i] = true;
            queue.offer(new Cell(0, i, heightMap[0][i]));
            queue.offer(new Cell(m-1, i, heightMap[m-1][i]));
        }
        
        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        while (!queue.isEmpty()) {
            Cell cell = queue.poll();
            for (int[] dir : dirs) {
                int row = cell.row + dir[0];
                int col = cell.col + dir[1];
                if (row >= 0 && row < m && col >= 0 && col < n && !visited[row][col]) {
                    visited[row][col] = true;
                    sum += Math.max(0, cell.height-heightMap[row][col]);
                    // 因为做的是缩小一圈的新围墙, height = Math.max(cell.h, neighbor.h);
                    queue.offer(new Cell(row, col, Math.max(heightMap[row][col], cell.height))); 
                    //用更高的，可能以后的结构还会变, 往里面，很可能cell高度变化。
                    //https://github.com/awangdev/LintCode/blob/master/Java/Trapping%20Rain%20Water%20II.java
                }
            }
        }
        return sum;
    }
}
