package com.mj.queue;

import java.util.Comparator;

import com.mj.heap.BinaryHeap;

/**
 * �ö����ʵ�����ȼ����У�ʹ�ù����ķ�ʽ��
 * @param <E>
 */
public class PriorityQueue<E>{
	private BinaryHeap<E> heap;

	//�Զ������ȼ�!
	public PriorityQueue(Comparator<E> comparator){
		heap = new BinaryHeap<>(comparator);
	}
	//Ԫ�ر����߱��ɱȽ���,�����Ƚ�����
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

	//�����ȼ���ߵĳ��ӣ�
	public E deQueue(){
		return heap.remove();
	}

	public E front(){
		return heap.get();
	}
}
