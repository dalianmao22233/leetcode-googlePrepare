class Solution {
    public TreeNode trimBST(TreeNode root, int L, int R) {
        if (root == null) {
            return null;
        }
        if (root.val >= L && root.val <= R) {
            root.left = trimBST(root.left, L, R);
            root.right = trimBST(root.right, L, R);
        } else if (root.val < L) {
            // root.left = null;  // delete left part , 但是删的不彻底， 只是断开了left child. 用delete recursive的删比较好。
            delete(root.left);
            root = trimBST(root.right, L, R);
        } else if (root.val > R) {
            // root.right = null; // delete right part
            delete(root.right);
            root = trimBST(root.left, L, R);
        }
        return root;
    }
    public void delete(TreeNode root) {
        if (root == null) {
            return;
        }
        delete(root.left);
        delete(root.right);
        root.left = null;
        root.right = null;
        root = null;
    }
}
