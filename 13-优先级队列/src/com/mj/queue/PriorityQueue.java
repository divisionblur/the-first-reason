package com.mj.queue;

import java.util.Comparator;

import com.mj.heap.BinaryHeap;

/**
 * 用二叉堆实现优先级队列！使用关联的方式！
 * @param <E>
 */
public class PriorityQueue<E>{
	private BinaryHeap<E> heap;

	//自定义优先级!
	public PriorityQueue(Comparator<E> comparator){
		heap = new BinaryHeap<>(comparator);
	}
	//元素本来具备可比较性,不传比较器！
	public PriorityQueue(){
		this(null);
	}
	
	public int size() {
		return heap.size();
	}

	public boolean isEmpty() {
		return heap.isEmpty();
	}
	
	public void clear() {
		heap.clear();
	}

	public void enQueue(E element) {
		heap.add(element);
	}

	//让优先级最高的出队！
	public E deQueue(){
		return heap.remove();
	}

	public E front(){
		return heap.get();
	}
}
