package learn;

import com.google.gson.Gson;

import java.util.HashMap;

public class Solution {
   static Gson gson;
  public static void main(String[] arguments) {
      final Server x = new MyServer();
      try {
          final User user = new User(
                  "Navaneeth",
                  22
          );
          gson = new Gson();
          final String userJson = gson.toJson(user);
          System.out.println("User Json: " + userJson);
          final User receivedUser = gson.fromJson(userJson, User.class);
          System.out.println("Received User: " + receivedUser);
          System.out.println("is same: " + (user == receivedUser));
          HashMap<String,Object> userMap = new HashMap<String,Object>();
          userMap = (HashMap<String, Object>) gson.fromJson(userJson, userMap.getClass());

          x.POST("users", userMap);

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

