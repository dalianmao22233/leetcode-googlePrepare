LCA, 可能有不存在target node的情况， 需要记录这个状态：
class ResultType {
    public boolean a_exist, b_exist;
    public TreeNode node;
    ResultType(boolean a, boolean b, TreeNode n) {
        a_exist = a;
        b_exist = b;
        node = n;
    }
}

public class Solution {
    /**
     * @param root The root of the binary tree.
     * @param A and B two nodes
     * @return: Return the LCA of the two nodes.
     */
    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode A, TreeNode B) {
        ResultType rt = helper(root, A, B);
        // 若 A 和 B 有一个不存在，则它们在二叉树上没有公共祖先
        if (rt.a_exist && rt.b_exist) {
            return rt.node;
        }
        return null;
    }

    public ResultType helper(TreeNode root, TreeNode A, TreeNode B) {
        if (root == null) {
            return new ResultType(false, false, null);
        }
            
        ResultType left_rt = helper(root.left, A, B);
        ResultType right_rt = helper(root.right, A, B);
       
        // 当前 root 对应的 a_exist 和 b_exist 的布尔值
        boolean a_exist = left_rt.a_exist || right_rt.a_exist || root == A;
        boolean b_exist = left_rt.b_exist || right_rt.b_exist || root == B;
        
        // 确定公共祖先的值
        // 要考虑到根结点是目标结点本身的情况
        if (root == A || root == B) {
            return new ResultType(a_exist, b_exist, root);
        }

        if (left_rt.node != null && right_rt.node != null) {
            return new ResultType(a_exist, b_exist, root);
        }
        if (left_rt.node != null) {
            return new ResultType(a_exist, b_exist, left_rt.node);
        }
        if (right_rt.node != null) {
            return new ResultType(a_exist, b_exist, right_rt.node);
        }

        return new ResultType(a_exist, b_exist, null);
    }
}
