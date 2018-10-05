package 树与二叉树;

public class Test_树的创建和销毁的测试
{
	public static void main(String[] args)
	{
		BinaryTree bt = null;
		//创建树
		bt = 树的创建和销毁.createTree_1(bt);
		System.out.println("\n--------------------------");
		//先序遍历数
		BinaryTree.xianxu(bt);
		System.out.println("\n--------------------------");
		//销毁树
		bt = 树的创建和销毁.deleteTree_houxu(bt);
		//先序遍历数
		BinaryTree.xianxu(bt);						//null
		//输出树的深度
		System.out.println(BinaryTree.shenDu(bt));	//0
	}
}
