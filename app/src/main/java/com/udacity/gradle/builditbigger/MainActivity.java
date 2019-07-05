package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.pavelprymak.androidjokelibrary.JokeActivity;
import com.pavelprymak.jokelib.Joke;


public class MainActivity extends AppCompatActivity implements EndpointsAsyncTask.EndpointsAsyncTaskListener {

    private EndpointsAsyncTask mAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mAsyncTask != null)
            mAsyncTask.setListener(null);
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

    public void tellJoke(View view) {
        Joke joke = new Joke();
        Toast.makeText(this, joke.getJoke(), Toast.LENGTH_LONG).show();

        if (mAsyncTask != null && !(mAsyncTask.getStatus() == AsyncTask.Status.RUNNING)) {
            mAsyncTask.setListener(null);
            mAsyncTask.cancel(true);
        }
        mAsyncTask = new EndpointsAsyncTask();
        mAsyncTask.setListener(this);
        mAsyncTask.execute();
    }


    @Override
    public void showJoke(String joke) {
        Intent intent = new Intent(this, JokeActivity.class);
        intent.putExtra(JokeActivity.KEY_JOKE, joke);
        startActivity(intent);
    }
}
