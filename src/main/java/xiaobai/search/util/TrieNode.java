package xiaobai.search.util;


public class TrieNode {
	public TrieNode parent;//该节点对应的父节点
	public int num;// 该词的搜索次数
	public TrieNode[] son;// 所有的儿子节点
	public boolean isEnd;// 是不是最后一个节点
	public String val;// 节点的值
	
	public TrieNode() {
		num = 0;
		son = new TrieNode[40000];
		parent = null;
		isEnd = false;
		val = "";
	}
}
