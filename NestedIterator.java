//approach-we are using a stack of iterator<NestedInteger>, first hasNext() is called then next() is called. in hasNext we are updating the nextE with the element that is to be returned at that point. we do this by acessing the top of stack and updating the nextE with next element in order, then if the nextE is integer we return true for hasNext(), if not we put the list again in the stack and do the same till we find an integer.
//time complexity-O(1)for hasNext() and Amortized O(1) for next() method.
//space Complexity-O(d) which is the auxillary space used by our stack where dis the depth of nested lists.
/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return empty list if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class NestedIterator implements Iterator<Integer> {

    Stack<Iterator<NestedInteger>> st;
    NestedInteger nextE;
    public NestedIterator(List<NestedInteger> nestedList) {
        this.st=new Stack<>();
        st.push(nestedList.iterator());
    
    }

    @Override
    public Integer next() {
       return nextE.getInteger();
    }

    @Override
    public boolean hasNext() {
         while(!st.isEmpty()){
            if(st.peek().hasNext()){
                nextE=st.peek().next();
                if(nextE.isInteger())return true;
                else st.push(nextE.getList().iterator());
            }else {
                st.pop();
                nextE=null;
            }
         }
         return false;
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
