package pl.simongk.application_testexercise;

import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by simongk on 24.06.16.
 */

public class DatabaseConfigUtil extends OrmLiteConfigUtil {

    public static void main(String[] args) throws SQLException,IOException{
        writeConfigFile("ormlite_config.txt");
    }
}
