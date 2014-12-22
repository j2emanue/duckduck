package jeffemanuel.org.modules;

import android.content.Context;
import android.media.AudioManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import jeffemanuel.org.duckduck.ListPageActivity;
import jeffemanuel.org.duckduck.MainActivity;
import jeffemanuel.org.duckduck.SummaryFragment;

/**
 * Created on 12/18/14.
 */

@Module(
        library = true,
        injects= {
                ListPageActivity.class,
                MainActivity.class,
                SummaryFragment.class
        }
) public class ActivityModule {
    Context context;

    public ActivityModule(Context context){
        this.context = context;
    }


    @Provides
    @Singleton
    AudioManager provideAudioManager(){return (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);}

    @Provides
    RecyclerView.LayoutManager provideLayoutManager() {
        return new LinearLayoutManager(context);
    }

}
