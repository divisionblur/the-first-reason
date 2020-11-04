package com.mj;

public class Main {

	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<>();
		new java.util.Stack<>();
		stack.push(11);
		stack.push(22);
		stack.push(33);
		stack.push(44);
		
		while (!stack.isEmpty()) {
			System.out.println(stack.pop());
		}
	}

}
