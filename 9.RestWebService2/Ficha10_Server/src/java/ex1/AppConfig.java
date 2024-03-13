//author: Daniel Santos
package ex1;

import ex1.Rest;
import java.util.HashSet;
import java.util.Set;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("resources")
public class AppConfig extends Application{
   private Set<Object> singletons = new HashSet<Object>();

   public AppConfig() {
      singletons.add(new Rest());
   }

   @Override
   public Set<Object> getSingletons() {
      return singletons;
   }    
    
}
