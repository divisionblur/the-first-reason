package com.mj.heap;

import java.util.Comparator;

import com.mj.printer.BinaryTreeInfo;

/**
 * 二叉堆（最大堆）使用动态数组实现，逻辑上的结构相当于一棵二叉树！
 * @author MJ Lee
 *
 * @param <E>
 */
@SuppressWarnings("unchecked")
public class BinaryHeap<E> extends AbstractHeap<E> implements BinaryTreeInfo {
	private E[] elements;
	private static final int DEFAULT_CAPACITY = 10;
	//	protected int size;
	//	protected Comparator<E> comparator;

	//传入一个数组叫你批量建队，当然也传入了一个比较器!
	public BinaryHeap(E[] elements, Comparator<E> comparator)  {
		super(comparator);
		
		if (elements == null || elements.length == 0) {
			this.elements = (E[]) new Object[DEFAULT_CAPACITY];  //没有传数组过来，就按照默认的办法做！
		} else {
			size = elements.length;   //现在是将数据一次性直接拷贝过来，那么size的值也就是一次性赋值的！
			int capacity = Math.max(elements.length, DEFAULT_CAPACITY);  //保证有DEFAULT_CAPACITY的大小！
			this.elements = (E[]) new Object[capacity];
			for (int i = 0; i < elements.length; i++){

				this.elements[i] = elements[i];  //将数据放到二叉堆中的数组中但还是乱七八糟的，成员变量和形参名一样，用this区分！
			}
			heapify();
		}
	}
	//下面的几个构造函数都是调用上面的构造函数来进行统一处理！
	public BinaryHeap(E[] elements)  {
		this(elements, null);
	}
	
	public BinaryHeap(Comparator<E> comparator) {
		this(null, comparator);
	}
	
	public BinaryHeap() {
		this(null, null);
	}

	@Override
	public void clear() {
		for (int i = 0; i < size; i++) {
			elements[i] = null;
		}
		size = 0;
	}

	@Override
	public void add(E element) {
		elementNotNullCheck(element);
		ensureCapacity(size + 1);
		elements[size++] = element;
		siftUp(size - 1);
	}

	@Override
	public E get() {
		emptyCheck();
		return elements[0];  //获取堆顶元素！
	}

	@Override
	public E remove() {
		emptyCheck();
		int lastIndex = --size; //最后一个元素在数组中的索引！
		E root = elements[0];   //先把堆顶元素拿出来，方便待会儿返回！
		elements[0] = elements[lastIndex];  //用最后一个元素覆盖第一个元素
		elements[lastIndex] = null; //删除最后一个元素！
		
		siftDown(0);//将放在堆顶的元素进行下滤！
		return root;  //返回删除的堆顶元素！
	}

	@Override
	public E replace(E element) {
		elementNotNullCheck(element);
		
		E root = null;
		if (size == 0) {//原来的堆中即数组中都没有元素，直接在堆顶即第一个位置(索引为0)添加一个元素即可！
			elements[0] = element;
			size++;
		} else {
			root = elements[0];
			elements[0] = element;
			siftDown(0);
		}
		return root;
	}
	
	/**
	 * 批量建堆
	 */
	private void heapify(){
		// 自上而下的上滤
//		for (int i = 1; i < size; i++) {
//			siftUp(i);
//		}
		
		// 自下而上的下滤  (size>>1)是第一个叶子节点的索引位置，那么此时再进行减一的话就是最后一个非叶子节点的索引位置。
		for (int i = (size >> 1) - 1; i >= 0; i--) {
			siftDown(i);
		}
	}
	
	/**
	 * 让index位置的元素下滤
	 * @param index
	 */
	private void siftDown(int index){//删除完堆顶进行处理  index开始就是0
		E element = elements[index];//现将要下滤的元素拿出来，放在一边！

		int half = size >> 1;// 第一个叶子节点的索引 == 非叶子节点的数量

		// index < 第一个叶子节点的索引
		// 必须保证index位置是非叶子节点
		while (index < half){
			// index的节点有2种情况
			// 1.只有左子节点,肯定不会是右子节点。(不可能出现有右无左！)
			// 2.同时有左右子节点
			// 默认为左子节点跟它进行比较
			int childIndex = (index << 1) + 1;  //(2i+1)是左子节点的索引！
			E child = elements[childIndex];
			
			// 右子节点
			int rightIndex = childIndex + 1;  //(左子节点索引位置加1就是右子节点索引的位置！)
			//索引的范围是  (0---size-1);
			// 选出左右子节点最大的那个(右边比左边大的话，就用右边喽！)
			if (rightIndex <= size-1 && compare(elements[rightIndex], child) > 0) { //右子节点的索引在有效范围内
				child = elements[childIndex = rightIndex];
			}
			
			if (compare(element, child) >= 0) break;

			// 将子节点存放到index位置
			elements[index] = child;//将子节点中大的元素挪到自己的位置上！
			// 重新设置index
			index = childIndex;
		}
		elements[index] = element;
	}
	
	/**
	 * 让index位置的元素上滤
	 * @param index
	 */
	private void siftUp(int index) {
//		E e = elements[index];
//		while (index > 0) {
//			int pindex = (index - 1) >> 1;  //获取父节点的索引！
//			E p = elements[pindex];
//			if (compare(e, p) <= 0) return;
//			
//			// 交换index、pindex位置的内容
//			E tmp = elements[index];
//			elements[index] = elements[pindex];
//			elements[pindex] = tmp;
//			
//			// 重新赋值index
//			index = pindex;
//		}

		E element = elements[index];

		while (index > 0) {
			int parentIndex = (index - 1) >> 1; //floor((i-1)/2),求取父节点的索引位置!
			E parent = elements[parentIndex];
			if (compare(element, parent)<= 0) break;
			
			// 将父元素存储在index位置
			elements[index] = parent;
			// 重新赋值index
			index = parentIndex;
		}
		elements[index] = element;
	}
	
	private void ensureCapacity(int capacity) {
		int oldCapacity = elements.length;
		if (oldCapacity >= capacity) return;
		
		// 新容量为旧容量的1.5倍
		int newCapacity = oldCapacity + (oldCapacity >> 1);
		E[] newElements = (E[]) new Object[newCapacity];
		for (int i = 0; i < size; i++) {
			newElements[i] = elements[i];
		}
		elements = newElements;
	}
	
	private void emptyCheck() {
		if (size == 0) {
			throw new IndexOutOfBoundsException("Heap is empty");
		}
	}
	
	private void elementNotNullCheck(E element) {
		if (element == null) {
			throw new IllegalArgumentException("element must not be null");
		}
	}

	@Override
	public Object root() {
		return 0;
	}

	@Override
	public Object left(Object node) {
		int index = ((int)node << 1) + 1;
		return index >= size ? null : index;
	}

	@Override
	public Object right(Object node) {
		int index = ((int)node << 1) + 2;
		return index >= size ? null : index;
	}

	@Override
	public Object string(Object node) {
		return elements[(int)node];
	}
}
