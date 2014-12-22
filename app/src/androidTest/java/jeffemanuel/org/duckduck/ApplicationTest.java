package jeffemanuel.org.duckduck;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ActivityInstrumentationTestCase2<MainActivity> {

    MainActivity mActivity;
    private Button goBtn;
    private EditText et_query;
    private RecyclerListAdapter mAdapter;

    public ApplicationTest() {
        super(MainActivity.class);

    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        setActivityInitialTouchMode(false);

        mActivity = getActivity();

        goBtn=(Button)mActivity.findViewById(
                R.id.btn_go);
        et_query = (EditText)mActivity.findViewById(R.id.et_query);
    }
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    public void testPreconditions() {
        assertTrue(mActivity.isNetworkAvailable());
        isLayoutValid();
    }

    public void isLayoutValid(){
        assertNotNull(goBtn);
        //assertNotNull(et_query);
        // assertTrue(mActivity.isNetworkAvailable());
        //assertNotNull(et_query.getText());

    }

    //todo resolve testing issues
    public void testUrlValid(){

       // SummaryFragment fragment = (SummaryFragment)getActivity().getFragmentManager().findFragmentByTag("SummaryFragment");
        getInstrumentation().waitForIdleSync();
        //String url =  fragment.BuildURLFromUserQuery("health".toString());

        //assertNotNull(fragment);
        //assertNotNull(url);
        //assertTrue(url!="");

//MainApplication mockFragment = Mockito.mock(MainApplication.class);
//Mockito.spy(Consts.class);
    }

}