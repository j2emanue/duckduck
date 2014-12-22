package jeffemanuel.org.duckduck;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.ButterKnife;
import jeffemanuel.org.base.BaseActivity;

public abstract class ListPageActivity extends BaseActivity {

    private SoundPool mSoundPool;
    private int mSoundID;
    boolean loaded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ButterKnife.inject(this);

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


@Inject AudioManager am;
    public void playBrandSound() {
        int volume_level= am.getStreamVolume(AudioManager.STREAM_MUSIC);
        // Is the sound loaded already ?
        if (loaded ) {
            mSoundPool.play(mSoundID, volume_level, volume_level, 1, 0, 1f);
        }
    }

    public void stopBrandSound(Object notUsed) {

        if (mSoundPool != null ) {
            mSoundPool.stop(mSoundID);
            mSoundPool.release();
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
