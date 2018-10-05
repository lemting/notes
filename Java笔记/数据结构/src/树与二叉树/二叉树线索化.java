package 树与二叉树;

public class 二叉树线索化
{
	private int data = 0;
	private 二叉树线索化 right = null;
	private 二叉树线索化 left = null;
	private boolean rtag = false;
	private boolean ltag = false;
	
	/*
	 * 	二叉树线索化算法(以中序遍历为例)
	 * 	
	 * 	本质: 让前后节点,建立关系
	 * 		线索化的过程就是在遍历过程中修改空指针的过程
	 * 		将空的left 改为节点的直接前驱
	 * 		将空的right 改为节点的直接后继   
	 * 
	 * 	规定: 
	 * 		A: 若节点有左子树,则left指向其左子树;否则,left 指向其直接前驱(即线索)
	 * 		B: 若节点有右子树,则right指向其左子树;否则,right 指向其直接后继(即线索)
	 * 		
	 * 		为了避免混淆,增加两个标志域:  | left | LTag | data | RTag | right |
	 * 		约定: 当  Tag 域为 0 时,表示正常情况;当  Tag 域为 1 时,表示线索情况
	 *  
	 */
	private static 二叉树线索化 temp = null;//让其始终指向刚刚访问的节点
	//中序遍历进行中序线索化
	public static void xiansuohua(二叉树线索化 root)
	{
		if(root == null)
			return;
		if(temp == null)
			temp = root;
		
		//左子树线索化
		二叉树线索化.xiansuohua(root.getLeft());
		
		//若没有左子树
		if(root.getLeft() == null)
		{
			root.setLtag(true);		//前驱线索
			root.setLeft(temp);		//左子树指向前驱
		}
		//若前驱没有右子树
		if(temp.getRight() == null)
		{
			temp.setRtag(true);		//后继线索
			temp.setRight(root);	//右子树指向后继
		}
		// 
		temp = root;
		
		//右子树线索化
		二叉树线索化.xiansuohua(root.getRight());
	}
	//中序遍历二叉线索树(非递归,且不用栈)
	
	public boolean getRtag(){return rtag;}
	public void setRtag(boolean rtag){this.rtag = rtag;}
	public boolean getLtag(){return ltag;}
	public void setLtag(boolean ltag){this.ltag = ltag;}
	public 二叉树线索化(){}
	public 二叉树线索化(int data){setData(data);}
	public int getData(){return data;}
	public void setData(int data){this.data = data;}
	public 二叉树线索化 getRight(){return right;}
	public void setRight(二叉树线索化 right){this.right = right;}
	public 二叉树线索化 getLeft(){return left;}
	public void setLeft(二叉树线索化 left){this.left = left;}
}
