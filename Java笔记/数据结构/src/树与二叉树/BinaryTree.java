package 树与二叉树;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/*
 *  树的叶子: 没有后继的节点
 * 	节点的度: 后继个数
 * 	树的度: 节点的度中最高的
 * 	树的深度(高度): 树的最高层数
 */

public class BinaryTree
{
	private int data = 0;
	private BinaryTree right = null;
	private BinaryTree left = null;
	private boolean qizi = false;	
	
	//添加元素
	public static void add(BinaryTree root,int data)
	{
		if(root == null)
			return;
		
		if(data > root.data)
		{
			if(root.left == null)
			{				
				root.left = new BinaryTree(data);
				return;
			}
			else
				add(root.left,data);
		}
		else if(data < root.data)
		{
			if(root.right == null)
			{				
				root.right = new BinaryTree(data);
				return;
			}
			else
				add(root.right, data);
		}
	} 
	
	//二叉树的层次遍历
	//利用队列储存,获取每一层的节点
	public static void cengci(BinaryTree root) 
    {
		//若根为空,则直接结束
		if(root != null)
		{
			Queue<BinaryTree> queue = new LinkedList<BinaryTree>();
			//根节点入队列
			queue.add(root);
			//若队列为空,则表示遍历结束
			while(!queue.isEmpty())
			{
				//获取队列的长度
				int len = queue.size();
				//获取队列里的每一个元素,并向队列里添加元素的子树节点
				for(int i = 0;i < len;i++)
				{
					//获取并删除节点
					BinaryTree temp = queue.poll();
					//输出节点信息
					System.out.print(temp.data + "\t");
					//若节点有左子树,则左子树入队列
					if(temp.left != null)
						queue.add(temp.left);
					//若节点有右子树,则右子树入队列
					if(temp.right != null)
						queue.add(temp.right);
				}
				System.out.println();
			}
		}
    }
	
	//中序遍历非递归
	public static void zhongxu_feidigui_2(BinaryTree root)
	{
		Stack<BinaryTree> stack = new Stack<BinaryTree>();
		//若节点为空且栈为空,则遍历结束
		while(root != null || !stack.empty())
		{
			//若节点不为空,则该节点入栈,继续向左走
			if(root != null)
			{
				stack.push(root);
				root = root.left;
			}
			else
			{
				//否则根据栈顶指示回退
				root = stack.pop();
				//输出该节点信息
				System.out.print(root.data + "\t");
				//继续向右走
				root = root.right;
			}
		}
	}
	//中序遍历非递归算法
	/*
	 * 	A: 若节点有左子树,该节点入栈;否则访问该节点
	 * 	B: 若节点有右子树,重复A;若节点没有右子树(节点访问完毕),根据栈顶指示回退,访问栈顶元素,并访问右子树,重复A;若栈为空,表示遍历结束
	 */
	//一直向左走,直到节点没有左子树
	public static BinaryTree goLeft(BinaryTree root,Stack<BinaryTree> stack)
	{
		if(root == null)
			return null;
		//若节点有左子树
		while(root.left != null)
		{	
			//该节点入栈
			stack.push(root);
			//继续向左访问
			root = root.left;
		}
		//返回最左端子树
		return root;
	}
	//非递归中序遍历
	public static void zhongxu_feidigui(BinaryTree root)
	{
		Stack<BinaryTree> stack = new Stack<BinaryTree>();
		//从最左端节点开始遍历
		root = goLeft(root,stack);
		while(root != null)
		{
			//访问该节点
			System.out.print(root.data + "\t");
			//若节点有右子树
			if(root.right != null)
				root = goLeft(root.right, stack); //于该节点下找到最左端子树
			//若节点没有右子树且栈不为空,根据栈顶指示回退
			else if(!stack.empty())
				root = stack.pop();
			//若节点没有右子树且栈为空,表示遍历结束
			else
				root = null;
		}
	}
	
	//拷贝树
	public static BinaryTree copyTree(BinaryTree root)
	{
		if(root == null)
			return null;
		//先拷贝根
		BinaryTree temp = new BinaryTree(root.data);
		//再拷贝左子树
		if(root.left != null)
			temp.left = copyTree(root.left);
		//再拷贝右子树
		if(root.right != null)
			temp.right = copyTree(root.right);

		return temp;
	}	
 	//求树深度
	public static int shenDu(BinaryTree root)
	{
	 	if(root == null)
			return 0;
		//用num1和num2分别储存左子树和右子树的深度
		int num1 = shenDu(root.left);	
		int num2 = shenDu(root.right);	
		//返回左右子树深度大的值
		return num1 > num2 ? num1 + 1: num2 + 1;
	}
	//求叶子数
	public static int hasYeZi(BinaryTree root)
	{
		int num = 0;
		if(root == null)
			return 0;
		if(root.getLeft() == null && root.getRight() == null)
			return 1;
		num += hasYeZi(root.getLeft());
		num += hasYeZi(root.getRight());	
		return num;
	}
	//先序遍历
	public static void xianxu(BinaryTree root)
	{
		if(root == null)
			return;
		System.out.print(root.data + "\t");
		xianxu(root.left);
		xianxu(root.right);
	}
	//中序遍历
	public static void show(BinaryTree root)
	{
		if(root == null)
			return;
		show(root.left);
		System.out.print(root.data + "\t");
		show(root.right);
	}
	//后序遍历
	public static void houxu(BinaryTree root)
	{
		if(root == null)
			return;
		show(root.left);
		show(root.right);
		System.out.print(root.data + "\t");
	}
		
	public int getData(){return data;}
	public void setData(int data){this.data = data;}
	public BinaryTree getRight(){return right;}
	public void setRight(BinaryTree right){this.right = right;}
	public BinaryTree getLeft(){return left;}
	public void setLeft(BinaryTree left){this.left = left;}
	public boolean isQizi(){return qizi;}
	public void setQizi(boolean qizi){this.qizi = qizi;}
	public BinaryTree(){}	
	public BinaryTree(int data){this.data = data;}
}
