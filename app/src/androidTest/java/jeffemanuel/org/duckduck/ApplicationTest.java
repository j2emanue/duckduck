package jeffemanuel.org.duckduck;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;

import org.mockito.Mockito;

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
        //assertNotNull(et_query);
        // assertTrue(mActivity.isNetworkAvailable());
        //assertNotNull(et_query.getText());

    }

    //todo resolve testing issues
    public void testSomething(){



MainApplication mockApplication = Mockito.mock(MainApplication.class);

    }

}