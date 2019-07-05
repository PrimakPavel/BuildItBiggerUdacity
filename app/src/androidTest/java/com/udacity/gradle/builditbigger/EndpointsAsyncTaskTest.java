package com.udacity.gradle.builditbigger;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class EndpointsAsyncTaskTest implements EndpointsAsyncTask.EndpointsAsyncTaskListener {

    @Test
    public void testAsyncAction() {
        EndpointsAsyncTask asyncTask = new EndpointsAsyncTask();
        asyncTask.setListener(this);
        asyncTask.execute();
    }

    @Override
    public void showJoke(String joke) {
        assertNotNull(joke);
        assertFalse(joke, joke.isEmpty());
    }
}
