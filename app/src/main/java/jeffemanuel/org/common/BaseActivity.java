package jeffemanuel.org.common;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import java.util.List;

import dagger.ObjectGraph;
import jeffemanuel.org.I_ModuleProvider;
import jeffemanuel.org.duckduck.MainApplication;
import timber.log.Timber;


public abstract class BaseActivity extends Activity implements I_ModuleProvider {

    private ObjectGraph activityGraph;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //allow GC of activity graph
        activityGraph=null;
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.tag(this.getClass().getSimpleName());
        // Perform scoped injection so that when this call returns all dependencies will be available for use
        // but only during activities life..

        activityGraph =  ((MainApplication) getApplication()).createScopedGraph(getModules().toArray());
        activityGraph.inject(this);

    }
    protected boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    /**sub classes should provide dagger module here for scoped injection*/
    public abstract List<Object> getModules();
}
