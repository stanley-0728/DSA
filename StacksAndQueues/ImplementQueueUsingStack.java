package StacksAndQueues;

import java.util.*;

public class ImplementQueueUsingStack {
  Stack<Integer> stack1;
  Stack<Integer> stack2;

  public ImplementQueueUsingStack() {
    stack1 = new Stack<>();
    stack2 = new Stack<>();
  }

  public void push(int x) {
    stack1.add(x);
  }

  public int pop() {
    if (stack2.isEmpty()) {
      while (!stack1.isEmpty()) {
        stack2.push(stack1.pop());
      }
    }
    return stack2.isEmpty() ? -1 : stack2.pop();
  }

  public int peek() {
    if (stack2.isEmpty()) {
      while (!stack1.isEmpty()) {
        stack2.push(stack1.pop());
      }
    }
    return stack2.isEmpty() ? -1 : stack2.peek();
  }

  public boolean empty() {
    return stack1.isEmpty() && stack2.isEmpty();
  }
}
