package com.example.retrofitexample;

import android.app.Application;
import android.content.Context;

import io.appflate.restmock.android.RESTMockTestRunner;

public class CustomTestRunner extends RESTMockTestRunner {
    @Override
    public Application newApplication(ClassLoader cl,
                                      String className,
                                      Context context) throws InstantiationException, IllegalAccessException, ClassNotFoundException {

        //I'm changing the application class only for test purposes. there I'll instantiate AppModule with RESTMock's url.
        String testApplicationClassName = FakeGetUrl.class.getCanonicalName();
        return super.newApplication(cl, testApplicationClassName, context);
    }
}
