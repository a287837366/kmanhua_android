package kankan.km.com.manhupro.tools.dbtools;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import kankan.km.com.manhupro.main.module.ManhuaModel;

/**
 * Created by aspn300 on 16/2/28.
 */
public class DBManager {

    private DBHelper helper;
    private SQLiteDatabase db;

    public DBManager(Context context) {
        helper = new DBHelper(context);
    }

    public void addFav(ManhuaModel model){

        db = helper.getWritableDatabase();

        String sql = "INSERT OR REPLACE INTO T_MANHUA VALUES(?, ?, ? ,?, ?, ? ,?)";
        db.execSQL(sql, new Object[]{model.getM_uid(),
                model.getM_title(),
                model.getM_icon(),
                model.getM_createTime(),
                model.getM_fromdata(),
                model.getU_phoneno(),
                model.getM_type()});


        db.close();
    }

    public void deleteById(String manhuaId){
        db = helper.getWritableDatabase();

        String sql = "DELETE FROM T_MANHUA where m_id = ?";
        db.execSQL(sql, new Object[]{manhuaId});

        db.close();

    }

    public int getManhuaSize(){
        db = helper.getReadableDatabase();

        String sql = "select count(*) from T_MANHUA";

        Cursor c = db.rawQuery(sql, new String[]{});

        int count = 0;

        if (c.moveToNext()){
            count = c.getInt(0);
        }

        c.close();
        db.close();

        return count;
    }

    public boolean isFav(String manhuaId){
        db = helper.getReadableDatabase();

        String sql = "SELECT * FROM T_MANHUA where m_id = ?";

        Log.d("SQL", sql);

        Cursor c = db.rawQuery(sql, new String[]{manhuaId});

        if (c.moveToNext()){

            c.close();
            db.close();

            return true;
        }

        c.close();
        db.close();

        return false;
    }

    public ArrayList<ManhuaModel> getHistoryManhua(){
        db = helper.getReadableDatabase();

        ArrayList<ManhuaModel> manhuaLists = new ArrayList<ManhuaModel>();

        String sql = "select * from t_manhua";

        Cursor c = db.rawQuery(sql, new String[]{});

        while (c.moveToNext()){

            ManhuaModel model = new ManhuaModel();
//
//            model.setM_uid(c.getString(0));
//            model.setM_name(c.getString(1));
//            model.setM_icon(c.getString(2));
//
//            Log.d(">>>>>>>>", model.getM_uid() + "     " + model.getM_name() + "    " + model.getM_icon() );

            manhuaLists.add(model);

        }

        c.close();
        db.close();


        return manhuaLists;
    }

}
