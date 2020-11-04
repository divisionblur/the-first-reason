package com.mj;
import com.mj.list.ArrayList;
import com.mj.list.List;

/**
 * 用动态数组来实现栈数据结构！
 * 调用动态数组的一些接口而已
 * 这里才用的是"关联"  而不是单纯的去继承动态数组，这样会继承一些栈数据结构所不需要的一些接口方法！
 * 采用"关联"的方式，只需要调用动态数组中实现栈操作的接口方法！
 * 当然JDK里的栈是继承的  class Stack<E> extends Vector<E>
 * @param <E>
 */

public class Stack<E> {
	private List<E> list = new ArrayList<>();
	public void clear() {
		list.clear();
	}
	
	public int size(){
		return list.size();
	}

	public boolean isEmpty() {
		return list.isEmpty();
	}

	public void push(E element) {
		list.add(element);
	}


	public E pop(){
		return list.remove(list.size() - 1);
	}

	//看一下栈顶元素是谁！
	public E top() {
		return list.get(list.size() - 1);
	}
}
