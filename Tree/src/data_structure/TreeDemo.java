package data_structure;

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

    // 二叉树的效率
    // 从前面的大部分对树的操作来看，都需要从根节点到下一层一层的查找。
    // 一颗满树，每层节点数大概为2^(n-1)，那么最底层的节点个数比树的其它节点数多1，因此，查找、插入或删除节点的操作大约有一半都需要找到底层的节点，另外四分之一的节点在倒数第二层，依次类推。
    // 总共N层共有2^n-1个节点，那么时间复杂度为O(log^n),底数为2。
    // 在有1000000 个数据项的无序数组和链表中，查找数据项平均会比较500000 次，但是在有1000000个节点的二叉树中，只需要20次或更少的比较即可。
    // 有序数组可以很快的找到数据项，但是插入数据项的平均需要移动 500000 次数据项，在 1000000 个节点的二叉树中插入数据项需要20次或更少比较，在加上很短的时间来连接数据项。
    // 同样，从 1000000 个数据项的数组中删除一个数据项平均需要移动 500000 个数据项，而在 1000000 个节点的二叉树中删除节点只需要20次或更少的次数来找到他，然后在花一点时间来找到它的后继节点，一点时间来断开节点以及连接后继节点。
    // 所以，树对所有常用数据结构的操作都有很高的效率。
    // 遍历可能不如其他操作快，但是在大型数据库中，遍历是很少使用的操作，它更常用于程序中的辅助算法来解析算术或其它表达式。

    // 总结
    // 树是由边和节点构成，根节点是树最顶端的节点，它没有父节点；二叉树中，最多有两个子节点；
    // 某个节点的左子树每个节点都比该节点的关键字值小，右子树的每个节点都比该节点的关键字值大，
    // 那么这种树称为二叉搜索树，其查找、插入、删除的时间复杂度都为logN；
    // 可以通过前序遍历、中序遍历、后序遍历来遍历树，
    // 前序是根节点-左子树-右子树，
    // 中序是左子树-根节点-右子树，
    // 后序是左子树-右子树-根节点；
    // 删除一个节点只需要断开指向它的引用即可。

    // 基础：红黑树
    // 二叉搜索树对于某个节点而言，其左子树的节点关键值都小于该节点关键值，右子树的所有节点关键值都大于该节点关键值。二叉搜索树作为一种数据结构，其查找、插入和删除操作的时间复杂度都为O(logn),底数为2。但是我们说这个时间复杂度是在平衡的二叉搜索树上体现的，也就是如果插入的数据是随机的，则效率很高，但是如果插入的数据是有序的，比如从小到大的顺序【10,20,30,40,50】插入到二叉搜索树中：
    // 从大到小就是全部在左边，这和链表没有任何区别了，这种情况下查找的时间复杂度为O(N)，而不是O(logN)。当然这是在最不平衡的条件下，实际情况下，二叉搜索树的效率应该在O(N)和O(logN)之间，这取决于树的不平衡程度。
    // 那么为了能够以较快的时间O(logN)来搜索一棵树，我们需要保证树总是平衡的（或者大部分是平衡的），也就是说每个节点的左子树节点个数和右子树节点个数尽量相等。红-黑树的就是这样的一棵平衡树，对一个要插入的数据项（删除也是），插入例程要检查会不会破坏树的特征，如果破坏了，程序就会进行纠正，根据需要改变树的结构，从而保持树的平衡。

    // 1、红-黑树的特征
    // 有如下两个特征：
    // 1、 节点都有颜色；
    // 2、 在插入和删除的过程中，要遵循保持这些颜色的不同排列规则。
    // 第一个很好理解，在红-黑树中，每个节点的颜色或者是黑色或者是红色的。
    // 当然也可以是任意别的两种颜色，这里的颜色用于标记，我们可以在节点类Node中增加一个boolean型变量isRed，以此来表示颜色的信息。
    // 第二点，在插入或者删除一个节点时，必须要遵守的规则称为红-黑规则：

    // 1.每个节点不是红色就是黑色的；
    // 2.根节点总是黑色的；
    // 3.如果节点是红色的，则它的子节点必须是黑色的（反之不一定）,(也就是从每个叶子到根的所有路径上不能有两个连续的红色节点)；
    // 4.从根节点到叶节点或空子节点的每条路径，必须包含相同数目的黑色节点（即相同的黑色高度）。

    // 从根节点到叶节点的路径上的黑色节点的数目称为黑色高度，规则 4 另一种表示就是从根到叶节点路径上的黑色高度必须相同。
    // 注意：新插入的节点颜色总是红色的，这是因为插入一个红色节点比插入一个黑色节点违背红-黑规则的可能性更小，
    // 原因是插入黑色节点总会改变黑色高度（违背规则4），
    // 但是插入红色节点只有一半的机会会违背规则3（因为父节点是黑色的没事，父节点是红色的就违背规则3）。
    // 另外违背规则3比违背规则4要更容易修正。当插入一个新的节点时，可能会破坏这种平衡性，那么红-黑树是如何修正的呢？

    // 2、红-黑树的自我修正
    // 红-黑树主要通过三种方式对平衡进行修正，改变节点颜色、左旋和右旋。
    // 1、 改变节点颜色
    // 新插入的节点为15，一般新插入颜色都为红色，
    // 那么我们发现直接插入会违反规则3，改为黑色却发现违反规则4。
    // 这时候我们将其父节点颜色改为黑色，父节点的兄弟节点颜色也改为黑色。
    // 通常其祖父节点颜色会由黑色变为红色，但是由于是根节点，所以我们这里不能改变根节点颜色。

    // 2、 右旋
    // 首先要说明的是节点本身是不会旋转的，旋转改变的是节点之间的关系，
    // 选择一个节点作为旋转的顶端，如果做一次右旋，这个顶端节点会向下和向右移动到它右子节点的位置，
    // 它的左子节点会上移到它原来的位置。右旋的顶端节点必须要有左子节点。

    // 3、 左旋
    // 左旋的顶端节点必须要有右子节点。
    // 注意：我们改变颜色也是为了帮助我们判断何时执行什么旋转，
    // 而旋转是为了保证树的平衡。光改变节点颜色是不能起到任何作用的，
    // 旋转才是关键的操作，在新增节点或者删除节点之后，可能会破坏二叉树的平衡，
    // 那么何时执行旋转以及执行什么旋转，这是我们需要重点关注的。

    // 3、左旋和右旋
    // 4、插入操作
    // 和二叉树的插入操作一样，都是得先找到插入的位置，然后再将节点插入。先看看插入的前段代码：
    // 因为插入后可能会导致树的不平衡，insertFixUp(node) 方法里主要是分情况讨论，
    // 分析何时变色，何时左旋，何时右旋。我们先从理论上分析具体的情况，然后再看insertFixUp(node) 的具体实现。
    // 如果是第一次插入，由于原树为空，所以只会违反红-黑树的规则2，所以只要把根节点涂黑即可；

    // 如果插入节点的父节点是黑色的，那不会违背红-黑树的规则，什么也不需要做；
    // 但是遇到如下三种情况，我们就要开始变色和旋转了：
    // 1、 插入节点的父节点和其叔叔节点（祖父节点的另一个子节点）均为红色。
    // 2、 插入节点的父节点是红色的，叔叔节点是黑色的，且插入节点是其父节点的右子节点。
    // 3、 插入节点的父节点是红色的，叔叔节点是黑色的，且插入节点是其父节点的左子节点。

    // 5、删除操作
    // 上面探讨完了红-黑树的插入操作，接下来讨论删除，红-黑树的删除和二叉查找树的删除是一样的，只不过删除后多了个平衡的修复而已。我们先来回忆一下二叉搜索树的删除：
    // 1、 如果待删除的节点没有子节点，那么直接删除即可。
    // 2、 如果待删除的节点只有一个子节点，那么直接删掉，并用其子节点去顶替它。
    // 3、 如果待删除的节点有两个子节点，这种情况比较复杂：首先找出它的后继节点，然后处理“后继节点”和“被删除节点的父节点”之间的关系，最后处理“后继节点的子节点”和“被删除节点的子节点”之间的关系。每一步中也会有不同的情况。
    // 实际上，删除过程太复杂了，很多情况下会采用在节点类中添加一个删除标记，并不是真正的删除节点。详细的删除我们这里不做讨论。

    // 6、红黑树的效率
    // 红黑树的查找、插入和删除时间复杂度都为O(log2N)，额外的开销是每个节点的存储空间都稍微增加了一点，
    // 因为一个存储红黑树节点的颜色变量。插入和删除的时间要增加一个常数因子，因为要进行旋转，平均一次插入大约需要一次旋转，
    // 因此插入的时间复杂度还是O(log2N),(时间复杂度的计算要省略常数)，但实际上比普通的二叉树是要慢的。

    // 大多数应用中，查找的次数比插入和删除的次数多，所以应用红黑树取代普通的二叉搜索树总体上不会有太多的时间开销。
    // 而且红黑树的优点是对于有序数据的操作不会慢到O(N)的时间复杂度。

}

class RedBlack<T extends Comparable<T>> {

    // 插入操作
    // 和二叉树的插入操作一样，都是得先找到插入的位置，然后再将节点插入。先看看插入的前段代码：
    public void insert(RedBlackNode<T> root, T data) {
        RedBlackNode<T> node = new RedBlackNode<>(true, data, null, null, null);
        insert(root, node);
    }

    public void insert(RedBlackNode<T> root, RedBlackNode<T> node) {
        if (root == null) {
            root = node;
        }
        RedBlackNode<T> current = null; // 表示最后node的父节点
        RedBlackNode<T> x = root; // 用来向下搜索
        // 1.找到插入位置
        while (x != null) {
            current = x;
            int cmp = node.key.compareTo(x.key);
            if (cmp < 0) {
                x = x.left;
            } else {
                x = x.right;
            }
        }
        node.parent = current; // 找到了插入的位置，将当前current作为node的父节点
        // 2.接下来判断node是左子节点还是右子节点
        int cmp = node.key.compareTo(current.key);
        if (cmp < 0) {
            current.left = node;
        } else {
            current.right = node;
        }
        // 3.利用旋转操作将其修正为一颗红黑树
        insertFixUp(root, node);
    }

    // 3.利用旋转操作将其修正为一棵红黑树
    private void insertFixUp(RedBlackNode<T> root, RedBlackNode<T> node) {
        RedBlackNode<T> parent; // 父节点
        RedBlackNode<T> gparent; // 祖父节点
        // 需要修正的条件：父节点存在，且父节点的颜色是红色
        while (((parent = node.parent) != null) && parent.isRed) {
            gparent = parent.parent; // 获得祖父节点
            // 若父节点是祖父节点的左子节点
            if (parent == gparent.left) {
                // 获得叔叔节点
                RedBlackNode<T> uncle = gparent.right;
                // case1:叔叔节点也是红色
                if (uncle != null && uncle.isRed) {
                    // 把父节点和叔叔节点涂黑
                    parent.isRed = false;
                    uncle.isRed = false;
                    // 把祖父节点涂红
                    gparent.isRed = true;
                    // 把位置放到祖父节点处
                    node = gparent;
                    // 继续while循环，重新判断
                    continue;
                }
                // case2:叔叔节点不是红色，且当前节点是右子节点
                if (node == parent.right) {
                    // 从父节点处左旋
                    leftRotate(root, parent);
                    // 然后将父节点和自己调换一下，为下面右旋做准备
                    RedBlackNode<T> tmp = parent;
                    parent = node;
                    node = tmp;
                }
                // case3:叔叔节点不是红色，且当前节点是左子节点
                parent.isRed = false;
                gparent.isRed = true;
                rightRotate(root, gparent);
            } else {
                // 若父节点是祖父节点的右子节点，与上面的情况完全相反，本质是一样的
                RedBlackNode<T> uncle = gparent.left;
                // case1:叔叔节点也是红色的
                if (uncle != null && uncle.isRed) {
                    parent.isRed = false;
                    uncle.isRed = false;
                    gparent.isRed = true;
                    node = gparent;
                    continue;
                }
                // case2:叔叔节点不是红色，且当前节点是左子节点
                if (node == parent.left) {
                    rightRotate(root, parent);
                    RedBlackNode<T> tmp = parent;
                    parent = node;
                    node = tmp;
                }
                // case3:叔叔节点不是红色，且当前节点是右子节点
                parent.isRed = false;
                gparent.isRed = true;
                leftRotate(root, gparent);
            }
        }
        // 将根节点设置为黑色
        root.isRed = false;
    }

    /**
     * 左旋做了三件事：
     * 1. 将y的左子节点赋给x的右子节点,并将x赋给y左子节点的父节点(y左子节点非空时)
     * 2. 将x的父节点p(非空时)赋给y的父节点，同时更新p的子节点为y(左或右)
     * 3. 将y的左子节点设为x，将x的父节点设为y
     */
    private void leftRotate(RedBlackNode<T> root, RedBlackNode<T> x) {
        RedBlackNode<T> y = x.right;
        // 将 y 的左子节点赋给 x 的右子节点，
        x.right = y.left;
        // 并将 x 赋给 y 左子节点的父节点( y 左子节点非空时)
        if (y.left != null) {
            y.left.parent = x;
        }
        // 将 x 的父节点 p (非空时)赋给 y 的父节点，同时更新 p 的子节点为 y (左或右)
        y.parent = x.parent;
        if (x.parent == null) {
            // 如果 x 的父节点为空(即 x 为根节点)，则将 y 设为根节点
            root = y;
        } else {
            // 如果 x 是左子节点
            if (x == x.parent.left) {
                // 则也将 y 设为左子节点
                x.parent.left = y;
            } else {
                // 否则将 y 设为右子节点
                x.parent.right = y;
            }
        }
        // 将 y 的左子节点设为 x ，将 x 的父节点设为 y
        y.left = x;
        x.parent = y;
    }

    /**
     * 右旋做了三件事：
     * 1. 将x的右子节点赋给y的左子节点,并将y赋给x右子节点的父节点(x右子节点非空时)
     * 2. 将y的父节点p(非空时)赋给x的父节点，同时更新p的子节点为x(左或右)
     * 3. 将x的右子节点设为y，将y的父节点设为x
     */
    private void rightRotate(RedBlackNode<T> root, RedBlackNode<T> y) {
        // 1. 将y的左子节点赋给x的右子节点，并将x赋给y左子节点的父节点(y左子节点非空时)
        RedBlackNode<T> x = y.left;
        y.left = x.right;
        if (x.right != null) {
            x.right.parent = y;
        }
        // 2. 将x的父节点p(非空时)赋给y的父节点，同时更新p的子节点为y(左或右)
        x.parent = y.parent;
        if (y.parent == null) {
            // 如果y的父节点为空(即y为根节点)，则旋转后将x设为根节点
            root = x;
        } else {
            // 如果y是左子节点
            if (y == y.parent.left) {
                // 则将x也设置为左子节点
                y.parent.left = x;
            } else {
                // 否则将x设置为右子节点
                y.parent.right = x;
            }
        }
        // 3. 将x的左子节点设为y，将y的父节点设为y
        x.right = y;
        y.parent = x;
    }
}

// 1、 节点类
// 节点类和二叉树的节点类差不多，只不过在其基础上增加了一个 boolean 类型的变量来表示节点的颜色。
class RedBlackNode<T extends Comparable<T>> {
    boolean isRed; // 颜色
    T key;
    RedBlackNode<T> left; // 左子节点
    RedBlackNode<T> right; // 右子节点
    RedBlackNode<T> parent; // 父节点

    public RedBlackNode(boolean isRed, T key, RedBlackNode<T> left, RedBlackNode<T> right, RedBlackNode<T> parent) {
        this.isRed = isRed;
        this.key = key;
        this.left = left;
        this.right = right;
        this.parent = parent;
    }

    // 获得节点的关键值
    public T getKey() {
        return key;
    }

    // 打印节点的关键值和颜色信息
    public String toString() {
        return key + (this.isRed ? "R" : "B");
    }
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
        // 当删除的节点存在两个子节点，那么删除之后，两个子节点的位置我们就没办法处理了。
        // 既然处理不了，我们就想到一种办法，用另一个节点来代替被删除的节点，那么用哪一个节点来代替呢？

        // 我们知道二叉搜索树中的节点是按照关键字来进行排列的，某个节点的关键字次高节点是它的中序遍历后继节点。
        // 用后继节点来代替删除的节点，显然该二叉搜索树还是有序的。（这里用后继节点代替，如果该后继节点自己也有子节点，我们后面讨论。）

        // 那么如何找到删除节点的中序后继节点呢？其实我们稍微分析，
        // 这实际上就是要找比删除节点关键值大的节点集合中最小的一个节点，
        // 只有这样代替删除节点后才能满足二叉搜索树的特性。

        // 后继节点也就是：比删除节点大的最小节点。
        // 算法：程序找到删除节点的右节点，(注意这里前提是删除节点存在左右两个子节点，如果不存在则是删除情况的前面两种)，
        // 然后转到该右节点的左子节点，依次顺着左子节点找下去，最后一个左子节点即是后继节点；
        // 如果该右节点没有左子节点，那么该右节点便是后继节点。

        // 需要确定后继节点没有子节点，如果后继节点存在子节点，那么又要分情况讨论了。
        // 1、 后继节点是删除节点的右子节点
        // 这种情况简单，只需要将后继节点表示的子树移到被删除节点的位置即可！
        // 2、 后继节点是删除节点的右子节点的左子节点
        // 将后继节点替换删除节点
        // 3、 删除有必要吗？
        // 通过上面的删除分类讨论，我们发现删除其实是挺复杂的，
        // 那么其实我们可以不用真正的删除该节点，只需要在Node类中增加一个标识字段isDelete，
        // 当该字段为true时，表示该节点已经删除，反正没有删除。那么我们在做比如find()等操作的时候，
        // 要先判断isDelete字段是否为true。这样删除的节点并不会改变树的结构。
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