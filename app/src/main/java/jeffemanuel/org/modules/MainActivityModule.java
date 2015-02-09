package jeffemanuel.org.modules;

import android.content.Context;

import dagger.Module;
import jeffemanuel.org.duckduck.MainActivity;

/**
 * Created on 12/18/14.
 */

@Module(
        addsTo = ActivityModule.class,
        injects= {
                MainActivity.class,
        }
) public class MainActivityModule {
    Context context;

    public MainActivityModule(){
    }


}
