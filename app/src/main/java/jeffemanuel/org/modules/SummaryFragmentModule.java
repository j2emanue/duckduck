package jeffemanuel.org.modules;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import dagger.Module;
import dagger.Provides;
import jeffemanuel.org.duckduck.SummaryFragment;


@Module(
        addsTo = ActivityModule.class,
        injects = {
                SummaryFragment.class
        })

public class SummaryFragmentModule {

    public SummaryFragmentModule(Context context) {
        this.context = context;
    }

    Context context;

    @Provides
    RecyclerView.LayoutManager provideLayoutManager() {
        return new LinearLayoutManager(context);
    }

}

