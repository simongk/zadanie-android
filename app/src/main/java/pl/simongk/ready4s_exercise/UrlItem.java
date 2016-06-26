package pl.simongk.ready4s_exercise;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by simongk on 24.06.16.
 */
@DatabaseTable(tableName = "urls")
public class UrlItem implements Serializable {


    @DatabaseField(columnName = "short_url")
    public String shortUrl;
    @DatabaseField(columnName = "long_url")
    public String longUrl;
    @DatabaseField(generatedId = true, columnName = "url_id")
    private int id;

    public UrlItem() {}

    public UrlItem(String shortUrl, String longUrl) {
        this.shortUrl = shortUrl;
        this.longUrl = longUrl;
    }


}