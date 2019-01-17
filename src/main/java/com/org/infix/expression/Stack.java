package com.org.infix.expression;

public class Stack<T> {
	private int stackSize;
    private T[] stackArr;
    private int top;
    
    @SuppressWarnings("unchecked")
	public Stack(int size) {
        this.stackSize = size;
        this.stackArr = (T[]) new Object[stackSize];
        this.top = -1;
    }
    
    public void push(T entry){
        if(this.isStackFull()){
            System.out.println(("Stack is full, so increasing the capacity."));
            this.increaseStackCapacity();
        }
        System.out.println("Adding: "+entry);
        this.stackArr[++top] = entry;
    }
    public T pop() throws Exception {
        if(this.isStackEmpty()){
            throw new Exception("Stack is empty,so you can not remove element.");
        }
        T entry = this.stackArr[top--];
        System.out.println("Removed entry: "+entry);
        return entry;
    }
    @SuppressWarnings("unchecked")
	private void increaseStackCapacity(){
        
        T[] newStack = (T[]) new Object[this.stackSize*2];
        for(int i=0;i<stackSize;i++){
            newStack[i] = this.stackArr[i];
        }
        this.stackArr = newStack;
        this.stackSize = this.stackSize*2;
    }
    public boolean isStackEmpty() {
        return (top == -1);
    }
    public boolean isStackFull() {
        return (top == stackSize - 1);
    }
    
    public T peek() {
        return stackArr[top];
    }
}
