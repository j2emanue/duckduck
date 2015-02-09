package jeffemanuel.org.duckduck;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import jeffemanuel.org.POJOs.DuckDataModel;
import jeffemanuel.org.POJOs.Icon;
import jeffemanuel.org.POJOs.RelatedTopic;
import jeffemanuel.org.common.BaseFragment;
import jeffemanuel.org.modules.SummaryFragmentModule;

/**
 * Retrieves Json response from duckduck go authority and displays
 * recyclerView on parsed response while playing a sound.
 */
public class SummaryFragment extends BaseFragment implements View.OnClickListener {

    private final String TAG = this.getClass().getSimpleName();

    private RecyclerListAdapter mAdapter;
    @Inject
    RecyclerView.LayoutManager mLayoutManager;

    @InjectView(R.id.my_recycler_view)
    RecyclerView mRecyclerView;
    @InjectView(R.id.et_query)
    EditText et_query;
    @InjectView(R.id.btn_go)
    Button goBtn;

    // private BehaviorSubject<String>jsonParsedSubject= new BehaviorSubject.OnSubscribeFunc<>();
    public SummaryFragment() {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_summary, container, false);
        //for view injection
        ButterKnife.inject(this, rootView);

        goBtn.setOnClickListener(this);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setLayoutManager(mLayoutManager);
        return rootView;
    }

    /**
     * Retrieves json object using GET asynchronously. Parses the json response and plays a sound
     * on response success.
     *
     * @param url - full duckduckgo url with query string
     */
    private void retrieveSearchResults(String url) {

        final ProgressDialog pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading...");
        pDialog.show();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        parseData(response);
                        playSound();
                        pDialog.hide();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                // hide the progress dialog
                pDialog.hide();
                if (getActivity() instanceof ListPageActivity)
                    ((ListPageActivity) getActivity()).showToast("Error: " + error.getMessage());
            }
        });

        // Adding request to request queue
        MainApplication.getInstance().addToRequestQueue(jsonObjReq, Consts.TAG_JSON);
    }


    private void parseData(JSONObject jsonResponse) {

        if (getActivity() != null && jsonResponse == null) {
            ((ListPageActivity) getActivity()).showToast(getString(R.string.no_results));
            return;
        }
        DuckDataModel dataModel = null;
        List<RelatedTopic> arrayTopics = null;
        String definition = null;
        try {
            GsonBuilder gsonb = new GsonBuilder();
            Gson gson = gsonb.create();

            dataModel = gson.fromJson(jsonResponse.toString(), DuckDataModel.class);
            arrayTopics = dataModel.getRelatedTopics();
            definition = dataModel.getDefinition();

            //if (!TextUtils.isEmpty(dataModel.getDefinition()))
            //   setDefinitionLayout(definition);
        } catch (Exception e) {
            //maybe also print a toast
            e.printStackTrace();
            return;
        }

        List<SearchItem> searchItems = new ArrayList<SearchItem>();
        //parse the search item data into a searchItem object and send to adapter
        for (int i = 0; i != dataModel.getRelatedTopics().size() - 1; i++) {

            try {
                //grab next topic
                RelatedTopic topic = arrayTopics.get(i);

                SearchItem item = new SearchItem();
                item.setDefinition(definition);

                //get the 'text' field
                item.setHeadline(topic.getText());

                //get the image
                Icon icon = topic.getIcon();

                item.setImageURL(icon.getURL());

                searchItems.add(item);
            } catch (Exception e) {
                Log.d(TAG, e.toString());
                e.printStackTrace();
            }
        }
        mAdapter = new RecyclerListAdapter(searchItems);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View view) {
        if (!TextUtils.isEmpty(et_query.getText())) {
            String URL = BuildURLFromUserQuery(et_query.getText().toString());
            retrieveSearchResults(URL);
        } else
            ((ListPageActivity) getActivity()).showToast(getString(R.string.invalid));
    }


    /**
     * @param query users query from search entry
     * @return sample of return would be a string such as 'http://api.duckduckgo.com/?q=facebook&format=json&pretty=1'
     */
    protected String BuildURLFromUserQuery(String query) {

        Uri.Builder builder = new Uri.Builder();
        builder.scheme(Consts.SCHEME)
                .authority(Consts.AUTHORITY)
                .appendQueryParameter("q", query)
                .appendQueryParameter("format", "json")
                .appendQueryParameter("pretty", "1");

        return builder.build().toString();
    }

    private void playSound() {
        if (getActivity() != null)
            ((ListPageActivity) getActivity()).playBrandSound();

    }

    @Override
    public List<Object> getModules() {
        return Arrays.<Object>asList(new SummaryFragmentModule(getActivity()));
    }
}
