// Very basic (generic) BinarySearchTree. Not self-balancing, handles duplicates by storing number of items.
package BinarySearchTree;

public class BinarySearchTree<T extends Comparable<T>> {
    // Including these to highlight the root when printing using toString().
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_UNDERLINE = "\u001B[4m";

    // A node contains its data, the multiplicity of that data, and parent,
    // leftChild, and rightChild pointers
    private class Node<E extends Comparable<E>> {
        int multiplicity;
        E data;
        Node<E> parent;
        Node<E> leftChild;
        Node<E> rightChild;

        Node(E data) {
            this(data, null);
        }

        Node(E data, Node<E> parent) {
            this(data, parent, null, null);
        }

        Node(E data, Node<E> parent, Node<E> leftChild, Node<E> rightChild) {
            this.data = data;
            this.parent = parent;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
            this.multiplicity = 1;
        }

        boolean isLeaf() {
            return (leftChild == null) && (rightChild == null);
        }

        // Adds node to the correct child position (left/right)
        void newChild(Node<E> newChildNode) {
            if (newChildNode.data.compareTo(data) <= 0) {
                if (leftChild != null) { // if we already have a child here, throw an exception!
                    throw new IllegalArgumentException("Can't have a new left child!");
                }
                leftChild = newChildNode;
            } else {
                if (rightChild != null) {
                    throw new IllegalArgumentException("Can't have a new right child!");
                }
                rightChild = newChildNode;
            }
        }

        E delete() {
            // If we have multiple of this node,
            // just decrement the multiplicity.
            if (multiplicity > 1) {
                multiplicity--;
                return data;
            }

            E temp = data;

            if (isLeaf()) { // If no child nodes,
                if (parent != null) { // just null it out.
                    if (data.compareTo(parent.data) <= 0) {
                        parent.leftChild = null;
                    } else {
                        parent.rightChild = null;
                    }
                }
            } else if (leftChild == null) { // if only a right child,
                if (parent != null) { // re-parent right child with its grandparent
                    parent.newChild(rightChild);
                }
            } else if (rightChild == null) { // similar to previous case
                if (parent != null) {
                    parent.newChild(leftChild);
                }
            } else { // if node has both children
                Node<E> successorNode = successor();

                // Replace node with its successor
                data = successorNode.data;
                multiplicity = successorNode.multiplicity;

                if (successorNode.parent != this) { // if not the parent of successor,
                    // replace its parent's left child with the successor's right child.
                    successorNode.parent.leftChild = successorNode.rightChild;
                } else { // if not is the parent, replace right child with successor's
                    rightChild = successorNode.rightChild;
                }
            }

            return temp;
        }

        // Returns the minimum-value node in this node's subtree.
        Node<E> minNode() {
            if (leftChild == null) {
                return this;
            }

            Node<E> minNode = leftChild;
            while (minNode.leftChild != null) {
                minNode = minNode.leftChild;
            }

            return minNode;
        }

        // Returns the node that comes next in order of their data.
        Node<E> successor() {
            if (rightChild != null) {
                return rightChild.minNode();
            }

            Node<E> currentNode = this;

            while (currentNode.parent != null &&
                    currentNode == currentNode.parent.rightChild) {
                currentNode = currentNode.parent;
            }

            return currentNode.parent;
        }

        // Returns the maximum-value node in this node's subtree.
        Node<E> maxNode() {
            if (rightChild == null) {
                return this;
            }

            Node<E> maxNode = rightChild;
            while (maxNode.rightChild != null) {
                maxNode = maxNode.rightChild;
            }

            return maxNode;
        }

        // Returns the node that is before this node in order of their data.
        Node<E> predecessor() {
            if (leftChild != null) {
                return leftChild.maxNode();
            }

            Node<E> currentNode = this;

            while (currentNode.parent != null &&
                    currentNode == currentNode.parent.leftChild) {
                currentNode = currentNode.parent;
            }

            return currentNode.parent;
        }
    }

    Node<T> root;
    int size;

    BinarySearchTree() {
        size = 0;
        root = null;
    }

    BinarySearchTree(T data) {
        size = 1;
        root = new Node<>(data);
    }

    private Node<T> findNode(T data) {
        if (isEmpty()) {
            return null;
        }

        Node<T> previousNode = null;
        Node<T> currentNode = root;

        while (currentNode != null) {
            previousNode = currentNode;

            if (data.compareTo(currentNode.data) > 0) {
                currentNode = currentNode.rightChild;
            } else if (data.compareTo(currentNode.data) < 0) {
                currentNode = currentNode.leftChild;
            } else { // If we've found the node, return it.
                return currentNode;
            }
        }

        // If we didn't find a node with this data, return its would-be parent.
        return previousNode;
    }

    private String inOrder(Node<T> node) {
        if (node == null) {
            return "";
        }

        StringBuilder result = new StringBuilder();

        result.append(inOrder(node.leftChild));

        int mult = node.multiplicity;

        while (mult-- > 0) {
            if (node == root) { // Highlighting root node!
                result.append(ANSI_GREEN).append(ANSI_UNDERLINE);
            }
            result.append(node.data);

            if (node == root) {
                result.append(ANSI_RESET);
            }
            result.append(" ");
        } // Replace above with result.append(node.data).append(" ");
          // to remove highlighting.

        result.append(inOrder(node.rightChild));

        return result.toString();
    }

    public boolean isEmpty() {
        return (root == null);
    }

    public void insert(T data) {
        if (data == null) {
            return;
        }

        size++;

        if (isEmpty()) {
            root = new Node<>(data);
            return;
        }

        Node<T> currentNode = findNode(data);

        // If we found a node with this data already, increase multiplicity
        if (data.equals(currentNode.data)) {
            currentNode.multiplicity++;
        } else { // else, make new child node
            currentNode.newChild(new Node<>(data, currentNode));
        }
    }

    public boolean contains(T data) {
        if (isEmpty() || data == null) {
            return false;
        }

        // Try to find the node.
        Node<T> currentNode = findNode(data);

        // Return whether the node found actually has the data or not.
        return data.equals(currentNode.data);
    }

    public T remove(T data) {
        if (isEmpty()) {
            return null;
        }

        // Try to find the node.
        Node<T> currentNode = findNode(data);

        // If we found it, delete it.
        if (data.equals(currentNode.data)) {
            return currentNode.delete();
        }

        return null;
    }

    public int getSize() {
        return size;
    }

    public String toString() {
        return inOrder(root);
    }

    public T successor(T data) {
        Node<T> currentNode = findNode(data);

        if (currentNode == null) {
            return null;
        }

        if (data.equals(currentNode.data)) {
            return currentNode.successor().data;
        }

        if (data.compareTo(currentNode.data) > 0) {
            if (currentNode == currentNode.parent.leftChild) {
                return currentNode.parent.data;
            } else {
                while (currentNode.parent != null && currentNode == currentNode.parent.rightChild) {
                    currentNode = currentNode.parent;
                }

                if (data.compareTo(currentNode.data) > 0) {
                    return null;
                }

                return currentNode.data;
            }
        } else {
            return currentNode.data;
        }
    }

    public T predecessor(T data) {
        Node<T> currentNode = findNode(data);

        if (currentNode == null) {
            return null;
        }

        if (data.equals(currentNode.data)) {
            return currentNode.predecessor().data;
        }

        if (data.compareTo(currentNode.data) < 0) {
            if (currentNode == currentNode.parent.rightChild) {
                return currentNode.parent.data;
            } else {
                while (currentNode.parent != null && currentNode == currentNode.parent.leftChild) {
                    currentNode = currentNode.parent;
                }

                if (data.compareTo(currentNode.data) < 0) {
                    return null;
                }

                return currentNode.data;
            }
        } else {
            return currentNode.data;
        }
    }

    public T getRoot() {
        return root.data;
    }

    public void clear() {
        size = 0; // Set the size to 0.
        root = null; // Set the root to null
        // The garbage collector will (eventually) clear things up.
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer> myBST = new BinarySearchTree<>(5);

        myBST.insert(2);
        myBST.insert(7);

        myBST.insert(-1);
        myBST.insert(9);

        myBST.insert(3);
        myBST.insert(6);
        myBST.insert(3);
        myBST.insert(3);

        System.out.println(myBST);

        myBST.remove(7);

        System.out.println(myBST);

        System.out.println("The current root value is " + myBST.getRoot());

        myBST.remove(5);

        System.out.println("The current root value is " + myBST.getRoot());

        System.out.println(myBST);

        System.out.println("The successor of 0 is " + myBST.successor(0) + ".");

        System.out.println("The predecessor of 3 is " + myBST.predecessor(3) + ".");

        System.out.println("The successor of 3 is " + myBST.successor(3) + ".");

        System.out.println("The predecessor of 7 is " + myBST.predecessor(7) + ".");

        Integer tooBig = myBST.successor(100);

        if (tooBig == null) {
            System.out.println("There is no successor of 100.");
        }

        Integer tooSmall = myBST.predecessor(-3);

        if (tooSmall == null) {
            System.out.println("There is no predecessor of -3.");
        }

        myBST.clear();

        System.out.println(myBST + "My BST has " + myBST.getSize() + " nodes.");
    }
}
