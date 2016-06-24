package pl.simongk.application_testexercise;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.j256.ormlite.dao.Dao;

import java.util.List;



/**
 * Created by simongk on 24.06.16.
 */
public class UrlAdapter extends ArrayAdapter<UrlItem> {

    private List<UrlItem> records;
    private Dao<UrlItem,Integer> urlDao;

    public UrlAdapter(Context context, int resource, List<UrlItem> objects,
                      Dao<UrlItem,Integer> urlDao) {
        super(context, resource, objects);
        this.records=objects;
        this.urlDao=urlDao;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final UrlItem urlItem = records.get(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,
                    parent,false);
        }

        TextView shortUrl = (TextView) convertView.findViewById(R.id.shortUrl);
        shortUrl.setText(urlItem.shortUrl);

        TextView longUrl = (TextView) convertView.findViewById(R.id.longUrl);
        longUrl.setText(urlItem.longUrl);

        return convertView;

    }
}
