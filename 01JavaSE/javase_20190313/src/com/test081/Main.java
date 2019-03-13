package com.test081;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Main {

	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<Integer>();
		
		//넣기
		stack.push(10);
		stack.push(20);
		stack.push(30);
		
		//꺼내기
		System.out.println(stack.pop()); // 30
		System.out.println(stack.pop()); // 20
		System.out.println(stack.pop()); // 10
		
		Queue<Integer> queue = new LinkedList<Integer>();
		
		//넣기
		queue.offer(10);
		queue.offer(20);
		queue.offer(30);
		
		//꺼내기
		System.out.println(queue.poll()); // 10
		System.out.println(queue.poll()); // 20
		System.out.println(queue.poll()); // 30
		
	}

}
