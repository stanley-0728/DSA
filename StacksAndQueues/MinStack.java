package StacksAndQueues;

import java.util.ArrayList;
import java.util.List;

public class MinStack {
  List<Integer> stack;
  List<Integer> minStack;

  public MinStack() {
    stack = new ArrayList<>();
    minStack = new ArrayList<>();
  }

  public void push(int val) {
    stack.add(val);
    if (minStack.isEmpty() || val <= minStack.get(minStack.size() - 1))
      minStack.add(val);
  }

  public void pop() {
    if (!stack.isEmpty()) {
      int removed = stack.remove(stack.size() - 1);
      if (!minStack.isEmpty() && removed == minStack.get(minStack.size() - 1))
        minStack.remove(minStack.size() - 1);
    }
  }

  public int top() {
    return stack.size() > 0 ? stack.get(stack.size() - 1) : -1;
  }

  public int getMin() {
    return minStack.size() > 0 ? minStack.get(minStack.size() - 1) : -1;

  }

}
