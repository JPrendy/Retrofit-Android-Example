package com.example.retrofitexample;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.appflate.restmock.RESTMockServer;
import io.appflate.restmock.utils.RequestMatchers;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */


@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class,     true,
            false);

    @Before
    public void setup(){
        RESTMockServer.reset();
    }


    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertEquals("com.example.retrofitexample", appContext.getPackageName());
    }


    @Test
    public void testGoodAnswer() throws Exception {
        RESTMockServer.whenGET(RequestMatchers.pathContains("posts")).thenReturnFile(200,
                "users/test.json");
        //launches activity with default intent
        activityRule.launchActivity(null);
        Thread.sleep(10000);
    }
}
