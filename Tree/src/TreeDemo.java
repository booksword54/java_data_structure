public class TreeDemo {
    // 前面我们介绍数组的数据结构，我们知道对于有序数组，查找很快，并介绍可以通过二分法查找，但是想要在有序数组中插入一个数据项，就必须先找到插入数据项的位置，然后将所有插入位置后面的数据项全部向后移动一位，来给新数据腾出空间，平均来讲要移动N/2次，这是很费时的。同理，删除数据也是。（数组插入、删除慢）
    // 然后我们介绍了另外一种数据结构——链表，链表的插入和删除很快，我们只需要改变一些引用值就行了，但是查找数据却很慢了，因为不管我们查找什么数据，都需要从链表的第一个数据项开始，遍历到找到所需数据项为止，这个查找也是平均需要比较N/2次。
    // 那么我们就希望一种数据结构能同时具备数组查找快的优点以及链表插入和删除快的优点，于是 树 诞生了。

    // 1、树
    // 树（tree）是一种抽象数据类型，用来模拟具有树状结构性质的数据集合。它是由n（n>0）个有限节点通过连接它们的边组成一个具有层次关系的集合。把它叫做“树”是因为它看起来像一棵倒挂的树，也就是说它是根朝上，而叶朝下的。
    // 1、 节点：上图的圆圈，比如A,B,C等都是表示节点。节点一般代表一些实体，在java面向对象编程中，节点一般代表对象。
    // 2、 边：连接节点的线称为边，边表示节点的关联关系。一般从一个节点到另一个节点的唯一方法就是沿着一条顺着有边的道路前进。在Java当中通常表示引用。

    // 树有很多种，向上面的一个节点有大于两个的子节点的树，称为多路树，后面会讲解2-3-4树和外部存储都是多路树的例子。而每个节点最多只能有两个子节点的一种形式称为二叉树，这也是本篇博客讲解的重点。

    // 树的常用术语
    // 1、 路径：顺着节点的边从一个节点走到另一个节点，所经过的节点的顺序排列就称为“路径”。
    // 2、 根：树顶端的节点称为根。一棵树只有一个根，如果要把一个节点和边的集合称为树，那么从根到其他任何一个节点都必须有且只有一条路径。A是根节点。
    // 3、 父节点：若一个节点含有子节点，则这个节点称为其子节点的父节点；B是D的父节点。
    // 4、 子节点：一个节点含有的子树的根节点称为该节点的子节点；D是B的子节点。
    // 5、 兄弟节点：具有相同父节点的节点互称为兄弟节点；比如上图的D和E就互称为兄弟节点。
    // 6、 叶节点：没有子节点的节点称为叶节点，也叫叶子节点，比如上图的D、H、E、F、G都是叶子节点。
    // 7、 子树：每个节点都可以作为子树的根，它和它所有的子节点、子节点的子节点等都包含在子树中。
    // 8、 节点的层次：从根开始定义，根为第一层，根的子节点为第二层，以此类推。
    // 9、 深度：对于任意节点n,n的深度为从根到n的唯一路径长，根的深度为0；
    // 10、 高度：对于任意节点n,n的高度为从n到一片树叶的最长路径长，所有树叶的高度为0；

    // 2、二叉树
    // 二叉树：树的每个节点最多只能有两个子节点
    // 上图的第一幅图B节点有DEF三个子节点，就不是二叉树，称为多路树；而每个节点最多只有两个节点，是二叉树，并且二叉树的子节点称为“左子节点”和“右子节点”。上图的4、9分别是8的左子节点和右子节点。
    // 二叉树的重要特性：
    // 二叉树的第n层上节点数最多2^(n-1)。
    // 高度为n的二叉树中，最多有2^n-1个节点。

    // 满二叉树:
    // 除最后一层无任何子节点外，每一层上的所有结点都有两个子结点

    // 完全二叉树:
    // 完全二叉树是由满二叉树而引出来的。
    // 1、 满二叉树是一棵特殊的完全二叉树，但完全二叉树不一定是满二叉树；
    // 2、 在完全二叉树中，若某个节点没有左子树，则一定没有右子树；

    // 二叉搜索树
    // 它是一种节点值之间具有一定数量级次序的二叉树，对于树中每个节点：
    // 若其左子树存在，则其左子树中每个节点的值都不大于该节点值；
    // 若其右子树存在，则其右子树中每个节点的值都不小于该节点值。

    // 平衡二叉树定义(AVL)：
    // 平衡二叉树（Balanced Binary Tree）又被称为AVL树，且具有以下性质：
    // 它是一 棵空树或它的左右两个子树的高度差的绝对值不超过1，并且左右两个子树都是一棵平衡二叉树。
    // 这个方案很好的解决了二叉查找树退化成链表的问题，把插入，查找，删除的时间复杂度最好情况和最坏情况都维持在O(logN)。
    // 但是频繁旋转会使插入和删除牺牲掉O(logN)左右的时间，不过相对二叉查找树来说，时间上稳定了很多。

    // 红黑树
    // 红黑树的特性:
    // 1、 每个结点是黑色或者红色；
    // 2、 根结点是黑色；
    // 3、 每个叶子结点（NIL）是黑色[注意：这里叶子结点，是指为空(NIL或NULL)的叶子结点！]；
    // 4、 如果一个结点是红色的，则它的子结点必须是黑色的；
    // 5、 每个结点到叶子结点NIL所经过的黑色结点的个数一样的；
    // 红黑树和平衡二叉树的区别
    // 1、 红黑树放弃了追求完全平衡,追求大致平衡,在与平衡二叉树的时间复杂度相差不大的情况下,保证每次插入最多只需要三次旋转就能达到平衡,实现起来也更为简单；
    // 2、 平衡二叉树追求绝对平衡,条件比较苛刻,实现起来比较麻烦,每次插入新节点之后需要旋转的次数不能预知；

    // 遍历树
    // 遍历树是根据一种特定的顺序访问树的每一个节点。比较常用的有前序遍历，中序遍历和后序遍历。而二叉搜索树最常用的是中序遍历。
    // 1、 中序遍历:左子树——》根节点——》右子树
    // 2、 前序遍历:根节点——》左子树——》右子树
    // 3、 后序遍历:左子树——》右子树——》根节点
}

class BinarySearchTree implements Tree {

    @Override
    public Node find(Node root, Integer data) {
        Node current = root;
        while (current != null) {
            if (current.data > data) {
                // 当前值比查找值大，搜索左子树
                current = current.leftChild;
            } else if (current.data < data) {
                //当前值比查找值小，搜索右子树
                current = current.rightChild;
            }
            return current;
        }
        return null;
    }

    @Override
    public boolean insert(Node root, Integer data) {
        Node newNode = new Node(data);
        if (root == null) {
            // 当前树为空树，没有任何节点
            root = newNode;
            return true;
        }
        Node current = root;
        Node parentNode = null;
        while (current != null) {
            parentNode = current;
            if (current.data > data) {
                // 当前值比插入值大，搜索左子节点
                current = current.leftChild;
                if (current == null) {
                    // 左子节点为空，直接将新值插入到该节点
                    parentNode.leftChild = newNode;
                    return true;
                }
            } else {
                current = current.rightChild;
                if (current == null) {
                    // 右子节点为空，直接将新值插入到该节点
                    parentNode.rightChild = newNode;
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean delete(Node root, Integer data) {
        Node current = root;
        Node parent = root;
        boolean isLeftChild = false;
        // 查找删除值，找不到直接返回false
        while (current.data != data) {
            parent = current;
            if (current.data > data) {
                isLeftChild = true;
                current = current.leftChild;
            } else {
                isLeftChild = false;
                current = current.rightChild;
            }
            if (current == null) {
                return false;
            }
        }
        // 如果当前节点没有子节点
        if (current.leftChild == null && current.rightChild == null) {
            if (current == root) {
                root = null;
            } else if (isLeftChild) {
                parent.leftChild = null;
            } else {
                parent.rightChild = null;
            }
            return true;
        }

        // 当前节点有一个子节点
        if (current.leftChild == null) {
            if (current == root) {
                root = current.rightChild;
            } else if (isLeftChild) {
                parent.leftChild = current.rightChild;
            } else {
                parent.rightChild = current.rightChild;
            }
            return true;
        } else if (current.rightChild == null) {
            if (current == root) {
                root = current.leftChild;
            } else if (isLeftChild) {
                parent.leftChild = current.leftChild;
            } else {
                parent.rightChild = current.leftChild;
            }
            return true;
        }

        // 当前节点存在两个子节点
        Node successor = getSuccessor(current);
        if (current == root) {
            root = successor;
        } else if (isLeftChild) {
            parent.leftChild = successor;
        } else {
            parent.rightChild = successor;
        }
        successor.leftChild = current.leftChild;
        return false;
    }

    private Node getSuccessor(Node delNode) {
        Node successorParent = delNode;
        Node successor = delNode;
        Node current = delNode.rightChild;
        while (current != null) {
            successorParent = successor;
            successor = current;
            current = current.leftChild;
        }
        // 后继节点不是删除节点的右子节点，将后继节点替换删除节点
        if (successor != delNode.rightChild) {
            successorParent.leftChild = successor.rightChild;
            successor.rightChild = delNode.rightChild;
        }
        return successor;
    }

    public Node findMax(Node root) {
        Node current = root;
        Node maxNode = current;
        while (current != null) {
            maxNode = current;
            current = current.rightChild;
        }
        return maxNode;
    }

    public Node findMin(Node root) {
        Node current = root;
        Node minNode = current;
        while (current != null) {
            minNode = current;
            current = current.leftChild;
        }
        return minNode;
    }
}

class Node {
    Integer data; // 节点数据
    Node leftChild; // 左子节点的引用
    Node rightChild; // 右子节点的应用

    public Node(Integer data) {
        this.data = data;
    }
}

interface Tree {
    // 查找节点
    Node find(Node root, Integer data);

    // 插入新节点
    boolean insert(Node root, Integer data);

    // 删除节点
    boolean delete(Node root, Integer data);
}