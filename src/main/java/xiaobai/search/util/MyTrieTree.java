package xiaobai.search.util;

import java.util.ArrayList;
import java.util.List;



public class MyTrieTree {
	private static List<TrieNode> hotWord = new ArrayList<TrieNode>();
	private TrieNode root;// 字典树的根

	private static MyTrieTree instance=new MyTrieTree();
	  public static MyTrieTree getInstance() {
	      return instance;
	  }
	  
	public MyTrieTree() {// 初始化字典树
		root = new TrieNode();
	}



	// 建立字典树
	public void insert(String str) {// 插入一个单词
		if (str == null || str.length() == 0) {
			return;
		}
		TrieNode node = root;
		char[] letters = str.toCharArray();
		for (int i = 0, len = str.length(); i < len; i++) {
			int pos = letters[i];
			System.out.println("------------------"+pos);
			
			if (node.son[pos] == null) {
				node.son[pos] = new TrieNode();
				node.son[pos].val = letters[i];
				node.son[pos].parent = node;         //设置子节点对应的父节点

			}
			node = node.son[pos];

		}
		node.num++;
		node.isEnd = true;

	}

	public boolean has(String str) {// 查找完全匹配的单词
		if (str == null || str.length() == 0) {
			return false;
		}
		TrieNode node = root;
		char[] letters = str.toCharArray();
		for (int i = 0, len = str.length(); i < len; i++) {
			int pos = letters[i];
			if (node.son[pos] != null) {
				node = node.son[pos];
			} else {
				return false;
			}
		}
		return node.isEnd;
	}

	// 前序遍历字典树
	public void preTraverses(TrieNode node) {
		if (node != null) {
			System.out.println(node.val);
			for (TrieNode child : node.son) {
				preTraverses(child);
			}
		}

	}

	public TrieNode getRoot() {
		return this.root;
	}

	public TrieNode prefix(String prefix) {// 计算单词前缀的数量
		if (prefix == null || prefix.length() == 0) {
			return null;
		}
		TrieNode node = root;
		char[] letters = prefix.toCharArray();
		for (int i = 0, len = prefix.length(); i < len; i++) {
			int pos = letters[i];
			if (node.son[pos] == null) {
				return null;
			} else {
				node = node.son[pos];
			}

		}
		return node;

	}
	
	public void findSon(TrieNode node){
		if(node.num>0){
			hotWord.add(node);
		}
		for(int i = 0 ; i < node.son.length ; i ++){
			if(node.son[i] != null){
				findSon(node.son[i]);
			}
				
		}
	}
	
	public String printf(TrieNode trieNode){
		if(trieNode.parent!=null){
			return printf(trieNode.parent)+trieNode.val;
		}
		return String.valueOf(trieNode.val);
	}
	
	
	
	
	public static List<TrieNode> getHotWord() {
		return hotWord;
	}

	public static void setHotWord(List<TrieNode> hotWord) {
		MyTrieTree.hotWord = hotWord;
	}
}
