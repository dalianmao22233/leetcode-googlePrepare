1. iterative: 很好理解，就是个BFS.  Time:O(n), Space:O(biggest level width)
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "#";
        }
        // bfs search
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur == null) {
                sb.append("#");
            } else {
                sb.append(String.valueOf(cur.val));
                
                queue.offer(cur.left);
                queue.offer(cur.right);
            }
            sb.append(",");
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) {
            return null;
        }
        String[] str = data.split(",");
        if (str[0].equals("#")) {
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.valueOf(str[0]));
        queue.offer(root);
        int i = 1;
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (i < str.length && !str[i].equals("#")) {
                cur.left = new TreeNode(Integer.valueOf(str[i]));
                queue.offer(cur.left);
                
            }
            i++;
            if (i < str.length && !str[i].equals("#")) {
                cur.right = new TreeNode(Integer.valueOf(str[i]));
                queue.offer(cur.right);
            }
            i++;
        }
        return root;
    }
}


2. recursive：Time:O(n), Space: O(height)，递归深度是height，worst case O(n)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null ) {
            return "#";
        }
        StringBuilder sb = new StringBuilder();
        dfs(root, sb);
        return sb.toString();
    }
    public void dfs(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("#,");
            return;
        }
        sb.append(String.valueOf(root.val));
        sb.append(",");
        
        dfs(root.left, sb);
        dfs(root.right, sb);
    }
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) {
            return null;
        }
        String[] str = data.split(",");
        if (str[0].equals("#")) {
            return null;
        }
        return dfs(str);
    }
    int index = 0;
    public TreeNode dfs(String[] str) {
        if (index >= str.length || str[index].equals("#")) {
            index++;
            return null;
        }
        TreeNode root = new TreeNode(Integer.valueOf(str[index]));
        index++;
        root.left = dfs(str);
        root.right = dfs(str);
        return root;
        
    }
}
