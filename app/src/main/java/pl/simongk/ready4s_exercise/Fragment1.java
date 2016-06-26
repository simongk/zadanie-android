package pl.simongk.ready4s_exercise;


import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment1 extends Fragment {

    private EditText httpEditText;
    private Button addButton;
    private String longURL;
    private String shortURL;

    public Fragment1() {
        // Required empty public constructor
    }

    public String getLongURL() {
        return longURL;
    }

    public void setLongURL(String longURL) {
        this.longURL = longURL;
    }

    public String getShortURL() {
        return shortURL;
    }

    public void setShortURL(String shortURL) {
        this.shortURL = shortURL;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment1, container, false);
        httpEditText = (EditText) view.findViewById(R.id.httpEditText);
        addButton = (Button) view.findViewById(R.id.addButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String linkUrl = httpEditText.getText().toString();
                if (Patterns.WEB_URL.matcher(linkUrl).matches()) {
                    setLongURL(linkUrl);
                    new MyTask(getLongURL())
                            .execute();
                    Toast.makeText(getActivity(), "URL is ok! :)", Toast.LENGTH_SHORT).show();
                } else Toast.makeText(getActivity(), "Wrong URL :(", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    public UrlOpenDatabaseHelper getHelper() {
        UrlOpenDatabaseHelper urlOpenDatabaseHelper = OpenHelperManager
                .getHelper(getActivity(), UrlOpenDatabaseHelper.class);
        return urlOpenDatabaseHelper;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        OpenHelperManager.releaseHelper();
    }

    public void addingtoDatabase() {
        try {
            final Dao < UrlItem, Integer > urlItems = getHelper().getDao();
            UrlItem urlItem = new UrlItem(getShortURL(), getLongURL());
            urlItems.create(urlItem);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private class MyTask extends AsyncTask < Void, Void, String > {

        final String url1;
        public MyTask(String url1) {
            this.url1 = url1;
        }

        @Override
        protected String doInBackground(Void...params) {
            return UrlShortener.shortUrl(url1);
        }

        @Override
        protected void onPostExecute(String s) {
            setShortURL(s);
            addingtoDatabase();
        }
    }
}