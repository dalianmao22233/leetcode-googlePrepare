其实分析下就是先序遍历，
只要是数的遍历就有递归和非递归的两种方法来求解，
首先来看递归版本的，思路是先利用DFS的思路找到最左子节点，然后回到其父节点，
把其父节点和右子节点断开，将原左子结点连上父节点的右子节点上，然后再把原右子节点连到新右子节点的右子节点上，然后再回到上一父节点做相同操作。

1. recursive：
The worst scenario is we have no right branches, then we have n left branches. 
In order to move left to right, so the worst case is o(n^2). 
The node will be visited is depend on how many left branches there are. not necessary right branches.
面试时写这个是最差的。。

class Solution {
    public void flatten(TreeNode root) {
        if (root==null) {
          return;
        }
        flatten(root.right);
        flatten(root.left);
        TreeNode right = root.right;
        root.right = root.left;
        root.left = null;
        while(root.right!=null){
            root = root.right;
        }
        root.right = right;
    }
}


用后序遍历  reverse postorder 更快！
the resulting sequence is traversed in the order of root -> left -> right, 
this solution construct the result working from the last to the first.
Which has visiting order of right -> left -> root. The reverse of the traversal ordering.

private TreeNode prev = null;

public void flatten(TreeNode root) {
    if (root == null)
        return;
    flatten(root.right);
    flatten(root.left);
    root.right = prev;
    root.left = null;
    prev = root;
}

2. iterative: 这个很好理解

从根节点开始出发，先检测其左子结点是否存在，如存在则将根节点和其右子节点断开，
将左子结点及其后面所有结构一起连到原右子节点的位置，
把原右子节点连到元左子结点最后面的右子节点之后。

class Solution {
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode cur = root;
        while (cur != null) {
            if (cur.left != null) {
                TreeNode p = cur.left;
                while (p.right != null) {
                    p = p.right;
                }
                p.right = cur.right;
                cur.right = cur.left;
                cur.left = null;
            }
            cur = cur.right;
        }
    }
}

或者：
这种可能是前序：
class Solution {
    public void flatten(TreeNode root) {
        if(root == null) return;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        
        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            
            if(node.right!=null) stack.push(node.right);
            if(node.left!=null) stack.push(node.left);
            
            if(!stack.isEmpty()){
                node.right = stack.peek();
            }
            node.left = null;
        }
    }
}
