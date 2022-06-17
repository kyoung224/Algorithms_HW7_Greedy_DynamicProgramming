import java.util.*;

import javax.swing.text.DefaultStyledDocument.ElementSpec;

public class HW7 {

  public static void main(String[] args) {
		// Q1
    System.out.println(bestValue(7, new int[] {}, new int[] {})); // 0
    System.out.println(bestValue(7, new int[] {4}, new int[] {1})); // 4
    System.out.println(bestValue(7, new int[] {4, 10, 2, 4}, new int[] {3, 1, 5, 2})); // 24
    System.out.println(bestValue(7, new int[] {4, 10, 2, 4}, new int[] {3, 3, 5, 2})); // 25
    System.out.println(bestValue(7, new int[] {4, 10, 2, 4}, new int[] {3, 5, 5, 2})); // 35

    // Q2
      System.out.println(bestValueForFused(4, new int[] {}, new int[] {})); // 0
      System.out.println(bestValueForFused(4, new int[] {4}, new int[] {1})); // 4
      System.out.println(bestValueForFused(4, new int[] {4, 10, 2}, new int[] {3, 1, 5})); // 12
      System.out.println(bestValueForFused(4, new int[] {4, 2, 2}, new int[] {3, 2, 5})); // 14
      System.out.println(bestValueForFused(6, new int[] {4, 2, 1}, new int[] {3, 3, 5})); // 18
      System.out.println(bestValueForFused(6, new int[] {4, 2, 1}, new int[] {3, 2, 9})); // 21 (3*4+9*1)

    // Q3 (Optional)
    /*
    System.out.println(Arrays.toString(bestPicksForFused(4, new int[] {}, new int[] {}))); // []
    System.out.println(Arrays.toString(bestPicksForFused(4, new int[] {4}, new int[] {1}))); // [true]
    System.out.println(Arrays.toString(bestPicksForFused(4, new int[] {4, 10, 2}, new int[] {3, 1, 5}))); // [true, false, false]
    System.out.println(Arrays.toString(bestPicksForFused(6, new int[] {4, 2, 2}, new int[] {3, 2, 5}))); // [false, true, true]
    System.out.println(Arrays.toString(bestPicksForFused(6, new int[] {4, 2, 1}, new int[] {3, 3, 5}))); // [true, true, false]
    System.out.println(Arrays.toString(bestPicksForFused(6, new int[] {4, 2, 1}, new int[] {3, 2, 9}))); // [true, false, true]
    */
  }

  public static int bestValue(int W, int[] counts, int values[]) {
    if (counts.length <= 0){
      return 0;
    }
    int totalValue = 0;
    MetalBar[] bars = new MetalBar[counts.length];

    for (int i = 0; i < bars.length; i++){
      bars[i] = new MetalBar(values[i], counts[i]);
    }

    Arrays.sort(bars);
    int index = 0;
    while (W > 0 && index < bars.length){
      int barCount = bars[index].getCount();
      if (barCount <= W){
        W = W - barCount;
        totalValue = totalValue + (bars[index].getValue() * barCount);
        index++;
      } else{
        totalValue = totalValue + (bars[index].getValue() * W);
        W = W - W;
        index++;
      }
    }
    return totalValue;
  }

  public static int bestValueForFused2(int W, int[] counts, int values[]) {
    int[][] table = new int[W + 1][counts.length + 1];
    int result = 0;

    for (int i = 1; i < counts.length + 1; i++){
      int metalIndex = i - 1;
      for (int j = 0; j < W + 1; j++){
        int WIndex = j;
        if (WIndex >= counts[metalIndex] && WIndex - counts[metalIndex] >= 0){
          WIndex = WIndex - counts[metalIndex];
          table[j][i] = counts[metalIndex] * values[metalIndex];
          if (WIndex > 0 && metalIndex + 1 < counts.length){
            int higherVal = 0;
            for (int k = metalIndex + 1; k < counts.length; k++){
              if (counts[k] <= WIndex){
                if (counts[k] * values[k] > higherVal){
                  higherVal = k;
                }
              }
            }
            table[j][i] = Math.max(table[j][i], counts[metalIndex] * values[metalIndex] + counts[higherVal] * values[higherVal]);
            WIndex = WIndex - counts[higherVal];
          }
        }
      }
    }
    /*
    for (int[] i : table){
      System.out.println(Arrays.toString(i));
    }
    */
    for (int i = 0; i <= counts.length; i++){
      if (table[W][i] > result){
        result = table[W][i];
      }
    }
    return result;
  }
	public static int bestValueForFused(int W, int[] counts, int[] values) {
		if (counts.length == 0){
      return 0;
    }

    int[][] table = new int[W + 1][counts.length + 1];

    for (int i = counts.length - 1; i >= 0; i--){
      int metalIndex = i;
      int count = counts[metalIndex];
      int value = values[metalIndex];

      for (int w = 0; w <= W; w++){
        if (w < count){
          table[w][metalIndex] = 0;
        } else{
          int newW = Math.max(0, w - count);
          int take = count * value + table[newW][metalIndex + 1];
          int dontTake = table[w][metalIndex + 1];

          if (take > dontTake){
            table[w][metalIndex] = take;
          } else{
            table[w][metalIndex] = dontTake;
          }
        }
      }
    }
    return table[W][0];
	}
/* recursive way
  public static int bestValueForFused(int W, int[] counts, int values[]) {
    if (counts.length <= 0){
      return 0;
    }
    return bestValueForFused(W, counts, values, 0);
  } 

  public static int bestValueForFused(int W, int[] counts, int values[], int metalIndex){
    int result = 0;
    if (W <= 0){
      return 0; 
    } else if (metalIndex >= counts.length){
      return 0;
    } else{
      int count = counts[metalIndex];
      int value = values[metalIndex];
      int take = value * count + bestValueForFused(W - count, counts, values, metalIndex + 1);
      int dontTake = bestValueForFused(W, counts, values, metalIndex + 1);
      //do something with take and dontTake 
      if (take > dontTake){
        result = take;
      } else{
        result = dontTake;
      }
    }
    return result;
  }
*/

  public static int[] bestPicksForFused(int W, int[] counts, int values[]) {
    return null;
  }
}
