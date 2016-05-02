package kankan.km.com.manhupro.tools.dbtools;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by aspn300 on 16/2/28.
 */
public class DBHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "kanmanhua.db";
    private static final int DATABASE_VERSION = 3;

    public DBHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS T_MANHUA" +
                "(m_id VARCHAR PRIMARY KEY, " +
                "m_title text," +
                "m_icon text, " +
                "m_createTime text," +
                "m_fromdata text, " +
                "u_phoneno text, " +
                "m_type text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE T_MANHUA");

        onCreate(db);
    }

}
