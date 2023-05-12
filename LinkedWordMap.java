/**
 * An implementation of the interface WordMap using linked elements.
 *
 * @author Marcel Turcotte (marcel.turcotte@uottawa.ca)
 */
import java.util.Arrays;
public class LinkedWordMap implements WordMap {

    private static class Elem {
      private String key;
      private int count;
      private Elem previous, next;

      private Elem(String key, Elem previous, Elem next){
        this.key = key;
        this.count = 0;
        this.previous = previous;
        this.next = next;
      }
    }

    private Elem head;
    private int size;

    public LinkedWordMap(){
      head = new Elem(null, null, null);
      head.previous = head;
      head.next = head;
      size = 0;
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
     * Returns true if and only if this WordMap contains the specified
     * word.
     *
     * @param key the specified word
     * @return true if and only if this WordMap contains the specified word
     * @throws NullPointerException if the value of the parameter is null
     */

    public boolean contains(String key) {
      if(key == null){
        throw new NullPointerException();
      }

      Elem current = head.next;

      while(current != head){
        if(current.key.equals(key)){
          return true;
        }
        current = current.next;
      }
      return false;
    }

    /**
     * Returns the count associated with the specified word or 0 if
     * the word is absent.
     *
     * @param key the specified word
     * @return the count associated with the specified word or 0 if absent
     * @throws NullPointerException if the value of the parameter is null
     */

    public int get(String key) {
      if(key == null){
        throw new NullPointerException();
      }
      Elem current = head.next;
      while(current != head){
        if(current.key.equals(key)){
          return current.count;
        }
        current = current.next;
      }
      return 0;
    }

    /**
     * Increments by 1 the counter associated with the specified
     * word. If the specified word is absent from the data structure,
     * a new association is created.
     *
     * @param key the specified word
     * @throws NullPointerException if the value of the parameter is null
     */

    public void update(String key) {
      if(key == null){
        throw new NullPointerException();
      }
      Elem current = head.next;
      if(current == head){
        current.next = new Elem(key, head, head);
        size++;
        current.next.count++;
        return;
      }
      while(current != head && (current.key.compareTo(key)) != 0){
        if(current.next == head){
          current.next = new Elem(key, current, head);
          size++;
          current.next.count++;
          return;
        }
        current = current.next;
      }
      current.count++;
    }

    /**
     * Returns all the keys (words) of this WordMap using their
     * natural order.
     *
     * @return all the keys (words)
     */

    public String[] keys() {
      String[] keys = new String[size];
      Elem current = head.next;
      for(int i = 0; i < size; i++) {
        keys[i] = current.key;
        current = current.next;
      }
      String temp;
      for (int i = 0; i < size; i++) {
        for (int j = i+1; j < size; j++) {
          if (keys[i].compareTo(keys[j]) > 0) {
              temp = keys[i];
              keys[i] = keys[j];
              keys[j] = temp;
          }
        }
      }
      return keys;
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
      String[] keys = keys();
      for(int i = 0; i < size; i++){
        counts[i] = get(keys[i]);
      }
      return counts;
    }


}
