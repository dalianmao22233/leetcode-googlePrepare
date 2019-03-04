1. 利用BST的性质来做deserialize, 用当前字符与栈顶比较，
如果当前字符>栈顶，那需要成为最接近当前字符（<当前字符）的那个节点的右孩子。
反之是栈顶的左孩子。

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
            // sb.append("#,"); 
            // 这句不能加， 会报错
            return;
        }
        sb.append(String.valueOf(root.val));
        sb.append(",");
        
        dfs(root.left, sb);
        dfs(root.right, sb);
    }

    // Decodes your encoded data to tree.
    // [2,1,3]举例：root=2
    
    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) {
            return null;
        }
        Stack<TreeNode> s = new Stack<>();
        String[] str = data.split(",");
        if (str[0].equals("#")) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.valueOf(str[0]));
        s.push(root); // 2

        for (int i = 1; i < str.length; i++) {
            TreeNode cur = null;
            int val = Integer.valueOf(str[i]);  // 1
            
            while (!s.isEmpty() && val > s.peek().val) {  // 当前字符是否>栈顶，如果是，应该接到右节点
                cur = s.pop();
            }
            TreeNode newNode = new TreeNode(val);
            
            if (cur != null) {
                cur.right = newNode;
            } else {   // root.left = 1.连接上了左节点
                s.peek().left = newNode;
            }
            s.push(newNode);
        }
        return root;
    }
}


2. iterative来做deserialize:

// Decodes your encoded data to tree.
public TreeNode deserialize(String data) {
    if (data == null || data.length() == 0) {
        return null;
    }
    String[] nodes = data.split(" ");
    int[] index = new int[] {0};
    return deserialize(nodes, index, Integer.MAX_VALUE);
}

// pre-order来做遍历

private TreeNode deserialize(String[] nodes, int[] index, int max) { 

    // 因为没办法做全局变量（题里要求）， 所以用index[0]来传递Index。
    
    if (index[0] >= nodes.length || Integer.valueOf(nodes[index[0]]) >= max) {
        return null;
    }
    TreeNode root = new TreeNode(Integer.valueOf(nodes[index[0]++])); //相当于index++
    root.left = deserialize(nodes, index, root.val);
    root.right = deserialize(nodes, index, max);
    return root;
}
