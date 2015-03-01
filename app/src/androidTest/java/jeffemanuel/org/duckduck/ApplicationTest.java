package jeffemanuel.org.duckduck;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ActivityInstrumentationTestCase2<MainActivity> {

    MainActivity mActivity;
    private Button goBtn;

    public ApplicationTest() {
        super(MainActivity.class);

    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        setActivityInitialTouchMode(false);

        mActivity = getActivity();

        goBtn = (Button) mActivity.findViewById(
                R.id.btn_go);

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
        assertNotNull((goBtn.getText()));
        //assertNotNull(et_query);
        // assertTrue(mActivity.isNetworkAvailable());
        //assertNotNull(et_query.getText());

    }

    //todo resolve testing issues
    public void testBuildURLFromUserQuery(){
        SummaryFragment frag = new SummaryFragment();
        String url=frag.BuildURLFromUserQuery("facebook");
        assertNotNull(url);
        //assertEquals("http://api.duckduckgo.com?q=facebook&format=json&pretty=1",url);
        url = frag.BuildURLFromUserQuery(null);
        assertNull(url);
    }

}