package learn;

import java.util.HashMap;

public class Solution {
  public static void main(String[] arguments) {
      final Server x = new MyServer();
      final HashMap<String, Object> user = new HashMap<String, Object>();
      try {
          user.put("Name", "Navaneeth");
          user.put("Age", 22);
          x.POST("users", user);

          Object output = x.GET("users");

          System.out.println(output);

          final HashMap<String, Object> patchuser = new HashMap<String, Object>();
          patchuser.put("Age", 23);
          x.PATCH("users", patchuser);

          output = x.GET("users");
          System.out.println(output);

          final HashMap<String, Object> putuser = new HashMap<String, Object>();
          patchuser.put("Age", 24);
          x.PUT("users", patchuser);

          output = x.GET("users");
          System.out.println(output);

          x.DELETE("users");
      } catch (HttpError e) {
          throw new RuntimeException(e);
      }
  }
}

