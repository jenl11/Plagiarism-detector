/**
 * A Binary Search Tree implementation of the interface WordMap.
 *
 * @author Marcel Turcotte (marcel.turcotte@uottawa.ca)
 */

public class TreeWordMap implements WordMap {

    private static class Elem {

        private String key;
        private int count;
        private Elem left, right;

        private Elem(String key) {
            this.key = key;
            count = 0;
        }

    }

    private Elem root;
    private int size;

    /**
     * Returns true if and only if this WordMap contains the specified
     * word.
     *
     * @param word the specified word
     * @return true if and only if this WordMap contains the specified word
     * @throws NullPointerException if the value of the parameter is null
     */

    public boolean contains(String key) {
        boolean found = false;
        Elem current = root;
        while (!found && current != null) {
            int test = key.compareTo(current.key);
            if (test == 0) {
                found = true;
            } else if (test < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return found;
    }

    /**
     * Increments by 1 the counter associated with the specified
     * word. If the specified word is absent from the data structure,
     * a new association is created.
     *
     * @param word the specified word
     * @throws NullPointerException if the value of the parameter is null
     */

    public void update(String key) {
        if(key == null){
          throw new NullPointerException();
        }

        if(root == null){
          root = new Elem(key);
          size++;
        }

        Elem current = root;
        Elem parent;
        while(current != null){
            parent = current;
            int test = key.compareTo(current.key);
            if(test == 0) {
              current.count++;
              return;
            } else if(test < 0) {
                current = current.left;
                if(current == null){
                  parent.left = new Elem(key);
                  current = parent;
                  size++;
                }
            } else {
                current = current.right;
                if(current == null){
                  parent.right = new Elem(key);
                  current = parent;
                  size++;
                }
            }
        }

    }

    /**
     * Returns the count associated with the specified word or 0 if
     * the word is absent.
     *
     * @param word the specified word
     * @return the count associated with the specified word or 0 if absent
     * @throws NullPointerException if the value of the parameter is null
     */

    public int get(String key) {
        boolean found = false;
        Elem current = root;
        while (current != null) {
            int test = key.compareTo(current.key);
            if (test == 0) {
                return current.count;
            } else if (test < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return 0;
    }

    /**
     * Returns the logical size of this WordMap. That is the number of
     * associations currently stored in it.
     *
     * @return the logical size of this WordMap
     */

    public int size() {
        return size;
    }

    /**
     * Returns all the keys (words) of this WordMap using their
     * natural order.
     *
     * @return all the keys (words)
     */

    public String[] keys() {
        String[] list = new String[size];
        LinkedList<String> result = new LinkedList<String>();

        if(root == null){
          return list;
        }
        
        keys(root, result);
        for(int i = 0; i < size; i++){
          list[i] = result.get(i);
        }

        return list;
    }

    private void keys(Elem a, LinkedList<String> list){
      if(a.left != null){
        keys(a.left, list);
      }
      list.addLast(a.key);
      if(a.right != null){
        keys(a.right, list);
      }
    }

    /**
     * Returns all the counts associated with keys in this
     * WordMap. The counts are in the same order as that of the keys
     * returned by the method keys().
     *
     * @return all the counts
     */

    public Integer[] counts() {
      Integer[] counts = new Integer[size];
      LinkedList<Integer> result = new LinkedList<Integer>();

      list(root, result);
      for(int i = 0; i < size; i++){
        counts[i] = result.get(i);
      }

      return counts;
    }

    private void list(Elem a, LinkedList<Integer> list){
      if(a.left != null){
        list(a.left, list);
      }
      list.addLast(a.count);
      if(a.right != null){
        list(a.right, list);
      }
    }

}
