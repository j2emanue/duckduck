package jeffemanuel.org.duckduck;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import jeffemanuel.org.modules.MainActivityModule;


public class MainActivity extends ListPageActivity {

    @Inject
    SharedPreferences preferences;//not used yet

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new SummaryFragment(),"SummaryFragment")
                    .commit();
        }

        loadBrandSound();

        if (!isNetworkAvailable())
            showToast(getString(R.string.no_network));
    }

    @Override
    public List<Object> getModules() {
        return Arrays.<Object>asList(new MainActivityModule());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
