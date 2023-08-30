class MinStack {
    
    // private int min=Integer.MAX_VALUE;
    private Node lst_point=null;
    public MinStack() {
        
    }
    
    public void push(int val) {
        int min=Math.min(val,lst_point==null? Integer.MAX_VALUE:lst_point.min);
        Node temp=new Node(min,val);
        if(lst_point==null)
        {
            lst_point=temp;
        }
        else{
            lst_point.next=temp;
            temp.prev=lst_point;
            lst_point=lst_point.next;
        }
    }
    
    public void pop() {
        Node temp=lst_point.prev;
        if(temp!=null)
        temp.next=null;
        
        lst_point.prev=null;
        lst_point=temp;
    }
    
    public int top() {
        return lst_point.val;
    }
    
    public int getMin() {
        return lst_point.min;
    }

    private class Node{
        int min;
        int val;
        Node next;
        Node prev;
        Node(int min,int val)
        {
            this.min=min;
            this.val=val;
            this.next=null;
            this.prev=null;
        }
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