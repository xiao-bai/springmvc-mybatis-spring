package xiaobai.mybatis.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import xiaobai.search.util.FileUtil;
import xiaobai.search.util.MinHeap;
import xiaobai.search.util.MyTrieTree;
import xiaobai.search.util.SearchResult;
import xiaobai.search.util.TopSearcherTree;
import xiaobai.search.util.TrieNode;

@Controller
@RequestMapping("/hotWordController")
public class HotWordController {

	@RequestMapping(value = "/find/{word}", method = RequestMethod.POST)
	public String find(@PathVariable String word, HttpServletRequest request) {
		System.out.println("..............................."+word);
		List<TrieNode> hotWord = new ArrayList<TrieNode>();
		List<SearchResult> list = new ArrayList<SearchResult>();
		MyTrieTree tree = MyTrieTree.getInstance();
		TrieNode node = tree.prefix(word);
		
		MyTrieTree.setHotWord(hotWord);//由于使用单例，所以使用之前一定要清空，不然就会将之前的结果放在里面
		tree.findSon(node);
		if(node != null){
			hotWord = MyTrieTree.getHotWord();
			System.out.println(hotWord.size());
			for (TrieNode trie : hotWord) {
				System.out.println(tree.printf(trie) + "     ----查找了:" + trie.num);
			}
			TrieNode[] top = topK(hotWord, 10);
			for (int i = 9; i > 0; i--) {
				for (int j = 0; j < i; j++) {
					if (top[j+1]!= null &&top[j].num < top[j + 1].num) {
						TrieNode temp = top[j];
						top[j] = top[j + 1];
						top[j + 1] = temp;
					}
				}
			}
			String result = "";
			SearchResult r = null ;
			for (int i = 0; i < 10 && top[i] !=null; i++) {
				result = tree.printf(top[i]);
				if(!result.equals("")){
					r = new SearchResult();
					r.setVal(result.substring(word.length()));
					r.setNum(top[i].num);
					list.add(r);
				}
			}
			request.setAttribute("words", list);
			request.setAttribute("prefix", word);
		}
		
		return "page/json/hotword-json";
	}

	/**
	 * search方法应该跳转到查找结果的页面，这里使用来测试，所以返回void
	 */
	@RequestMapping(value = "/search/{word}", method = RequestMethod.POST)
	public String search(@PathVariable String word, HttpServletRequest request) {
		MyTrieTree tree = MyTrieTree.getInstance();
		FileUtil fileUtil = FileUtil.getInstance();
		
		if(!fileUtil.createTxt()){
			System.out.println("将记录保存入数据库");
		}
		if(word!= null && word !=""){
			tree.insert(word);
		}
		fileUtil.writeByRandomAccess(word);
		request.setAttribute("result", "1");
		return "page/json/result-json";
	}
	
	
	@RequestMapping(value="/topsearch/{type}/",method = RequestMethod.GET)
	public String topSearch(@PathVariable String type, HttpServletRequest request){
		TopSearcherTree topsearcher = new TopSearcherTree();
		if(type.equals("hour")){
			
		}
		request.setAttribute("result", "1");
		return "page/json/result-json"; 
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
