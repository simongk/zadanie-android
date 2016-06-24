package pl.simongk.application_testexercise;


import android.app.Dialog;
import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.query.In;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.R.id.list;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment2 extends ListFragment {

    private UrlOpenDatabaseHelper urlOpenDatabaseHelper;
    private ListView listView;
    private Dao<UrlItem,Integer> urlItems;
    private List<UrlItem> urlItemList;

    public Fragment2() {
        // Required empty public constructor
    }

    @Override
    public String toString() {
        return String.valueOf(urlItemList);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        TextView tvshortUrl = (TextView) v.findViewById(R.id.shortUrl);
        String shortUrltext = tvshortUrl.getText().toString();
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(shortUrltext));
        getActivity().startActivity(i);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2, container, false);
        urlOpenDatabaseHelper = OpenHelperManager
                .getHelper(getActivity(),UrlOpenDatabaseHelper.class);
        try {
            urlItems = urlOpenDatabaseHelper.getDao();
            urlItemList=urlItems.queryForAll();
            listView = (ListView) view.findViewById(android.R.id.list);
            listView.setClickable(true);
            UrlAdapter ua = new UrlAdapter(getActivity(),R.layout.list_item,urlItemList,urlItems);
            setListAdapter(ua);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return view;

    }
}
