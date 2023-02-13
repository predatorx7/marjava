import java.util.HashMap;

public class Solution {
  public static void main(String[] arguments) {
      final Server x = new MyServer();
      try {
          Object output = x.GET("hello");
          System.out.println(output);
      } catch (HttpError e) {
          throw new RuntimeException(e);
      }
  }
}

