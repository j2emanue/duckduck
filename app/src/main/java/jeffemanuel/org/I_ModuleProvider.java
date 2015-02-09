package jeffemanuel.org;

/**
 * Created by bl630326 on 2/9/15.
 */

import java.util.List;

/**implementors must provide a dagger module for dependency injection*/
public interface I_ModuleProvider {
  public List<Object> getModules();

}
