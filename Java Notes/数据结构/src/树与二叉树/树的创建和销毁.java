package 树与二叉树;

import java.util.Scanner;

public class 树的创建和销毁
{
	/*
	 * 	通过先序和中序遍历创建树
	 * 
	 * 	算法: 
	 * 		A: 通过先序遍历找到根节点,再通过根节点在中序遍历的位置找出左子树,右子树
	 * 		B: 在根的左子树中,找到左子树的根节点(在先序中找),转步骤A
	 * 		C: 在根的右子树中,找到右子树的根节点(在先序中找),转步骤A
	 */
	
	/*
	 * 	#号法创建树
	 * 		用   # 补齐树的空叶子,再进行遍历,即可用得到的遍历结果创建一个树
	 * 
	 * 		按前序输入二叉线索树中的节点值,构造二叉线索树
	 */
	public static BinaryTree createTree_1(BinaryTree bt)
	{
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		System.out.print("输入下一节点值: ");
		String s = scanner.nextLine();	//输入下一节点值
		//# 代表该节点为空
		if(s.trim().equals("#"))
			bt = null;
		else if(s.trim().matches("\\d+"))
		{
			//构造根节点(前序)
			bt = new BinaryTree(Integer.parseInt(s.trim()));	
			//递归构造左子树
			bt.setLeft(createTree_1(bt.getLeft()));	
			//递归构造右子树		
			bt.setRight(createTree_1(bt.getRight()));		
		}
		//若输入错误,则重新构建该节点
		else
		{
			System.out.print("输入有误! ");
			//则重新构建该节点
			bt = createTree_1(bt);
		}
		return bt;
	}
	
	/*
	 *  后序遍历销毁树
	 *  
	 */	
	public static BinaryTree deleteTree_houxu(BinaryTree root)
	{
		if(root == null)
			return null;
		//递归销毁左子树
		root.setLeft(deleteTree_houxu(root.getLeft()));
		//递归销毁右子树
		root.setRight(deleteTree_houxu(root.getRight()));
		//销毁根节点
		root = null;
		return root;
	}
}
