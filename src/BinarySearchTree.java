public class BinarySearchTree<E extends Comparable<E>> {

    //Node class
    private class TreeNode {
        E value;
        TreeNode left;
        TreeNode right;

        public TreeNode(E value) {
            this.value = value;
        }
    }

    //Root node
    private TreeNode
            root;

    //Removes a value from BST
    public boolean remove(E value) {
        TreeNode parent = null;
        TreeNode node = root;
        boolean done = false;
        while (!done) {
            if (node == null){
                return false;
            }
            if (node.value.compareTo(value) < 0) {
                parent = node;
                node = node.right;
            } else if (node.value.compareTo(value) > 0) {
                parent = node;
                node = node.left;
            } else {
                done = true;
            }
        }
        // Case for no left child
        if (node.left == null) {
            if (parent == null) {
                root = node.right;
            } else {
                if (parent.value.compareTo(value) < 0) {
                    parent.right = node.right;
                } else {
                    parent.left = node.right;
                }
            }
        } else { // Case for left child
            TreeNode parentOfRight = node;
            TreeNode rightMost = node.left;
            while (rightMost.right != null) {
                parentOfRight = rightMost;
                rightMost = rightMost.right;
            }
            node.value = rightMost.value;
            if (parentOfRight.right == rightMost) {
                parentOfRight.right = rightMost.left;
            } else {
                parentOfRight.left = rightMost.left;
            }
        }
        return true;
    }

    //Inserts value into BST.
    public boolean insert(E value) {
        if (root == null) {
            root = new TreeNode(value);
        } else {
            TreeNode parent = null;
            TreeNode node = root;
            while (node != null) {
                parent = node;
                if ((node.value).equals(value)){
                    return false;
                }
                else if (node.value.compareTo(value) < 0) {
                    node = node.right;
                } else {
                    node = node.left;
                }
            }

            TreeNode newNode = new TreeNode(value);
            if (parent.value.compareTo(value) < 0) {
                parent.right = newNode;
            } else {
                parent.left = newNode;
            }
        }
        return true;
    }

    //Searches BST
    public boolean search(E value) {
        boolean found = false;
        TreeNode node = root;

        while (!found && node != null) {
            if (node.value.equals(value)) {
                found = true;
            } else if ((node.value).compareTo(value) < 0) {
                node = node.right;
            } else {
                node = node.left;
            }
        }
        return found;
    }

    //Helper method for displayInOrder method
    public void display(String message) {
        System.out.println(message);
        displayInOrder(root);
    }

    //Recursive method that displays the values of the nodes in order.
    private void displayInOrder(TreeNode node) {
        if (node == null) return;

        displayInOrder(node.left);
        System.out.println(node.value);
        displayInOrder(node.right);
    }

    //Helper method for returning the number of nodes.
    public int numberNodes(){
        return numberNodes(root);
    }

    //Recursive method for returning the number of nodes.
    private int numberNodes(TreeNode node){
        if (node == null){
            return 0;
        } else {
            return 1 + numberNodes(node.left) + numberNodes(node.right);
        }
    }

    //Helper method for returning the number of leaf nodes.
    public int numberLeafNodes(){
        return numberLeafNodes(root);
    }
    //Recursive method for returning the number of leaf nodes.
    private int numberLeafNodes(TreeNode node){
        int isLeafNode; //Value of 1 if it is a leaf node and 0 if not. Simplifies return statement.
        if (node == null){
            return 0;
        } else if (node.left == null && node.right == null) {
            isLeafNode = 1;
        } else {
            isLeafNode = 0;
        }
        return isLeafNode + numberLeafNodes(node.left) + numberLeafNodes(node.right);
    }

    //Helper method for returning the height.
    public int height(){
        return height(root);
    }
    //Recursive method for returning the height.
    private int height(TreeNode node){
        if (node == null){
            return 0;
        }
        if(node == root) {
            return Math.max(height(node.left), height(node.right));
        }
        else {
            return 1 + Math.max(height(node.left),height(node.right));
        }
    }
}