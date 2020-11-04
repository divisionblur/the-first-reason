package com.mj;

import java.util.HashMap;

public class Trie<V> {
	private int size;
	private Node<V> root;
	
	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void clear() {
		size = 0;
		root = null;
	}

	public V get(String key){
		Node<V> node = node(key);
		return node != null && node.word ? node.value : null;
	}

	public boolean contains(String key) {
		Node<V> node = node(key);
		return node != null && node.word;  // word==true说明存在这个单词！
	}

	public V add(String key, V value) {
		keyCheck(key);
		// 创建根节点
		if (root == null) {
			root = new Node<>(null);
		}

		Node<V> node = root;
		int len = key.length();
		for (int i = 0; i < len; i++){
			char c = key.charAt(i);
			boolean emptyChildren = node.children == null; //node.children这个引用没有指向一个对象(hashmap实例)
			Node<V> childNode = emptyChildren ? null : node.children.get(c);//如果这个引用指向了一个hashmap
																	// 通过key获取下一个节点的位置即value(Node)
			if (childNode == null){
				childNode = new Node<>(node);
				childNode.character = c;
				node.children = emptyChildren ? new HashMap<>() : node.children;//第一次添加"单词"的时候node中的hashmap引用肯定
				//没有指向一个hashmap实例，就需要new一个,如果不是第一次添加引用肯定指向了一个实例那么就不做处理
				node.children.put(c, childNode);
			}
			node = childNode;
		}

		if (node.word) { //已经存在这个单词
			V oldValue = node.value;  //将新的value替换就得value
			node.value = value;
			return oldValue;
		}

		// 新增一个单词
		node.word = true;
		node.value = value;  //value可以作为这个单词对应的一个数据，存放在这个单词的最后一个字符所在的Node中
		size++;		//字典树中新增了一个单词
		return null;
	}



	//删除一个完整的单词，并且返回这个单词对应的Value
	public V remove(String key){
		// 找到要删除的字符串的最后一个节点
		Node<V> node = node(key);
		// 如果不是单词结尾，不用作任何处理
		if (node == null || ! node.word) return null;
		size--;
		V oldValue = node.value;
		
		// 如果还有子节点
		if (node.children != null && !node.children.isEmpty()) {
			node.word = false;   //把这个位置的"单词结尾"置为false!
			node.value = null;
			return oldValue;
		}
		
		// 如果没有子节点
		Node<V> parent = null;
		while ((parent = node.parent) != null) {
			parent.children.remove(node.character); //hashmap里的remove  通过key找到节点,再删除节点
			if (parent.word || !parent.children.isEmpty()) break;  //发现父节点是一个单词的结尾即word==true,
																// 或者父节点还有其他的子节点！
			node = parent;
		}
		return oldValue;
	}

	public boolean startsWith(String prefix) {
		return node(prefix) != null;
	}


	//找到一个字符串的最后一个字符所在的Node
//	private Node<V> node(String key){
//		keyCheck(key);
//
//		Node<V> node = root;
//		int len = key.length();
//		for (int i = 0; i < len; i++){
//			if (node == null || node.children == null || node.children.isEmpty()) return null;
//			//确保节点不是null,节点里的hashmap类型引用有指向的hashmap实例,hashmap不为空！
//			char c = key.charAt(i);
//			node = node.children.get(c);  //get是通过key获取value，而value的类型就是Node<>  没找到对应的value也会返回null
//		}
//		return node;
//	}

	private Node<V> node(String key){
		keyCheck(key);

		Node<V> node=root;
		int len = key.length();
		for (int i = 0; i <len; i++)  {
			if( node==null || node.children==null || node.children.isEmpty() ) return null;

			char c = key.charAt(i);
			node = node.children.get(c);
		}
		return node;
	}


	
	private void keyCheck(String key){
		if (key == null || key.length() == 0) {
			throw new IllegalArgumentException("key must not be empty");
		}
	}
	
	private static class Node<V> {
		Node<V> parent;
		HashMap<Character, Node<V>> children;   //HashMap类型的引用
		Character character;   //每一个节点对应的字符是什么！
		V value;
		boolean word; // 是否为单词的结尾（是否为一个完整的单词）
		public Node(Node<V> parent){
			this.parent = parent;
		}
	}
}
