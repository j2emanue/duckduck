package jeffemanuel.org.modules;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.Vibrator;
import android.preference.PreferenceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module(
        library = true)

public class ActivityModule {

        Context context;

        public ActivityModule(Context context){
            this.context = context;
        }

    @Provides
    public Context provideContext(){
    return context;
}
        @Provides
        @Singleton
        AudioManager provideAudioManager(){return (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);}


    @Provides
    @Singleton
    Vibrator provideVibratorManager(){return (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);}

    @Provides
    SharedPreferences provideSharedPref(){

       return PreferenceManager
               .getDefaultSharedPreferences(context);
    }


    }
