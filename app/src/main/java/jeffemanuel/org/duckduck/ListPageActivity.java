package jeffemanuel.org.duckduck;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import jeffemanuel.org.base.BaseActivity;


public abstract class ListPageActivity extends BaseActivity {

    private SoundPool mSoundPool;
    private int mSoundID;
    boolean plays = false, loaded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    public void loadBrandSound(){

        mSoundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);

        mSoundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {

            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                loaded = true;
            }
        });
        mSoundID = mSoundPool.load(this, R.raw.ducksound, 1);


    }


    public void playBrandSound() {

        AudioManager am = (AudioManager) getSystemService(AUDIO_SERVICE);
        int volume_level= am.getStreamVolume(AudioManager.STREAM_MUSIC);

        // Is the sound loaded already ?
        if (loaded && !plays) {
            mSoundPool.play(mSoundID, volume_level, volume_level, 1, 0, 1f);
            plays = true;

        }
    }

    public void stopBrandSound() {

        if (mSoundPool != null && plays) {
            mSoundPool.stop(mSoundID);
            mSoundPool.release();
            plays = false;
        }

    }

    /**
     *
     * @param msg text to show in toast
     */
    public void showToast(String msg){

        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_layout,
                (ViewGroup) findViewById(R.id.toast_layout_root));

        TextView text = (TextView) layout.findViewById(R.id.text);
        text.setText(msg);
        Toast toast = new Toast(getApplicationContext());
        //toast.setGravity(Gravity., 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }
}
