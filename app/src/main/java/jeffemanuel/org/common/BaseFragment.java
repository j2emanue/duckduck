package jeffemanuel.org.common;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;

import dagger.ObjectGraph;
import jeffemanuel.org.I_ModuleProvider;
import jeffemanuel.org.duckduck.MainApplication;

public abstract class BaseFragment extends Fragment implements I_ModuleProvider{

    private ObjectGraph activityGraph;

    @Override
    public void onDestroy() {
        super.onDestroy();
        activityGraph=null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityGraph =  ((MainApplication) getActivity().getApplication()).createScopedGraph(getModules().toArray());
        activityGraph.inject(this);

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        }


}

