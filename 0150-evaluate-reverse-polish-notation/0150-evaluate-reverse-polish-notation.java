class Solution {
    public int evalRPN(String[] tokens) {
        int a,b;
		Stack<Integer> state = new Stack<Integer>();
		for (String ch : tokens) {
			if(ch.equals("+"))
				state.add(state.pop()+state.pop());
			else if(ch.equals("*"))
				state.add(state.pop() * state.pop());
			else if(ch.equals("/")) {
				b = state.pop();
				a = state.pop();
				state.add(a / b);
			}
			else if(ch.equals("-")) {
				b = state.pop();
				a = state.pop();
				state.add(a - b);
			}
			else 
				state.add(Integer.parseInt(ch));
		}	
		return state.pop();
    }
}