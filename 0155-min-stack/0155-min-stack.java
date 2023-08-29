class MinStack {
    private static final int VALUE = 0;
    private static final int MIN = 1;
    private int top;
    private ArrayList<int[]> stackArray;
    private int min;

    public MinStack() {
        this.stackArray = new ArrayList<>();
        this.top = -1;
        this.min = Integer.MAX_VALUE;
    }

    public void push(int val) {
        min = Math.min(min, val);
        int[] arr = new int[]{val, min};
        stackArray.add(++top, arr);
    }

    public void pop() {
        stackArray.remove(top--);

        if(stackArray.isEmpty()) min = Integer.MAX_VALUE; //min 값 초기화
        else min = stackArray.get(top)[MIN]; //삭제 후 min값 업데이트
    }

    public int top() {
        return stackArray.get(top)[VALUE];
    }

    public int getMin() {
        return stackArray.get(top)[MIN];
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */