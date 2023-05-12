import java.util.HashSet;

public class Jaccard implements Similarity{

  public double score(WordMap a, WordMap b){
    if(a == null || b == null){
      throw new NullPointerException();
    }
    if(a.size() == 0 || b.size() == 0){
      throw new IllegalArgumentException();
    }

    HashSet<String> inter = new HashSet<String>();
    HashSet<String> uni = new HashSet<String>();
    String[] wordA = a.keys();
    String[] wordB = b.keys();
    boolean k = false;

    for(int i = 0; i < a.size(); i++){
      uni.add(wordA[i]);
      for(int j = 0; j < b.size(); j++){
        if(!k){
          uni.add(wordB[j]);
        }
        if(wordA[i] == wordB[j]){
          inter.add(wordA[i]);
        }
      }
      k = true;
    }
    return Double.valueOf(inter.size())/Double.valueOf(uni.size());
  }
}
