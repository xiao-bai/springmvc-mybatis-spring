package xiaobai.search.test;

import java.util.ArrayList;
import java.util.List;

import xiaobai.search.util.MinHeap;
import xiaobai.search.util.MyTrieTree;
import xiaobai.search.util.TrieNode;

public class TestTree {
	private static List<TrieNode> hotWord = new ArrayList<TrieNode>();
	public static void main(String[] args) {
		MyTrieTree tree = MyTrieTree.getInstance();
		TrieNode n = null ;
		String[] strs = {"广广光","广广光","广广光","广广光","广广光","广广光","广州大学" ,"广州大学" ,"广州市站" ,  "广大群众" , "广大群众", "广大群众", "广东工业大学","广东工业大学","广大群众","广州广州","广州日报","广州市","广州","广州 好地方","广州","广州","广东省","广东 是这样的","广州是个大城市"};
		String[] prefix = { "广广"};
		for (String str : strs) {
			tree.insert(str);
		}
		for (String str : strs) {
			tree.insert(str);
		}
		for (String pre : prefix) {
			n = tree.prefix(pre);
		}
		System.out.println(n);
		tree.findSon(n);//这个方法中加入小顶堆去查找最大的十个就行了
		
		hotWord = MyTrieTree.getHotWord();
		System.out.println(hotWord.size());
		for(TrieNode trie : hotWord){
				System.out.println(tree.printf(trie)+"     ----查找了:"+trie.num);
		}
		System.out.println("------------------");
		TrieNode[] top5 = topK(hotWord, 5);
		for(int i=4; i>0 ; i--)
		{
			for(int j=0 ; j < i ; j++ ){
				if(top5[j+1]!= null && top5[j].num < top5[j+1].num ){
					TrieNode temp = top5[j] ;
					top5[j] = top5[j+1] ;
					top5[j+1] = temp ;
				}
			}
		}
		for(int i = 0 ; i < 5 && top5[i] !=null ; i ++){
			System.out.println(top5[i].num);
		}
		
	}
	// 从data数组中获取最大的k个数
	private static TrieNode[] topK(List<TrieNode> data,int k)
	{
		// 先取K个元素放入一个数组topk中
		TrieNode[] topk = new TrieNode[k]; 
		for(int i = 0;i< k && i < data.size() ;i++)
		{
			topk[i] = data.get(i);
		}
		
		// 转换成最小堆
		MinHeap heap = new MinHeap(topk);
		
		// 从k开始，遍历data
		for(int i= k;i<data.size();i++)
		{
			TrieNode root = heap.getRoot();
			
			// 当数据大于堆中最小的数（根节点）时，替换堆中的根节点，再转换成堆
			if(data.get(i).num > root.num)
			{
				heap.setRoot(data.get(i));
			}
		}
		
		return topk;
}
}
