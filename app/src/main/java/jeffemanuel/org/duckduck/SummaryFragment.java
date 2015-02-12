package jeffemanuel.org.duckduck;

import android.app.Activity;
import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
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
import butterknife.OnClick;
import jeffemanuel.org.Mock.JsonMock;
import jeffemanuel.org.POJOs.DuckDataModel;
import jeffemanuel.org.POJOs.Icon;
import jeffemanuel.org.POJOs.RelatedTopic;
import jeffemanuel.org.common.BaseActivity;
import jeffemanuel.org.common.BaseFragment;
import jeffemanuel.org.common.interfaces.Listener;
import jeffemanuel.org.modules.SummaryFragmentModule;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Retrieves Json response from duckduck go authority and displays
 * recyclerView on parsed response while playing a sound.
 */
public class SummaryFragment extends BaseFragment {

    private final String TAG = this.getClass().getSimpleName();

    private RecyclerListAdapter mAdapter;

    private Listener mListener;

    //inject a layoutManager
    @Inject
    RecyclerView.LayoutManager mLayoutManager;

    @InjectView(R.id.my_recycler_view)
    RecyclerView mRecyclerView;
    @InjectView(R.id.et_query)
    EditText et_query;
    @InjectView(R.id.btn_go)
    Button goBtn;

    public SummaryFragment() {
        //later on we can pass in extras if we need it
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.tag(TAG);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (!(activity instanceof ListPageActivity))
            throw new ClassCastException("Activity must be instance of ListPageActivity");
        else {
            mListener = (Listener) activity;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_summary, container, false);
        //for view injection
        ButterKnife.inject(this, rootView);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
        return rootView;
    }

    /**
     * Retrieves json object using http GET asynchronously. Parses the json response and plays a sound
     * on response success.
     *
     * @param url - full duckduckgo url with query string
     */
    private void retrieveSearchResults(String url) {

        Timber.d("Retrieving search results from: %s", url);
        final ProgressDialog pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage(getString(R.string.loading_message));
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
                Timber.d("Error: %s", error.getMessage());
                // hide the progress dialog
                pDialog.hide();
                mListener.showToast("Error: " + error.getMessage());
            }
        });

        // Adding request to request queue
        MainApplication.getInstance().addToRequestQueue(jsonObjReq, Consts.TAG_JSON);
    }


    private void parseData(JSONObject jsonResponse) {
        Timber.d("parsing json response: %s", jsonResponse);

        if (getActivity() != null && jsonResponse == null) {
            mListener.showToast(getString(R.string.no_results));
            return;
        }
        DuckDataModel dataModel;
        List<RelatedTopic> arrayTopics;
        String definition = null;
        try {
            GsonBuilder gsonb = new GsonBuilder();
            Gson gson = gsonb.create();

            dataModel = gson.fromJson(jsonResponse.toString(), DuckDataModel.class);
            arrayTopics = dataModel.getRelatedTopics();

        } catch (Exception e) {
            //maybe also print a toast
            e.printStackTrace();
            return;
        }

        List<SearchItem> searchItems = new ArrayList<SearchItem>();
        //parse the search item data into a searchItem object and send to adapter
        if (dataModel.getRelatedTopics().isEmpty() && getActivity() != null) {
            ((MainActivity) getActivity()).showToast(getString(R.string.empty_results_warning));
            return;
        }

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

    @OnClick({R.id.btn_go})
    public void onClick(View view) {
        handleGoBtnClicked();
    }

    private void handleGoBtnClicked() {
        if ( ! TextUtils.isEmpty(et_query.getText())) {
            String URL = BuildURLFromUserQuery(et_query.getText().toString());
            if (!((BaseActivity) getActivity()).isNetworkAvailable()) {
                //if no network fake the call for demo/testing
                fakeApiCall(1).subscribe(new Subscriber<JSONObject>() {
                    @Override
                    public void onCompleted() {
                        Toast.makeText(getActivity(), "mocking json", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        //todo handle errors
                    }

                    @Override
                    public void onNext(JSONObject jsonMock) {
                        parseData(jsonMock);
                    }
                });

            } else
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

    /**
     *
     * @param delay - seconds to fake delay
     * @return an observable fake api call. Subscribers can call onNext to get the
     * faked json response
     */
    public static Observable<JSONObject> fakeApiCall(final long delay) {
        return Observable.create(new Observable.OnSubscribe<JSONObject>() {
            @Override
            public void call(Subscriber<? super JSONObject> subscriber) {
                // simulate I/O latency
                SystemClock.sleep(delay);
                final JsonMock fakeJson = new JsonMock();
                subscriber.onNext(fakeJson);
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
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
