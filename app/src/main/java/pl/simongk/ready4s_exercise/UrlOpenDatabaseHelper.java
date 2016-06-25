package pl.simongk.ready4s_exercise;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by simongk on 24.06.16.
 */

public class UrlOpenDatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "UrlsDB.db";
    private static final int DATABASE_VERSION = 1;
    private Dao<UrlItem,Integer> urlItemDao = null;

    public UrlOpenDatabaseHelper(Context context) {
        super(context, DATABASE_NAME,null,DATABASE_VERSION) ;
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource,UrlItem.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion){
        try {
            TableUtils.dropTable(connectionSource,UrlItem.class,true);
            onCreate(database,connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Dao<UrlItem, Integer> getDao() throws SQLException {
        if(null==urlItemDao){
                urlItemDao = getDao(UrlItem.class);
        }
        return urlItemDao;
    }
}
