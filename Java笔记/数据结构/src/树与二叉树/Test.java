package 树与二叉树;

public class Test
{
	//679: 落球
	public static void luoQiu(BinaryTree root,BinaryTree head)
	{
		//若根为空则退出
		if(root == null)
			return;
		//若数只剩树根则输出树根并退出
		if(head.getLeft() == null && head.getRight() == null)
		{
			System.out.println(head.getData());
			head = null;
			return;
		}
		//若当前节点为false
		if(!root.isQizi())
		{
			//转换
			root.setQizi(true);
			//若左子为叶子
			if(root.getLeft() != null && root.getLeft().getLeft() == null && root.getLeft().getRight() == null)
			{
				//输出左子
				System.out.println(root.getLeft().getData());
				//删除左子
				root.setLeft(null);
				//每次落完一球就递归调用重新开始落球
				luoQiu(head,head);
				return;
			}
			else
				//左子不为叶子则递归
				luoQiu(root.getLeft(),head);
		}
		else
		{
			//转换
			root.setQizi(false);
			//若右子为叶子
			if(root.getRight() != null && root.getRight().getLeft() == null && root.getRight().getRight() == null)
			{
				//输出右子
				System.out.println(root.getRight().getData());
				//删除右子
				root.setRight(null);
				//每次落完一球就递归调用重新开始落球
				luoQiu(head,head);
				return;
			}
			else
				//右子不为叶子则递归
				luoQiu(root.getRight(),head);
		}
	}
	
	public static void main(String[] args)
	{
		BinaryTree root = new BinaryTree(10);	
		BinaryTree.add(root, 15);
		BinaryTree.add(root, 17);
		BinaryTree.add(root, 13);
		BinaryTree.add(root, 2);
		BinaryTree.add(root, 4);
		BinaryTree.add(root, 0);
		BinaryTree.add(root, 20);
		BinaryTree.add(root, 16);
		BinaryTree.add(root, 14);
		BinaryTree.add(root, 12);
		BinaryTree.add(root, 5);
		BinaryTree.add(root, 3);
		BinaryTree.add(root, 1);
		BinaryTree.add(root, -1);
	
		//拷贝数
		BinaryTree copyroot = null;
		copyroot = BinaryTree.copyTree(root);
		//层次遍历
		BinaryTree.cengci(root);
		System.out.println("---------------------------");
		//非递归中序遍历
		BinaryTree.zhongxu_feidigui(root);
		System.out.println("\n---------------------------");
		//非递归中序遍历_2
		BinaryTree.zhongxu_feidigui_2(root);
		System.out.println("\n---------------------------");
		//中序遍历完全二叉树
		BinaryTree.show(copyroot);
		System.out.println("\n---------------------------");
		//后序遍历完全二叉树
		BinaryTree.houxu(copyroot);
		System.out.println("\n---------------------------");
		//子叶数
		System.out.println(BinaryTree.hasYeZi(root));
		System.out.println("---------------------------");
		//深度
		System.out.println(BinaryTree.shenDu(root));
		System.out.println("---------------------------");
		//落球
		luoQiu(root,root);		
	}
}
