牛客网 剑指Offer

# JZ 1~20

## JZ1   二维数组中查找

在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。

```java
// 针对 每行都排序且只有第一列排序 情况: 
public boolean Find(int target, int [][] array) {
    if(array == null || array.length == 0 || array[0].length == 0 || array[0][0] > target)
        return false;
    // 二分查找第一列,找到第一个大于目标值的行号(相等直接返回)
    int l = 0, r = array.length - 1;
    while (l <= r) {
        int mid = l + (r - l) / 2;
        if (array[mid][0] == target)
            return true;
        if (array[mid][0] > target)
            r = mid - 1;
        else
            l = mid + 1;
    }
    // 遍历第一行到该行
    for (int i = l - 1;i >= 0;i--) {
        // 对每一行二分查找目标值
        int left = 0, right = array[0].length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (array[i][mid] == target)
                return true;
            if (array[i][mid] > target)
                right = mid - 1;
            else
                left = mid + 1;
        }
    }
    return false;
}
```

```java
// 二分法, 从左下角(右上角思路类似)开始, 如果比目标值大,则往上走; 相等,直接返回; 小于,往右走
public boolean Find(int target, int [][] array) {
    if (array == null)
        return false;
    int rows = array.length;
    if (rows == 0)
        return false;
    int cols = array[0].length;
    if (cols == 0)
        return false;
    int row = rows - 1;
    int col = 0;
    while (row >= 0 && col < cols) {
        if (array[row][col] == target)
            return true;
        if (array[row][col] < target)
            col++;
        else
            row--;
    }
    return false;
}
```



## JZ2   替换空格

请实现一个函数，将一个字符串中的每个空格替换成“%20”。例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。

```java

public String replaceSpace(StringBuffer str) {
    // return str.toString().replaceAll(" ", "%20"); // 调用 String.replaceAll() 方法
    int len = str.length();
    StringBuilder sb = new StringBuilder();
    for (int i = 0;i < len;i++) {
        char c = str.charAt(i);
        sb.append(c != ' ' ? c : "%20");
    }
    return sb.toString();
}
```



## JZ3   从头到尾打印链表

输入一个链表，按链表从尾到头的顺序返回一个ArrayList。

```java
// 将所有数据顺序添加到 ArrayList, 通过 Collections.reverse() 方法反转 ArrayList
public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
     ArrayList<Integer> list = new ArrayList<>();
     ListNode p = listNode;
     while (p != null) {
         list.add(p.val);
         p = p.next;
     }
     Collections.reverse(list);
     return list;
 }
```

```java
// 通过递归逆序添加到 ArrayList
ArrayList<Integer> list = new ArrayList<>();
public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
    if (listNode != null) {
        printListFromTailToHead(listNode.next);
        list.add(listNode.val);
    }
    return list;
}
```

```java
// 通过栈逆序添加到 ArrayList
public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
    ArrayList<Integer> list = new ArrayList<>();
    Stack<Integer> stack = new Stack<>();
    ListNode p = listNode;
    while (p != null) {
        stack.push(p.val);
        p = p.next;
    }
    while (!stack.isEmpty()) {
        list.add(stack.pop());
    }
    return list;
}
```



## JZ4   重建二叉树

输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。

```java
public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
    return r(pre, in, 0, pre.length - 1, 0, in.length - 1);
}
// 前序遍历第一个为根节点, 找到中序遍历的根节点位置, 左边为左子树, 右边为右子树, 递归左右子树
public TreeNode r(int[] pre, int[] in, int pl, int pr, int il, int ir) {
    if(pl > pr || il > ir)
        return null;
    TreeNode root = new TreeNode(pre[pl]);
    int x = il;
    for (int i = il;i <= ir;i++) {
        if (in[i] == pre[pl]) {
            x = i;
            break;
        }
    }
    root.left = x == il ? null : r(pre, in, pl + 1, pl + x - il, il, x - 1);
    root.right = x == ir ? null : r(pre, in, pl + x - il + 1, pr, x + 1, ir);
    return root;
}
```



## JZ5   用两个栈实现队列

用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。

```java
Stack<Integer> stack1 = new Stack<Integer>();
Stack<Integer> stack2 = new Stack<Integer>();

public void push(int node) {
    stack1.push(node); // 将数据压入 stack1
}

public int pop() {
    // 如果两个栈都没有数据, 则返回 0
    if (stack1.isEmpty() && stack2.isEmpty())
            return 0;
    if (stack2.isEmpty()) { // 如果 stack2 没有数据, 则从 stack1 中获取数据
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
    }
    return stack2.pop(); // 从 stack2 弹出数据
}
```



## JZ6   旋转数组的最小数字

把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。

输入一个非递减排序的数组的一个旋转，输出旋转数组的最小元素。

例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。

NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。

```java
// 原数组为升序(元素可相等)数组
public int minNumberInRotateArray(int [] array) {
    if(array == null || array.length == 0)
        return 0;
    int l = 0, r = array.length - 1;
    while (l < r) { // 二分查找, l == r 时比较无效
        int mid = l + (r - l) / 2;
        if(array[l] == array[r]) { // 避免全相等数组 出现死循环
            l++;
        } else if (array[mid] > array[r]) { // 如果 mid > r, 则目标出现在区间 (mid, r]
            if (array[mid] > array[mid + 1]) { //如果 mid+1 < mid, 则 mid+1 为转择点
                return array[mid + 1];
            }
            l = mid + 1; //缩小区间
        } else if (array[mid] < array[l]) { // 如果 l > mid, 则目标出现在区间 (l, mid]
            if (array[mid] < array[mid - 1]) { //如果 mid-1 < mid, 则 mid 为转择点
                return array[mid];
            }
            r = mid - 1; //缩小区间
        }
    }
    return 0;
}
```



## JZ7   斐波那切数列

大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第n项（从0开始，第0项为0，第1项是1）。 n<=39

```java
// 动态规划
public int Fibonacci(int n) {
    int a = 0, b = 1;
    while (n-- > 0) {
        b = a + b;
        a = b - a;
    }
    return a;
}
```



## JZ8   跳台阶

一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）。

```java
// 递归
public int JumpFloor(int target) {
    if(target <= 2) 
        return target;
    return JumpFloor(target - 1) + JumpFloor(target - 2);
}
```

```java
// 斐波那切数列, 动态规划
public int JumpFloor(int target) {
    int a = 1, b = 1;
    while (target-- > 0) {
        b = a + b;
        a = b - a;
    }
    return a;
}
```



## JZ9   变态跳台阶

一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。求该青蛙跳上一个n级的台阶总共有多少种跳法。

```java
// 通过规律发现结果为 2^(n-1)
public int JumpFloorII(int target) {
    return 1 << (target-1);
}
```

```java
// 动态规划, 每多一阶台阶 = 前一阶多走一个台阶 + 一步直接越过所有台阶
public int JumpFloorII(int target) {
    int a = 1; int b = 1;
    for(int i = 2; i <= target; i++){
        a = b + 1;
        b += a;
    }
    return a;
}
```



## JZ10  矩阵覆盖

我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形。请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，总共有多少种方法？

比如n=3时，2*3的矩形块有3种覆盖方法

```java
// 斐波那切数列, 动态规划
public int RectCover(int target) {
    if(target <= 0) return 0;
    int a = 1, b = 1;
    while (target-- > 0) {
        b = a + b;
        a = b - a;
    }
    return a;
}
```



## JZ11  二进制中1的个数

输入一个整数，输出该数32位二进制表示中1的个数。其中负数用补码表示。

```java
// 转成二进制, 统计1的个数
public int NumberOf1(int n) {
    if(n == 0) return 0;
    if(n == Integer.MIN_VALUE) return 1;
    int count = 0;
    byte[] arr = new byte[32];
    arr[31] = (byte)(n < 0 ? 1 : 0);
    n = Math.abs(n);
    int len = 0;
    while(n > 0) {
        arr[len++] = (byte)(n & 1);
        n /= 2;
    }
    if(arr[31] == 1) {
        for (int i = 0;i < 31;i++)
            arr[i] = (byte)(~arr[i] + 2);
        int x = 0;
        while (arr[x] != 0) x++;
        count = 32 - len;
        for(int i = x+1;i <= len;i++)
            count += arr[i];
    } else {
        for (int i = 0;i < len;i++)
            count += arr[i];
    }
    return count;
}
```

```java
// 技巧法
public int NumberOf1(int n) {
    int count = 0;
    while (n != 0) {
        count++;
        n &= (n-1); //把最右边的 1 去除
    }
    return count;
}
```



## JZ12  数值的整数次方

给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。

保证base和exponent不同时为0

```java
// exponent 为负数时, base = 1/base, 循环累积 abs(exponent) 次
public double Power(double base, int exponent) {
    if(base == 0 || exponent == 1) return base;
    if(base == 1 || exponent == 0) return 1;
    double sum = 1.0d;
    if(exponent < 0) {
        base = 1 / base;
        exponent = -exponent;
    }
    while (exponent-- > 0)
        sum *= base;
    return sum;
}
```



## JZ13 调整数组顺序使奇数在偶数前面

输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分，所有的偶数位于数组的后半部分，并保证奇数和奇数，偶数和偶数之间的相对位置不变。

```java
// 使用额外空间存放奇数偶数, 然后赋值回去
public void reOrderArray(int [] array) {
    int[] a = new int[array.length];
    int[] b = new int[array.length];
    int la = 0, lb = 0;
    for (int x: array) {
        if(x % 2 == 0)
            b[lb++] = x;
        else
            a[la++] = x;
    }
    for (int i = 0;i < la;i++)
        array[i] = a[i];
    for (int i = 0; i < lb; i++)
        array[la + i] = b[i];
}
```

```java
// 遇到奇数, 则将奇数往前放入, 该奇数之前的数往后移动
public void reOrderArray(int... array) {
    int x = 0;
    for(int i = 0;i < array.length;i++) {
        if(array[i] % 2 == 1) {
            int temp = array[i];
            for(int j = i;j > x;j--)
                array[j] = array[j - 1];
            array[x++] = temp;
        }
    }
}
```



## JZ14  链表中倒数第K个节点

输入一个链表，输出该链表中倒数第k个结点。

```java
// 将所有节点压入栈中, 返回弹出的第 K 个
public ListNode FindKthToTail(ListNode head,int k) {
    if(k <= 0) return null;
    Stack<ListNode> stack = new Stack<>();
    ListNode p = head;
    while (p != null) {
        stack.push(p);
        p = p.next;
    }
    if(stack.size() < k) return null;
    while (--k > 0) {
        stack.pop();
    }
    return stack.peek();
}
```

```java
// p,q = head, p 遍历到第 K 个节点, 然后 p,q 继续遍历直到 p 到链尾, 最后返回 q
public ListNode FindKthToTail(ListNode head,int k) {
    if(k == 0) return null;
    ListNode p = head;
    while (p != null && k-- > 0) {
        p = p.next;
    }
    if(k > 0) return null;
    ListNode q = head;
    while(p != null) {
        p = p.next;
        q = q.next;
    }
    return q;
}
```



## JZ15  反转链表

输入一个链表，反转链表后，输出新链表的表头。

```java
// 将所有节点顺序压入栈中, 弹出节点链接起来, 返回第一个退栈节点
public ListNode ReverseList(ListNode head) {
    if(head == null) return null;
    Stack<ListNode> stack = new Stack<>();
    ListNode p = head;
    while (p != null) {
        stack.push(p);
        p = p.next;
    }
    ListNode newHead = stack.pop();
    p = newHead;
    while (!stack.isEmpty()) {
        p.next = stack.pop();
        p = p.next;
    }
    p.next = null;
    return newHead;
}
```

```java
// 遍历链表, 将遍历节点指向前一个节点
public ListNode ReverseList(ListNode head) {
    if (head == null)
        return null;
    ListNode p = null, q = null;
    while (head != null) {
        p = head.next; // 记录下一个节点
        head.next = q; // 当前节点指向前一个节点
        q = head; // 记录当前节点
        head = p; // 进入下一节点
    }
    return q;
}
```



## JZ16  合并两个排序的链表

输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。

```java
// 双指针遍历两个链表
public ListNode Merge(ListNode list1,ListNode list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;
        ListNode head = null;
        ListNode p = list1;
        ListNode q = list2;
        ListNode node = null;
        if (list1.val < list2.val) {
            head = list1;
            p = p.next;
        } else {
            head = list2;
            q = q.next;
        }
        node = head;
        while (p != null || q != null) {
            if (p == null) {
                node.next = q;
                break;
            }
            if(q == null) {
                node.next = p;
                break;
            }
            if(p.val < q.val) {
                node.next = p;
                p = p.next;
            } else {
                node.next = q;
                q = q.next;
            }
            node = node.next;
        }
        return head;
    }
```

```java
// 递归 返回 val 小的节点
public ListNode Merge(ListNode list1,ListNode list2) {
    if (list1 == null) return list2;
    if (list2 == null) return list1;
    if(list1.val < list2.val) {
        list1.next = Merge(list1.next, list2);
        return list1;
    } else {
        list2.next = Merge(list1, list2.next);
        return list2;
    }
}
```



## JZ17  数的子结构

输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构）

```java
public boolean HasSubtree(TreeNode root1,TreeNode root2) {
	if(root1 == null || root2 == null)
        return false;
    // 当 root1 与 root2 的值相同时, 递归左右子树, 当都返回 true 时, 则存在
    if(root1.val == root2.val) { // root2 的左,右子树不存在时, 视为 true        
        if((root2.left == null || HasSubtree(root1.left, root2.left)) 
           && (root2.right == null || HasSubtree(root1.right, root2.right)))
            return true;
    }
    //继续递归左右子树, 任意一个子树满足时, 返回 true
    return HasSubtree(root1.left, root2) || HasSubtree(root1.right, root2);
}
```



## JZ18  二叉树的镜像

操作给定的二叉树，将其变换为源二叉树的镜像。

```java
public void Mirror(TreeNode root) {
    if (root == null)
        return;
    // 交换左右节点
    TreeNode temp = root.left; root.left = root.right; root.right = temp;
    // 递归左右子树
    Mirror(root.left); 
    Mirror(root.right);
}
```



## JZ19  顺时针打印矩阵

输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，例如，如果输入如下4 X 4矩阵： 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 则依次打印出数字1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.

```java
// 循环 l -> r: [t][i], t -> b: [i][r], r -> l: [b][i], b -> t: [i][l]
public ArrayList<Integer> printMatrix(int [][] matrix) {
    if(matrix == null || matrix.length == 0 || matrix[0].length == 0) 
        return new ArrayList<>();
    int l = 0, t = 0, b = matrix.length - 1, r = matrix[0].length - 1;
    ArrayList<Integer> list = new ArrayList<>(b * r);
    while (true) {
        for (int i = l;i <= r;i++)
            list.add(matrix[t][i]);
        if(++t > b) break;
        for (int i = t;i <= b;i++)
            list.add(matrix[i][r]);
        if(--r < l) break;
        for (int i = r;i >= l;i--)
            list.add(matrix[b][i]);
        if(--b < t) break;
        for (int i = b;i >= t;i--)
            list.add(matrix[i][l]);
        if(++l > r) break;
    }
    return list;
}
```



## JZ20  包含 min 函数的栈

定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的min函数 (时间复杂度应为O(1))。

```java
Stack<Integer> stack = new Stack<>();
Stack<Integer> min = new Stack<>();

public void push(int node) {
    stack.push(node);
    // 将最小值压入栈中
    min.push((min.isEmpty() || node <= min.peek()) ? node : min.peek());
}

public void pop() {
    stack.pop();
    min.pop();
}

public int top() {
    return stack.peek();
}

public int min() {
    return min.peek();
}
```



# JZ 21~40

## JZ21  栈的压入,弹出序列

输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否可能为该栈的弹出顺序。假设压入栈的所有数字均不相等。例如序列1,2,3,4,5是某栈的压入顺序，序列4,5,3,2,1是该压栈序列对应的一个弹出序列，但4,3,5,1,2就不可能是该压栈序列的弹出序列。（注意：这两个序列的长度是相等的）

```java
public boolean IsPopOrder(int [] pushA,int [] popA) {
    Stack<Integer> stack = new Stack<>();
    int k = 0; // 弹栈序列索引
    for (int i = 0;i <= pushA.length;i++) {
        while (!stack.isEmpty() && stack.peek() == popA[k]) { //如果栈顶在弹栈序列, 则弹栈
            stack.pop(); k++;
        }
        if(i < popA.length) // 将压栈序列压入栈中, 在全部压入完成后, 进行最后一次判断是否符合弹栈
            stack.push(pushA[i]);
    }
    return stack.isEmpty(); // 如果栈为空, 则弹栈序列符合
}
```



## JZ22  从上往下打印二叉树

从上往下打印出二叉树的每个节点，同层节点从左至右打印。

```java
// 层次遍历: 利用队列
public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
    ArrayList<Integer> list = new ArrayList<>();
    if (root == null)
        return list;
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root); // 将根节点加入队列
    while (!queue.isEmpty()) {
        int len = queue.size(); //记录当前队列个数
        while (len-- > 0) {
            TreeNode node = queue.poll(); 
            list.add(node.val); // 遍历当前节点
            if(node.left != null) queue.add(node.left); // 将左节点加入队列
            if(node.right != null) queue.add(node.right); // 将右节点加入队列
        }
    }
    return list;
}
```

```java
// 层次遍历: 利用递归, 返回一个 List<List<Integer>>, 元素为每一层的所有节点
public List<List<Integer>> PrintFromTopToBottom(TreeNode root) {
    List<List<Integer>> list = new ArrayList<>();
    p(list, root, 0);
    return list;
}

public void p(List<List<Integer>> list, TreeNode root, int level) {
    if(root == null)
        return;
    for (int i = list.size();i <= level;i++) { //保证 list 长度
        list.add(new ArrayList<>());
    }
    list.get(level).add(root.val); //添加元素
    p(list, root.left, level + 1); //遍历左子树, 层次+1
    p(list, root.right, level + 1); //遍历右子树, 层次+1
}
```



## JZ23  二叉搜索树的后序遍历序列

输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。如果是则输出Yes,否则输出No。假设输入的数组的任意两个数字都互不相同。

```java
public boolean VerifySquenceOfBST(int [] sequence) {
    if (sequence == null || sequence.length == 0) return false;
    return v(sequence, 0, sequence.length - 1);
}

public boolean v(int[] sequence, int l, int r) {
    if(l >= r) return true;
    int x = l;
    while (x < r && sequence[x] < sequence[r]) x++; // 找到第一个大于根节点的索引
    if(x != r) { // 如果该索引在根节点左边, 则存在右子树
        for (int i = x;i < r;i++) { // 判断右子树的所有值是否满足都小于根节点
            if(sequence[i] < sequence[r])
                return false;
        }
    }
    // 递归左右子树
    return v(sequence, l, x - 1) && v(sequence, x, r - 1);
}
```



## JZ24  二叉树中和为某一值的路径

输入一颗二叉树的根节点和一个整数，按字典序打印出二叉树中结点值的和为输入整数的所有路径。路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。

```java
ArrayList<ArrayList<Integer>> lists = new ArrayList<>();
public ArrayList<ArrayList<Integer>> FindPath(TreeNode root,int target) {
    if(root == null)
        return lists;
    f(new ArrayList<>(), root, target);
    return lists;
}

public void f(ArrayList<Integer> list, TreeNode root, int target) {
    if(root == null) return;
    target -= root.val;
    list.add(root.val); // 将当前节点加入路径
    if (root.left == null && root.right == null && target == 0) { 
        lists.add(new ArrayList<>(list)); // 如果是叶子节点且等于目标值, 将路径加入路径列表
    } else {
        f(list, root.left, target); // 递归左子树
        f(list, root.right, target); // 递归右子树
    }
    list.remove(list.size() - 1); //将当前节点从路径删除
}
```



## JZ25  复杂链表的复制

输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，另一个特殊指针random指向一个随机节点），请对此链表进行深拷贝，并返回拷贝后的头结点。（注意，输出结果中请不要返回参数中的节点引用，否则判题程序会直接返回空）

```java
public RandomListNode Clone(RandomListNode pHead) {
	if(pHead == null) return null;
    RandomListNode p = pHead;
    // 原链表每个节点后都复制一个节点, 1-2-3-4-5 复制成 1-1'-2-2'-3-3'-4-4'-5-5'
    while (p != null) {
        RandomListNode node = new RandomListNode(p.label);
        node.next = p.next;
        p.next = node;
        p = node.next;
    }
    p = pHead;
    while (p != null) { // 复制每个复制节点的 random 指针
        if (p.random != null) {
            p.next.random = p.random.next;
        }
        p = p.next.next;
    }
    RandomListNode newHead = pHead.next;
    p = pHead;
    RandomListNode q = newHead;
    while (p != null) { // 将链表分成两个链表, 一个还原为原链表, 一个为深拷贝链表
        p.next = q.next;
        q.next = q.next == null ? null : q.next.next;
        q = q.next;
        p = p.next;
    }
    return newHead;
}
```



## JZ26 二叉搜索树与双向链表

输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。要求不能创建任何新的结点，只能调整树中结点指针的指向。

```java
List<TreeNode> list = new ArrayList<>();
public TreeNode Convert(TreeNode pRootOfTree) {
    if (pRootOfTree == null)
        return null;
    c(pRootOfTree);
    TreeNode root = null, p = null;
    for (TreeNode node: list) { // 遍历节点列表, 按顺序设置 left,right
        if(root == null) {
            root = node;
        } else {
            node.left = p;
            p.right = node;
        }
        p = node;
    }
    return root;
}
// 中序遍历, 将所有节点添加到列表
public void c(TreeNode root) {
    if(root == null)
        return;
    c(root.left);
    list.add(root);
    c(root.right);
}
```

```java
public TreeNode Convert(TreeNode pRootOfTree) {
    if (pRootOfTree == null)
        return null;
    if(pRootOfTree.left == null && pRootOfTree.right == null) // 如果是叶子节点, 直接返回
        return pRootOfTree;
    TreeNode left = Convert(pRootOfTree.left); // 获取左子树的头结点
    // 找到左子树末尾节点, 将根节点添加到末尾
    TreeNode p = left;
    while (p != null && p.right != null) {
        p = p.right;
    }
    if (p != null) {
        p.right = pRootOfTree;
        pRootOfTree.left = p;
    }
    TreeNode right = Convert(pRootOfTree.right); // 获取右子树的头结点
    if (right != null) { // 将右子树头结点添加到根节点
        pRootOfTree.right = right;
        right.left = pRootOfTree;
    }
    return left != null ? left : pRootOfTree; // 左子树为空, 则返回根节点
}
```



## JZ27  字符串的排列

输入一个字符串,按字典序打印出该字符串中字符的所有排列。例如输入字符串abc,则按字典序打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。

输入描述: 

> 输入一个字符串,长度不超过9(可能有字符重复),字符只包括大小写字母。

```java
public ArrayList<String> Permutation(String str) {
    ArrayList<String> list = new ArrayList<>();
    if(str == null || str.isEmpty())
        return list;
    char[] chs = str.toCharArray();
    Arrays.sort(chs); // 排序字符数组, 避免出现重复项
    p(list, chs, new boolean[chs.length], new StringBuilder());
    return list;
}
    
public void p(ArrayList<String> list, char[] chs, boolean[] vis, StringBuilder sb) {
    if (sb.length() == chs.length) { // 长度符合, 则停止递归
        list.add(sb.toString());
        return;
    }
    for (int i = 0;i < chs.length;i++) {
        if(vis[i]) continue; 
        if(i > 0 && chs[i] == chs[i-1] && vis[i-1]) continue; // 避免重复项
        sb.append(chs[i]); // 递归前添加字符, 设置为已访问
        vis[i] = true; 
        p(list, chs, vis, sb); // 递归
        vis[i] = false;
        sb.deleteCharAt(sb.length() - 1); //递归后删除字符, 设置为未访问
    }
}
```



## JZ28  数组中出现次数超过一半的数字

数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。如果不存在则输出0。

```java
// 使用 map 存储数组元素以及元素出现个数, 如果元素个数超过一半, 则返回该元素
public int MoreThanHalfNum_Solution(int [] array) {
    if(array == null || array.length == 0)
        return 0;
    Map<Integer, Integer> map = new HashMap<>();
    for (int x: array) {
        int count = map.containsKey(x) ? map.get(x) + 1 : 1;
        if(count * 2 > array.length)
            return x;
        map.put(x, count);
    }
    return 0;
}
```

```java
// 如果存在元素个数超过一半, 则为排序后的 array[len/2]
public int MoreThanHalfNum_Solution(int [] array) {
    if(array == null || array.length == 0)
        return 0;
    Arrays.sort(array);
    int num = array[array.length/2];
    int count = 0;
    for (int x: array) {
        if(x > num)
            break;
        if(x == num)
            count++;
    }
    return count > array.length / 2 ? num : 0;
}
```



## JZ29  最小的 K 个数

输入n个整数，找出其中最小的K个数。例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4。

```java
// 排序数组后, 获取前 K 个数
public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
    ArrayList<Integer> list = new ArrayList<>();
    if(input == null || input.length == 0 || k > input.length) return list;
    Arrays.sort(input);
    for (int i = 0; i < k; i++)
        list.add(input[i]);
    return list;
}
```



## JZ30  连续子数组的最大和

HZ偶尔会拿些专业问题来忽悠那些非计算机专业的同学。今天测试组开完会后,他又发话了:在古老的一维模式识别中,常常需要计算连续子向量的最大和,当向量全为正数的时候,问题很好解决。但是,如果向量中包含负数,是否应该包含某个负数,并期望旁边的正数会弥补它呢？例如:{6,-3,-2,7,-15,1,2,2},连续子向量的最大和为8(从第0个开始,到第3个为止)。给一个数组，返回它的最大连续子序列的和，你会不会被他忽悠住？(子向量的长度至少是1)

```java
// 时间复杂度: O(n!), 从 i = 0 -> len, j = i -> len 计算最大值 
int max = Integer.MIN_VALUE;
public int FindGreatestSumOfSubArray(int[] array) {
    for (int i = 0; i < array.length; i++)
        f(array, i);
    return max;
}

public void f(int[] array, int start) {
    int sum = 0;
    for (int i = start; i < array.length; i++) {
        sum += array[i];
        max = Math.max(max, sum);
    }
}
```

```java
// 动态规划, 如果遍历元素前面元素的和为负数, 则放弃前面的和, 从当前元素开始重新计和
public int FindGreatestSumOfSubArray(int[] array) {
    int max = array[0], sum = array[0];
    for (int i = 1; i < array.length; i++) {
        sum = Math.max(array[i] , sum + array[i]);
        max = Math.max(max, sum);
    }
    return max;
}
```



## JZ31  整数中 1 出现的次数

求出1 ~ 13的整数中1出现的次数,并算出100 ~ 1300的整数中1出现的次数？为此他特别数了一下1~13中包含1的数字有1、10、11、12、13因此共出现6次,但是对于后面问题他就没辙了。ACMer希望你们帮帮他,并把问题更加普遍化,可以很快的求出任意非负整数区间中1出现的次数（从1 到 n 中1出现的次数）。

```java
// 暴力解法
public int NumberOf1Between1AndN_Solution(int n) {
    int count = 0;
    for (int i = 1;i <=n;i++) {
        for(int j = i;j > 0;j/=10) {
            if(j % 10 == 1) count++;
        }
    }
    return count;
}
```

```java
public int NumberOf1Between1AndN_Solution(int n) {
    int count = 0;
    for (int i = 1;i <=n;i*=10) {
        int a = n / i, b = n % i;
        // a % 10 为上一位, 上一位为1, 后一位为几就加几; 否则不加
        count += (a + 8) / 10 * i + (a % 10 == 1 ? b + 1 : 0); 
    }
    return count;
}
```



## JZ32  把数组排成最小数

输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。例如输入数组{3，32，321}，则打印出这三个数字能排成的最小数字为321323。

```java
// 暴力法, 全排列所有结果, 获取最小结果
StringBuilder min = new StringBuilder();
public String PrintMinNumber(int [] numbers) {
    for (int number : numbers)
        min.append(number);
    p(new StringBuilder(), numbers, new boolean[numbers.length]);
    return min.toString();
}
public void p(StringBuilder sb, int[] numbers, boolean[] vis) {
    if(sb.length() == min.length()) {
        if(sb.toString().compareTo(min.toString()) < 0)
            min = new StringBuilder(sb);
        return;
    }
    for (int i = 0; i < numbers.length; i++) {
        if(vis[i]) continue;
        vis[i] = true;
        int len = sb.length();
        sb.append(numbers[i]);
        p(sb, numbers, vis);
        sb.delete(len, sb.length());
        vis[i] = false;
    }
}
```

```java
// 冒泡排序, 两个相邻的数有两种组合, 值大的组合往下沉
public String PrintMinNumber(int [] numbers) {
    if(numbers == null || numbers.length == 0)
        return "";
    for (int i = 0;i < numbers.length - 1;i++) {
        for (int j = 0;j < numbers.length - 1 - i;j++) {
            long a = Long.parseLong(numbers[j] + "" + numbers[j + 1]);
            long b = Long.parseLong(numbers[j + 1] + "" + numbers[j]);
            if(a > b) {
                numbers[j] ^= numbers[j + 1];
                numbers[j + 1] ^= numbers[j];
                numbers[j] ^= numbers[j + 1];
            }
        }
    }
    StringBuilder sb = new StringBuilder();
    for (int number: numbers) {
        sb.append(number);
    }
    return sb.toString();
}
```



## JZ33  丑数

把只包含质因子2、3和5的数称作丑数（Ugly Number）。例如6、8都是丑数，但14不是，因为它包含质因子7。 习惯上我们把1当做是第一个丑数。求按从小到大的顺序的第N个丑数。

```java
// 使用 TreeSet
public int GetUglyNumber_Solution(int index) {
    TreeSet<Integer> set = new TreeSet<>();
    int x = 0;
    set.add(1);
    while (index-- > 0) {
        x = set.ceiling(x + 1);
        set.add(x * 2);
        set.add(x * 3);
        set.add(x * 5);
    }
    return x;
}
```

```java
public int GetUglyNumber_Solution(int index) {
    if(index <= 0) return 0;
    int[] nums = new int[index];
    nums[0] = 1;
    int o = 0, p = 0, q = 0;
    for (int i = 1; i < index; i++) {
        nums[i] = Math.min(nums[o] * 2, Math.min(nums[p] * 3, nums[q] * 5));
        // 已经添加的值不需要重复添加
        if(nums[i] == nums[o] * 2) o++; 
        if(nums[i] == nums[p] * 3) p++;
        if(nums[i] == nums[q] * 5) q++;
    }
    return nums[index - 1];
}
```



## JZ34  第一个只出现一次的字符

在一个字符串(0<=字符串长度<=10000，全部由字母组成)中找到第一个只出现一次的字符,并返回它的位置, 如果没有则返回 -1（需要区分大小写）.（从0开始计数）

```java
// 暴力法
public int FirstNotRepeatingChar(String str) {
    char[] chs = str.toCharArray();
    for (int i = 0;i < chs.length;i++) {
        int j = 0;
        for(j = 0;j < chs.length;j++) {
            if(chs[j] == chs[i] && i != j) {
                break;
            }
        }
        if(j == chs.length) {
            return i;
        }
    }
    return -1;
}
```

```java
// 暴力法, 避免重复遍历相同元素
public int FirstNotRepeatingChar(String str) {
    char[] chs = str.toCharArray();
    boolean[] vis = new boolean[chs.length];
    for (int i = 0;i < chs.length;i++) {
        if(vis[i]) continue;
        boolean flag = true;
        for(int j = i + 1;j < chs.length;j++) {
            if(vis[j]) continue;
            if(chs[j] == chs[i]) {
                flag = false;
                vis[j] = true;
            }
        }
        if(flag) return i;
    }
    return -1;
}
```

```java
// 桶
public int FirstNotRepeatingChar(String str) {
    char[] chs = str.toCharArray();
    int[] num = new int[128];
    for (char c : chs) {
        num[c]++;
    }
    for (int i = 0; i < chs.length; i++) {
        if(num[chs[i]] == 1)
            return i;
    }
    return -1;
}
```



## * JZ35  数组中的逆序对

在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组,求出这个数组中的逆序对的总数P。并将P对1000000007取模的结果输出。 即输出P%1000000007

输入描述:

> 题目保证输入的数组中没有的相同的数字
>
> 数据范围：
>
> ​	对于%50的数据,size<=10^4^
>
> ​	对于%75的数据,size<=10^5^
>
> ​	对于%100的数据,size<=2*10^5^

```java
public int InversePairs(int [] array) {
    if(array == null) return 0;
    int[] tmp = new int[array.length];
    return mergeSort(array, tmp, 0, array.length-1);
}
//归并排序，递归
private int mergeSort(int[] array, int[] tmp, int low, int high) {
    if(low >= high) return 0;
    int res = 0, mid = low + (high - low) / 2;
    res += mergeSort(array, tmp, low, mid);
    res %= 1000000007;
    res += mergeSort(array, tmp, mid + 1, high);
    res %= 1000000007;
    res += merge(array, tmp, low, mid, high);
    res %= 1000000007;
    return res;
}

//归并排序，合并
private int merge(int[] array, int[] tmp, int low, int mid, int high) {
    int i = low, i1 = low, i2 = mid + 1;
    int res = 0;
    while(i1 <= mid && i2 <= high) {
        if(array[i1] > array[i2]) {
            res += mid - i1 + 1;
            res %= 1000000007;
            tmp[i++] = array[i2++];
        } else
            tmp[i++] = array[i1++];
    }
    while(i1 <= mid)
        tmp[i++] = array[i1++];
    while(i2 <= high)
        tmp[i++] = array[i2++];
    for (i = low; i <= high; i++)
        array[i] = tmp[i];
    return res;
}
```



## JZ36  两个链表的第一个公共节点

输入两个链表，找出它们的第一个公共结点。（注意因为传入数据是链表，所以错误测试数据的提示是用其他方式显示的，保证传入数据是正确的）

```java
// 将两个链表的所有节点压入两个栈中, 从栈顶开始找到第一个不同的节点, 该节点的 next 为要找的公共头节点
public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
    if (pHead1 == null || pHead2 == null) return null;
    if (pHead1 == pHead2) return pHead1;
    Stack<ListNode> stack1 = new Stack<>();
    Stack<ListNode> stack2 = new Stack<>();
    ListNode p = pHead1, q = pHead2;
    while (p != null) {
        stack1.push(p); p = p.next;
    }
    while (q != null) {
        stack2.push(q); q = q.next;
    }
    while (!stack1.isEmpty() && !stack2.isEmpty() && stack1.peek()==stack2.peek()) {
        stack1.pop();
        stack2.pop();
    }
    if(stack1.isEmpty()) return pHead1;
    if(stack2.isEmpty()) return pHead2;
    if(stack1.peek().next == stack2.peek().next) {
        return stack1.peek().next;
    }
    return null;
}
```



## JZ37  数字在升序数组中出现的次数

统计一个数字在升序数组中出现的次数。

```java
// 遍历整个数组
public int GetNumberOfK(int [] array , int k) {
    int count = 0;
    for (int i = 0;i < array.length;i++) {
        if(array[i] < k)
            continue;
        if(array[i] == k) {
            count++;
            while (++i < array.length && array[i] == k) count++;
        }
        break;
    }
    return count;
}
```

```java
// 二分查找
public int GetNumberOfK(int [] array , int k) {
    int count = 0;
    int l = 0, r = array.length - 1;
    while (l <= r) {
        int mid = l + (r - l) / 2;
        if (array[mid] == k) { // 找到一个目标元素
            count++;
            for (int i = mid - 1;i >= l;i--) { // 往左遍历查找目标元素
                if (array[i] == k)
                    count++;
                else break;
            }
            for (int i = mid + 1;i <= r;i++) { //往右遍历查找目标元素
                if (array[i] == k)
                    count++;
                else break;
            }
            break;
        }
        //缩小范围
        if (array[mid] < k)
            l = mid + 1;
        else
            r = mid - 1;
    }
    return count;
}
```



## JZ38  二叉树的深度

输入一棵二叉树，求该树的深度。从根结点到叶结点依次经过的结点（含根、叶结点）形成树的一条路径，最长路径的长度为树的深度。

```java
// 递归
public int TreeDepth(TreeNode root) {
    if(root == null) return 0;
    if(root.left == null && root.right == null) return 1;
    // 递归左右子树, 返回最大深度
    return 1 + Math.max(TreeDepth(root.left), TreeDepth(root.right)); 
}
```



## JZ39  平衡二叉树

输入一棵二叉树，判断该二叉树是否是平衡二叉树。

在这里，我们只需要考虑其平衡性，不需要考虑其是不是排序二叉树

```java
public boolean IsBalanced_Solution(TreeNode root) {
    return s(root) != -1;
}

// 获取最深深度, 如果左右子树深度差值 > 1, 返回 -1
public int s(TreeNode root) {
    if(root == null) return 0;
    if(root.left == null && root.right == null) return 1;
    int left = s(root.left);
    if(left == -1) return -1;
    int right = s(root.right);
    if(right == -1) return -1;
    if(Math.abs(left - right) > 1) // 如果左右子树深度差值 > 1, 返回 -1
        return -1;
    return 1 + Math.max(left, right);
}
```



## * JZ40  数组中只出现一次的数字

一个整型数组里除了两个数字之外，其他的数字都出现了两次。请写程序找出这两个只出现一次的数字。

```java
// 用 map 存储所有元素, 取出 value=1 的 key
public void FindNumsAppearOnce(int [] array,int num1[] , int num2[]) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int i : array)
        map.put(i, map.containsKey(i) ? map.get(i) + 1 : 1);
    int count = 0;
    for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
        if(entry.getValue() == 1) {
            if(count == 0) {
                num1[0] = entry.getKey();
            } else if(count == 1) {
                num2[0] = entry.getKey();
            } else break;
            count++;
        }
    }
}
```

```java
// n = a ^ b ^ c ^ c = a ^ b, n = n & -n, n & a = ((a^b) & (-a^b)) & a
public void FindNumsAppearOnce(int [] array,int num1[] , int num2[]) {
    int n = 0;
    for (int x: array)
        n ^= x;
    n &= -n;
    for (int x: array) {
        if((n & x) == 0)
            num1[0] ^= x;
        else
            num2[0] ^= x;
    }
}
```



# JZ 41~60

## JZ41  和为 S 的连续正数序列

小明很喜欢数学,有一天他在做数学作业时,要求计算出9~16的和,他马上就写出了正确答案是100。但是他并不满足于此,他在想究竟有多少种连续的正数序列的和为100(至少包括两个数)。没多久,他就得到另一组连续正数和为100的序列:18,19,20,21,22。现在把问题交给你,你能不能也很快的找出所有和为S的连续正数序列? Good Luck!

输出描述:

> 输出所有和为S的连续正数序列。序列内按照从小至大的顺序，序列间按照开始数字从小到大的顺序

```java
 public ArrayList<ArrayList<Integer> > FindContinuousSequence(int sum) {
     ArrayList<ArrayList<Integer>> list = new ArrayList<>();
     // 区间 [l, r]
     int l = 1, r = 2;
     // 区间和
     int s = l + r; 
     while(r <= sum / 2 + 1) {
         if (s == sum) {
             ArrayList<Integer> ll = new ArrayList<>();
             for (int i = l;i <= r;i++)
                 ll.add(i);
             list.add(ll);
             s -= l++; // 缩小左边界 [l+1, r], 继续寻找
         } else if (s > sum) // 区间和过大, 缩小左边界 [l+1, r]
             s -= l++;
         else // 区间和过小, 扩大右边界 [l, r+1]
             s += ++r;
     }
     return list;
 }
```



## JZ42  和为 S 的两个数字

输入一个递增排序的数组和一个数字S，在数组中查找两个数，使得他们的和正好是S，如果有多对数字的和等于S，输出两个数的乘积最小的。

输出描述: 

> 对应每个测试案例，输出两个数，小的先输出。

```java
public ArrayList<Integer> FindNumbersWithSum(int [] array,int sum) {
    ArrayList<Integer> list = new ArrayList<>();
    int l = 0, r = array.length - 1;
    while (l < r) {
        int s = array[l] + array[r];
        if (s == sum) {
            list.add(array[l]); list.add(array[r]);
            break;
        } else if (s < sum) {
            l++;
        } else {
            r--;
        }
    }
    return list;
}
```



## JZ43  左旋转字符串

汇编语言中有一种移位指令叫做循环左移（ROL），现在有个简单的任务，就是用字符串模拟这个指令的运算结果。对于一个给定的字符序列S，请你把其循环左移K位后的序列输出。例如，字符序列S=”abcXYZdef”,要求输出循环左移3位后的结果，即“XYZdefabc”。是不是很简单？OK，搞定它！

```java
public String LeftRotateString(String str,int n) {
    if(str == null || str.length() <= 1 || n == 0)
        return str;
    n = n % str.length();
    if(n > 0) { // 左移
        return str.substring(n) + str.substring(0, n);
    } else { // 右移
        return str.substring(str.length() + n) + str.substring(0, str.length() + n);
    }
}
```



## JZ44  反转单词顺序列

牛客最近来了一个新员工Fish，每天早晨总是会拿着一本英文杂志，写些句子在本子上。同事Cat对Fish写的内容颇感兴趣，有一天他向Fish借来翻看，但却读不懂它的意思。例如，“student. a am I”。后来才意识到，这家伙原来把句子单词的顺序翻转了，正确的句子应该是“I am a student.”。Cat对一一的翻转这些单词顺序可不在行，你能帮助他么？

```java
// 字符串分割
public String ReverseSentence(String str) {
    if(str == null || str.length() <= 1)
        return str;
    StringBuilder sb = new StringBuilder();
    String[] ss = str.split(" ");
    if(ss == null || ss.length == 0)
        return str;
    for(int i = ss.length - 1;i >= 0;i--)
        sb.append(ss[i]).append(" ");
    return sb.toString().substring(0, sb.length() - 1);
}
```

```java
public String ReverseSentence(String str) {
    if(str == null || str.length() <= 1)
        return str;
    StringBuilder sb = new StringBuilder();
    char[] chs = str.toCharArray();
    for (int i = chs.length - 1;i >= 0;i--) {
        if (chs[i] == ' ') {
            sb.append(" ");
        } else {
            int l = i;
            while (i - 1 >= 0 && chs[i - 1] != ' ') i--;
            sb.append(str.subSequence(i, l + 1));
        }
    }
    return sb.toString();
}
```

```java
// 每个单词逆序, 总字符串逆序
public String ReverseSentence(String str) {
    if(str == null || str.length() <= 1)
        return str;
    char[] chs = str.toCharArray();
    for (int i = 0, l = 0; i <= chs.length; i++) {
        if (i == chs.length || chs[i] == ' ') {
            reverse(chs, l, i - 1);
            l = i + 1;
        }
    }
    reverse(chs, 0, chs.length - 1);
    return new String(chs);
}
    
public void reverse(char[] chs, int l, int r) {
    while (l < r) {
        chs[l] ^= chs[r];
        chs[r] ^= chs[l];
        chs[l] ^= chs[r];
        l++; r--;
    }
}
```



## JZ45  扑克牌顺子

LL今天心情特别好,因为他去买了一副扑克牌,发现里面居然有2个大王,2个小王(一副牌原本是54张^_^)...他随机从中抽出了5张牌,想测测自己的手气,看看能不能抽到顺子,如果抽到的话,他决定去买体育彩票,嘿嘿！！“红心A,黑桃3,小王,大王,方片5”,“Oh My God!”不是顺子.....LL不高兴了,他想了想,决定大\小 王可以看成任何数字,并且A看作1,J为11,Q为12,K为13。上面的5张牌就可以变成“1,2,3,4,5”(大小王分别看作2和4),“So Lucky!”。LL决定去买体育彩票啦。 现在,要求你使用这幅牌模拟上面的过程,然后告诉我们LL的运气如何， 如果牌能组成顺子就输出true，否则就输出false。为了方便起见,你可以认为大小王是0。

```java
public boolean isContinuous(int [] numbers) {
    if(numbers.length < 5) return false; // 必须 5 张牌
    Arrays.sort(numbers);
    int joker = 0;
    int first = 0;
    while (first < numbers.length && numbers[first] == 0) { // 判断 joker 的张数
        joker++;
        first++;
    }
    if (first == numbers.length) return false; // 不存在 5 张 joker
    int start = numbers[first]; // 顺子开始数值
    for (first = first + 1;first < numbers.length;first++) {
        if (numbers[first] == start) // 顺子牌面不能相等
            return false;
        joker -= (numbers[first] - start - 1); // 不够用 joker 凑
        if (joker < 0) // joker 不够则不能凑成顺子
            return false;
        start = numbers[first];
    }
    return true;
}
```



## JZ46  孩子们的游戏(圆圈中最后剩下的数)

每年六一儿童节,牛客都会准备一些小礼物去看望孤儿院的小朋友,今年亦是如此。HF作为牛客的资深元老,自然也准备了一些小游戏。其中,有个游戏是这样的:首先,让小朋友们围成一个大圈。然后,他随机指定一个数m,让编号为0的小朋友开始报数。每次喊到m-1的那个小朋友要出列唱首歌,然后可以在礼品箱中任意的挑选礼物,并且不再回到圈中,从他的下一个小朋友开始,继续0...m-1报数....这样下去....直到剩下最后一个小朋友,可以不用表演,并且拿到牛客名贵的“名侦探柯南”典藏版(名额有限哦!!^_^)。请你试着想下,哪个小朋友会得到这份礼品呢？(注：小朋友的编号是从0到n-1)

如果没有小朋友，请返回-1

```java
// 暴力法, 模拟报数
public int LastRemaining_Solution(int n, int m) {
    if (n <= 0 || m <= 0)
        return -1;
    boolean[] childes = new boolean[n];
    int i = 0, j = 0;
    int count = 0;
    while (true) {
        if (!childes[i]) {
            if (++j == m) {
                childes[i] = true;
                if (++count == n - 1)
                    break;
                j = 0;
            }
        }
        i = (i + 1) % n;
    }
    for (int x = 0;x < n;x++) {
        if (!childes[x])
            return x;
    }
    return -1;
}
```

```java
public int LastRemaining_Solution(int n, int m) {
    if (n <= 0 || m <= 0)
        return -1;
    int x = 0;
    for (int i = 2;i <= n;i++)
        x = (x + m) % i;
    return x;
}
```



## JZ47  求1+2+3+...+n

求1+2+3+...+n，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。

```java
// 递归代替循环, && 代替 if
public int Sum_Solution(int n) {
    boolean b = n > 0 && (n += Sum_Solution(n - 1)) > 0;
    return n;
}
```



## JZ48  不用加减乘除做加法

写一个函数，求两个整数之和，要求在函数体内不得使用+、-、*、/四则运算符号。

```java
public int Add(int num1,int num2) {
    if (num1 == 0) return num2;
    if (num2 == 0) return num1;
    int sum = 0;
    while (num2 != 0) {
        sum = num1 ^ num2;
        num2 = (num1 & num2) << 1;
        num1 = sum;
    }
    return sum;
}
```



## JZ49  把字符串转成整数

将一个字符串转换成一个整数，要求不能使用字符串转换整数的库函数。 数值为0或者字符串不是一个合法的数值则返回0

输入描述

> 输入一个字符串,包括数字字母符号,可以为空

输出描述

> 如果是合法的数值表达则返回该数字，否则返回0

```java
public int StrToInt(String str) {
    if(str == null || str.isEmpty())
        return 0;
    int n = 0;
    int type = 0;
    int start = 0;
    char t = str.charAt(0);
    if(t == '+') {
        type = 1;
        start = 1;
    } else if (t == '-') {
        type = -1;
        start = 1;
    }
    for (;start < str.length();start++) {
        char c = str.charAt(start);
        if (c > '9' || c < '0')
            return 0;
        n = n * 10 + c - '0';
    }
    return type == -1 ? -n : n;
}
```



## JZ50  数组中重复的数字

在一个长度为n的数组里的所有数字都在0到n-1的范围内。 数组中某些数字是重复的，但不知道有几个数字是重复的。也不知道每个数字重复几次。请找出数组中任意一个重复的数字。 例如，如果输入长度为7的数组{2,3,1,0,2,5,3}，那么对应的输出是第一个重复的数字2。

```java
public boolean duplicate(int numbers[],int length,int [] duplication) {
    duplication[0] = -1;
    if (numbers == null || numbers.length <= 1)
        return false;
    Arrays.sort(numbers);
    for (int i = 0;i < numbers.length - 1;i++) {
        if(numbers[i] == numbers[i+1]) {
            duplication[0] = numbers[i];
            return true;
        }
    }
    return false;
}
```



## JZ51  构建乘积数组

给定一个数组A[0,1,...,n-1],请构建一个数组B[0,1,...,n-1],其中B中的元素B[i]=A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]。不能使用除法。（注意：规定B[0] = A[1] * A[2] * ... * A[n-1]，B[n-1] = A[0] * A[1] * ... * A[n-2];）

对于A长度为1的情况，B无意义，故而无法构建，因此该情况不会存在。

```java
// 暴力法
public int[] multiply(int[] A) {
    int[] b = new int[A.length];
    for (int i = 0;i < A.length;i++) {
        b[i] = 1;
        for (int j = 0;j < A.length;j++) {
            if(j == i) continue;
            b[i] *= A[j];
        }
    }
    return b;
}
```

```java
// L[i] = A[0]*..*A[i-1], R[i] = A[i+1]*..*A[n-1], L[i] * R[i] = A[0]*..*A[n-1]/A[i]
public int[] multiply(int[] A) {
    int[] L = new int[A.length];
    int[] R = new int[A.length];
    L[0] = 1; R[A.length - 1] = 1;
    for (int i = 1;i < A.length;i++)
        L[i] = A[i - 1] * L[i - 1];
    for (int i = A.length - 2;i>=0;i--)
        R[i] = A[i + 1] * R[i + 1];
    int[] b = new int[A.length];
    for (int i = 0;i < A.length;i++)
        b[i] = L[i] * R[i];
    return b;
}
```



## JZ52  正则表达式匹配

请实现一个函数用来匹配包括'.'和'\*'的正则表达式。模式中的字符'.'表示任意一个字符，而'\*'表示它前面的字符可以出现任意次（包含0次）。 在本题中，匹配是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和"ab\*ac\*a"匹配，但是与"aa.a"和"ab\*a"均不匹配

```java
public boolean match(char[] str, char[] pattern) {
    if(str == null || pattern == null)
        return false;
    return m(str, pattern, 0, 0);
}
// 双指针遍历
public boolean m(char[] str, char[] pattern, int i, int j) {
    if(j == pattern.length) // 如果都到达末尾, 则返回 true; 如果 str 还有没有遍历则返回 false
        return i == str.length;
    if (pattern[j] == '*') // 如果 '*' 单独出现, 则返回 false
        return false;
    // 如果后一位是 '*'
    if (j + 1 < pattern.length && pattern[j + 1] == '*') {
        // 如果当前字符匹配, 递归 匹配 0 次(j+2), 匹配 1 次(i+1,j+2), 匹配多次(i+1)
        if (i < str.length && (pattern[j] == '.' || pattern[j] == str[i])) { 
            return m(str, pattern, i, j + 2) || m(str, pattern, i + 1, j + 2)
                										m(str, pattern, i + 1, j);
        } else { // 如果当前字符不匹配, 则 '*' 匹配 0 次
            return m(str, pattern, i, j + 2);
        }
    }
    // 如果当前位匹配, 则递归下一位; 如果不匹配, 则返回 false
    if (i < str.length && pattern[j] == str[i] || pattern[j] == '.')
        return m(str, pattern, i + 1,j + 1);
    return false;
}
```



## JZ53  表示数值的字符串

请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。例如，字符串"+100","5e2","-123","3.1416"和"-1E-16"都表示数值。 但是"12e","1a3.14","1.2.3","+-5"和"12e+4.3"都不是。

```java
public boolean isNumeric(char[] str) {
    if(str == null || str.length == 0) // 字符串不能为空
        return false;
    int start = 0;
    if(str[0] == '+' || str[0] == '-') // 从 +,- 之后开始遍历
        start = 1;
    boolean e = true, d = true; // 记录后面遍历是否可以出现 'e','E' 和 '.'
    while (start < str.length) { // 遍历字符串
        if(str[start] == 'e' || str[start] == 'E') { // 如果是 'e','E'
            if(!e || start + 1 >= str.length) return false; //如果已有或长度不够,返回false
            if(str[start + 1] == '+' || str[start + 1] == '-') // 从 +,- 之后开始遍历
                start++;
            e = false; d = false; // 后面不能出现 'e','E' 和 '.'
        } else if(str[start] == '.') { // 如果是 '.'
            if(!d || start + 1 >= str.length) return false; //如果已有或长度不够,返回false
            d = false; //后面不能出现 '.'
        } else if(str[start] > '9' || str[start] < '0') { // 如果是其他字符, 返回 false
            return false;
        }
        start++;
    }
    return true;
}
```



## JZ54  字符流中第一个不重复的字符

请实现一个函数用来找出字符流中第一个只出现一次的字符。例如，当从字符流中只读出前两个字符"go"时，第一个只出现一次的字符是"g"。当从该字符流中读出前六个字符“google"时，第一个只出现一次的字符是"l"。

输出描述: 

> 如果当前字符流没有存在出现一次的字符，返回#字符。

```java
LinkedList<Character> list = new LinkedList<>();
//Insert one char from stringstream
public void Insert(char ch) {
    if(list.isEmpty() || !list.contains(ch)) {
        list.add(ch);
    } else {
        list.remove((Character)ch);
    }
}
//return the first appearence once char in current stringstream
public char FirstAppearingOnce() {
    return list.isEmpty() ? '#' : list.getFirst();
}
```



## JZ55  链表中环的入口结点

给一个链表，若其中包含环，请找出该链表的环的入口结点，否则，输出null。

```java
// 将所有节点加入 set, 如果一下节点在 set 中存在, 则该节点为入口节点
public static ListNode EntryNodeOfLoop(ListNode pHead) {
    if (pHead == null)
        return null;
    HashSet<ListNode> set = new HashSet<>();
    ListNode fast = pHead;
    while (fast != null) {
        if(set.contains(fast)) {
            return fast;
        }
        set.add(fast);
        fast = fast.next;
    }
    return null;
}
```

```java
// 快慢指针, 快指针遇到慢指针时, 有环
// 快指针走了 2(x+y), 慢指针走了 (x+y), 在 (x+y) 处相遇, 在 (x+y+x) 处回到 x 处
// |<----x---->|<----y---->|<----x---->|
//             |<----y---->|
// 慢指针再走 x 会回到 x 处, 即入口节点
public ListNode EntryNodeOfLoop(ListNode pHead) {
    if (pHead == null)
        return null;
    ListNode fast = pHead;
    ListNode slow = pHead;
    while (fast != null && fast.next != null) {
        fast = fast.next.next;
        slow = slow.next;
        if (fast == slow) // 找到 x+y 处
            break;
    }
    if (fast == null || fast.next == null) // 无环
        return null;
    fast = pHead;
    while (fast != slow) { // 慢指针走 x, 快指针从头开始走 x, 在 x 处相遇
        fast = fast.next;
        slow = slow.next;
    }
    return fast;
}
```



## JZ56  删除链表中重复的节点

在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，返回链表头指针。 例如，链表1->2->3->3->4->4->5 处理后为 1->2->5

```java
public ListNode deleteDuplication(ListNode pHead) {
    if (pHead == null || pHead.next == null)
        return pHead;
    ListNode root = new ListNode(0);
    root.next = pHead;
    ListNode p = root;
    while (p != null && p.next != null && p.next.next != null) {
        if(p.next.val == p.next.next.val) {
            ListNode q = p.next.next.next;
            while (q != null && q.val == p.next.val) q = q.next;
            p.next = q;
        } else {
            p = p.next;
        }
    }
    return root.next;
}
```



## JZ57  二叉树的下一个结点

给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针。

```java
public TreeLinkNode GetNext(TreeLinkNode pNode) {
    if(pNode == null)
        return null;
    return go(pNode, false);
}

public TreeLinkNode go(TreeLinkNode pNode, boolean right) {
    if(pNode == null)
        return null;
    if (pNode.next == null) { // 根节点没有父节点
        if(right) return null; // 如果是右子树递归来的, 则返回 null
        TreeLinkNode p = pNode.right;
        while (p != null && p.left != null) p = p.left;
        return p;
    } else {
        if (!right && pNode.right != null) { // 如果右子树不为空, 返回右子树的最左节点
            TreeLinkNode p = pNode.right;
            while (p != null && p.left != null) p = p.left;
            return p;
        } else {
            TreeLinkNode parent = pNode.next; // 父节点
            if (parent.left == pNode) { // 当前为父节点的左子树, 则返回父节点
                return parent;
            } else { // 当前为父节点的右子树, 则递归父节点
                return go(parent, true);
            }
        }
    }
}
```



## JZ58  对称的二叉树

请实现一个函数，用来判断一棵二叉树是不是对称的。注意，如果一个二叉树同此二叉树的镜像是同样的，定义其为对称的。

```java
boolean isSymmetrical(TreeNode pRoot) {
    if(pRoot == null)
        return true;
    return f(pRoot.left, pRoot.right);
}

boolean f(TreeNode root1, TreeNode root2) {
    if(root1 == null && root2 == null)
        return true;
    if(root1 == null || root2 == null)
        return false;
    if(root1.val != root2.val)
        return false;
    // 递归 (左子树的左子树 和 右子树的右子树) && (左子树的右子树 和 右子树的左子树)
    return f(root1.left, root2.right) && f(root1.right, root2.left);
}
```



## JZ59  按之字形顺序打印二叉树

请实现一个函数按照之字形打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右至左的顺序打印，第三行按照从左到右的顺序打印，其他行以此类推。

```java
// 层次遍历, 偶数层列表翻转 
public ArrayList<ArrayList<Integer> > Print(TreeNode pRoot) {
     ArrayList<ArrayList<Integer>> list = new ArrayList<>();
     if(pRoot == null) return list;
     Queue<TreeNode> queue = new LinkedList<>();
     queue.add(pRoot);
     int level = 1;
     while (!queue.isEmpty()) {
         int len = queue.size();
         ArrayList<Integer> ll = new ArrayList<>();
         while (len-- > 0) {
             TreeNode p = queue.poll();
             ll.add(p.val);
             if(p.left != null) queue.add(p.left);
             if(p.right != null) queue.add(p.right);
         }
         if(level++ % 2 == 0)
             Collections.reverse(ll);
         list.add(ll);
     }
     return list;
 }
```



## JZ60  把二叉树打印成多行

从上到下按层打印二叉树，同一层结点从左至右输出。每一层输出一行。

```java
// 层次遍历
ArrayList<ArrayList<Integer> > Print(TreeNode pRoot) {
    ArrayList<ArrayList<Integer>> list = new ArrayList<>();
    if(pRoot == null) return list;
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(pRoot);
    while (!queue.isEmpty()) {
        int len = queue.size();
        ArrayList<Integer> ll = new ArrayList<>();
        while (len-- > 0) {
            TreeNode p = queue.poll();
            ll.add(p.val);
            if(p.left != null) queue.add(p.left);
            if(p.right != null) queue.add(p.right);
        }
        list.add(ll);
    }
    return list;
}
```



# JZ 60+

## JZ61  序列化二叉树

请实现两个函数，分别用来序列化和反序列化二叉树

二叉树的序列化是指：把一棵二叉树按照某种遍历方式的结果以某种格式保存为字符串，从而使得内存中建立起来的二叉树可以持久保存。序列化可以基于先序、中序、后序、层序的二叉树遍历方式来进行修改，序列化的结果是一个字符串，序列化时通过 某种符号表示空节点（#），以 ！ 表示一个结点值的结束（value!）。

二叉树的反序列化是指：根据某种遍历顺序得到的序列化字符串结果str，重构二叉树。

例如，我们可以把一个只有根节点为1的二叉树序列化为"1,"，然后通过自己的函数来解析回这个二叉树

```java
// 序列化, 层次遍历, null 用 '#' 输出
String Serialize(TreeNode root) {
    if(root == null)
        return "#";
    StringBuilder sb = new StringBuilder();
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);
    TreeNode nullNode = new TreeNode(0);
    while (!queue.isEmpty()) {
        int len = queue.size();
        boolean allNull = true; // 子树是否全部为 null
        while (len-- > 0) {
            TreeNode p = queue.poll();
            if (p == nullNode) {
                sb.append("#,");
            } else {
                sb.append(p.val).append(",");
                allNull = allNull && p.left == null && p.right == null;
                queue.add(p.left != null ? p.left : nullNode);
                queue.add(p.right != null ? p.right : nullNode);
            }
        }
        if(allNull) break; // 如果子树全部为 null, 则无需继续遍历
    }
    return sb.toString();
}
// 反序列化, 层次遍历
TreeNode Deserialize(String str) {
    if(str == null || str.length() == 0)
        return null;
    String[] strs = str.split(",");
    if(strs == null || strs.length == 0 || strs[0].equals("#"))
        return null;
    TreeNode root = new TreeNode(Integer.valueOf(strs[0]));
    Queue<TreeNode> queue =  new LinkedList<>();
    queue.add(root);
    int i = 1; int len = strs.length;
    while (i < len && !queue.isEmpty()) {
        int qlen = queue.size();
        while (i < len && qlen-->0) {
            TreeNode node = queue.poll();
            if(!strs[i].equals("#")) {
                node.left = new TreeNode(Integer.valueOf(strs[i]));
                queue.add(node.left);
            }
            if(++i >= len) return root;
            if(!strs[i].equals("#")) {
                node.right = new TreeNode(Integer.valueOf(strs[i]));
                queue.add(node.right);
            }
            ++i;
        }
    }
    return root;
}
```



## JZ62  二叉搜索树的第 K 个结点

给定一棵二叉搜索树，请找出其中的第k小的结点。例如， （5，3，7，2，4，6，8）  中，按结点数值大小顺序第三小结点的值为4。

```java
TreeNode t = null;
int n = 0;
TreeNode KthNode(TreeNode pRoot, int k) {
    if (k <= 0)  return null;
    n = k;
    p(pRoot);
    return t;
}

void p(TreeNode root) {
    if(root == null)
        return;
    p(root.left);
    if(--n == 0) {
        t = root;
        return;
    }
    p(root.right);
}
```



## JZ63  数据流中的中位数

如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。我们使用Insert()方法读取数据流，使用GetMedian()方法获取当前读取数据的中位数。

```java
ArrayList<Integer> list = new ArrayList<>();
// 插入可以通过二分查找, 可以保存中位数索引, 提高效率
public void Insert(Integer num) {
    int i = 0;
    int len = list.size();
    for (;i < len;i++) {
        if (list.get(i) >= num)
            break;
    }
    list.add(i, num);
}

public Double GetMedian() {
    int len = list.size();
    if (len == 0)
        return null;
    int m = len / 2;
    if (len % 2 == 1) {
        return (double)list.get(m);
    } else {
        return (double)(list.get(m) + list.get(m - 1)) / 2;
    }
}
```



## JZ64  滑动窗口的最大值

给定一个数组和滑动窗口的大小，找出所有滑动窗口里数值的最大值。例如，如果输入数组{2,3,4,2,6,2,5,1}及滑动窗口的大小3，那么一共存在6个滑动窗口，他们的最大值分别为{4,4,6,6,6,5}； 针对数组{2,3,4,2,6,2,5,1}的滑动窗口有以下6个： {[2,3,4],2,6,2,5,1}， {2,[3,4,2],6,2,5,1}， {2,3,[4,2,6],2,5,1}， {2,3,4,[2,6,2],5,1}， {2,3,4,2,[6,2,5],1}， {2,3,4,2,6,[2,5,1]}。

窗口大于数组长度的时候，返回空

```java
public ArrayList<Integer> maxInWindows(int [] num, int size) {
    ArrayList<Integer> list = new ArrayList<>();
    if (size <= 0 || size > num.length) return list;
    int m = -1;
    for (int i = 0;i <= num.length - size;i++) {
        System.out.println("m:" + m + ",i:" + i);
        if (m >= i) {
            int s = i + size - 1;
            if (num[s] < num[m]) {
                list.add(num[m]);
            } else {
                m = s;
                list.add(num[s]);
            }
            continue;
        }
        m = i;
        for (int j = i + 1;j < i + size;j++) {
            if (num[j] >= num[m]) {
                m = j;
            }
        }
        list.add(num[m]);
    }
    return list;
}
```



## JZ65  矩阵中的路径

请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。路径可以从矩阵中的任意一个格子开始，每一步可以在矩阵中向左，向右，向上，向下移动一个格子。如果一条路径经过了矩阵中的某一个格子，则该路径不能再进入该格子。 例如 [[a b c e] [s f c s] [a d e e]] 矩阵中包含一条字符串"bcced"的路径，但是矩阵中不包含"abcb"路径，因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入该格子。

```java
public boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
    if(str == null || str.length == 0 || matrix == null || matrix.length == 0 
       || str.length > matrix.length)
        return false;
    boolean[] vis = new boolean[matrix.length];
    for (int i = 0;i < matrix.length;i++) { 
        if(h(matrix, vis, str, 0, i / cols, i % cols, rows, cols)) // 递归每一个矩阵元素
            return true;
    }
    return false;
}

public boolean h(char[] matrix, boolean[] vis, char[] str, int x, int row, int col, int rows, int cols) {
    if (row < 0 || row >= rows || col < 0 || col >= cols) // 越界, 返回 false
        return false;
    int m = row * cols + col;
    if (vis[m] || matrix[m] != str[x]) // 当前被访问或当前不是要访问的元素, 返回 false
        return false;
    if(x == str.length - 1) // 最后一个要访问的元素, 返回 true
        return true;
    vis[m] = true; // 标记当前被访问
    if (h(matrix, vis, str, x + 1, row - 1, col, rows, cols) // 递归上下左右
        || h(matrix, vis, str, x + 1, row, col + 1, rows, cols)
        || h(matrix, vis, str, x + 1, row + 1, col, rows, cols)
        || h(matrix, vis, str, x + 1, row, col - 1, rows, cols))
        return true;
    vis[m] = false; // 移除标记
    return false;
}
```



## JZ66  机器人的运动范围

地上有一个m行和n列的方格。一个机器人从坐标0,0的格子开始移动，每一次只能向左，右，上，下四个方向移动一格，但是不能进入行坐标和列坐标的数位之和大于k的格子。 例如，当k为18时，机器人能够进入方格（35,37），因为3+5+3+7 = 18。但是，它不能进入方格（35,38），因为3+5+3+8 = 19。请问该机器人能够达到多少个格子？

```java
// 队列, 从 0,0 开始往 rows,cols 遍历
public int movingCount(int threshold, int rows, int cols) {
    if(threshold < 0) return 0;
    Queue<Integer> queue = new LinkedList<>();
    boolean[] vis = new boolean[rows * cols];
    queue.add(0);
    int count = 0;
    while (!queue.isEmpty()) {
        int len = queue.size();
        while (len-- > 0) {
            Integer x = queue.poll();
            if(vis[x]) continue;
            count++;
            int row = x / cols; int col = x % cols;
            if (col + 1 < cols && sum(row,col + 1) <= threshold)
                queue.add(x + 1);
            if (row + 1 < rows && sum(row + 1, col) <= threshold)
                queue.add(x + cols);
            vis[x] = true;
        }
    }
    return count;
}
public  int sum(int x, int y) {
    int sum = 0;
    while (x > 0) {
        sum += x % 10; x /= 10;
    }
    while (y > 0) {
        sum += y % 10; y /= 10;
    }
    return sum;
}
```

```java
// 递归, 递归上下左右
public int movingCount(int threshold, int rows, int cols) {
    if(threshold < 0)
        return 0;
    boolean[][] vis = new boolean[rows][cols];
    return m(vis, 0, 0, rows, cols, threshold);
}
public int m(boolean[][] vis, int row, int col, int rows, int cols, int k) {
    if(row < 0 || row >= rows || col < 0 || col >= cols 
       			|| vis[row][col] || sum(row, col) > k) // 越界或访问过或大于 K, 返回 0
        return 0;
    vis[row][col] = true;
    return 1 + m(vis, row - 1, col, rows, cols, k) // 递归上下左右
        + m(vis, row, col + 1, rows, cols, k) 
        + m(vis, row + 1, col, rows, cols, k) 
        + m(vis, row, col - 1, rows, cols, k);
}
public int sum(int x, int y) {
    int sum = 0;
    while (x > 0) {
        sum += x % 10; x /= 10;
    }
    while (y > 0) {
        sum += y % 10; y /= 10;
    }
    return sum;
}
```



## JZ67  剪绳子

给你一根长度为n的绳子，请把绳子剪成整数长的m段（m、n都是整数，n>1并且m>1，m<=n），每段绳子的长度记为k[1],...,k[m]。请问k[1]x...xk[m]可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。

输入描述:

> 输入一个数n，意义见题面。（2 <= n <= 60）

```java
public int cutRope(int target) {
    if(target <= 1) return 0;
    if(target <= 3) return target - 1;
    int max = 0;
    if (target % 3 == 0) { 
        max = (int)Math.pow(3, target / 3);
    } else {
        int x = target - 2;
        max = 2 * (int)Math.pow(3, x / 3) * Math.max(x % 3, 1);
    }
    return max;
}
```