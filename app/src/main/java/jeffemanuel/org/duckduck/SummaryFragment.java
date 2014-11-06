package jeffemanuel.org.duckduck;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created  on 10/31/14.
 */


/**
 * Retrieves Json response from duckduck go authority and displays
 * recyclerView on parsed response while playing a sound.
 */
public class SummaryFragment extends Fragment implements View.OnClickListener {

    private final String TAG = this.getClass().getSimpleName();
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerListAdapter mAdapter;
    private RecyclerView mRecyclerView;

    public SummaryFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_summary, container, false);

        Button goBtn = (Button) rootView.findViewById(R.id.btn_go);
        goBtn.setOnClickListener(this);

        mRecyclerView = (RecyclerView) rootView
                .findViewById(R.id.my_recycler_view);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());
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


    private void parseData(JSONObject jsonObj) {

        if (jsonObj == null) {
            ((ListPageActivity) getActivity()).showToast(getString(R.string.no_results));
            return;
        }

        JSONArray arrayTopics = null;
        String definition = null;
        try {
            arrayTopics = jsonObj.getJSONArray(Consts.RELATED_TOPICS);
            definition = jsonObj.getString(Consts.DEFINITION);

            if (!TextUtils.isEmpty(definition))
                setDefinitionLayout(definition);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        List<SearchItem> searchItems = new ArrayList<SearchItem>();

        for (int i = 0; i != arrayTopics.length(); i++) {

            try {

                //grab next topic
                JSONObject topic = arrayTopics.getJSONObject(i);

                if (topic.has(Consts.TEXT)) {
                    SearchItem item = new SearchItem();
                    item.setDefinition(definition);

                    //get the 'text' field
                    item.setHeadline(topic.getString(Consts.TEXT));

                    //get the image
                    JSONObject icon = topic.getJSONObject(Consts.ICON);
                    if (icon.has(Consts.URL))
                        item.setImageURL(icon.getString(Consts.URL));

                    searchItems.add(item);
                }
            } catch (JSONException e) {
                Log.d(TAG, e.toString());
                e.printStackTrace();
            }

        }


        mAdapter = new RecyclerListAdapter(searchItems);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void setDefinitionLayout(String definition) {
        TextView tv_def = (TextView) getView().findViewById(R.id.tv_definition);
        getView().findViewById(R.id.card_view).setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View view) {
        EditText query = (EditText) getView().findViewById(R.id.et_query);

        if (!TextUtils.isEmpty(query.getText())) {
            String URL = BuildURLFromUserQuery(query.getText().toString());

            retrieveSearchResults(URL);


        } else
            ((ListPageActivity) getActivity()).showToast(getString(R.string.invalid));

    }


    /**
     * @param query users query from search entry
     * @return sample of return would be a string such as 'http://api.duckduckgo.com/?q=facebook&format=json&pretty=1'
     */
    private String BuildURLFromUserQuery(String query) {

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
}
