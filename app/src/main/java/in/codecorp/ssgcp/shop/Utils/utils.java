package in.codecorp.ssgcp.shop.Utils;

import android.net.Uri;

/**
 * Created by NavdeepAhuja on 2018-12-21.
 */

public class utils {
    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "cpdb.db";

    public static final String TAB_NAME = "CARTT";
    public static final String COL_ID = "id";
    public static final String COL_NAME = "name";
    public static final String COL_DESC = "description";
    public static final String COL_REGPRICE = "regular_price";
    public static final String COL_IMG = "src";
    public static final String COL_SALEPRICE = "sale_price";
    public static final String COL_rating = "rating";
    public static final String COL_QTY = "qty";
    public static final String COL_TOT = "total";

    public static final String CREATE_TAB_QUERY = "create table CARTT(" +
            "id integer primary key autoincrement," +
            "name text," +
            "description text," +
            "regular_price text," +
            "src text," +
            "sale_price text," +
            "rating text" +
            "qty text" +
            ")";

    public static final Uri USER_URI = Uri.parse("content://in.codecorp.shop.cp/"+TAB_NAME);

    public static final String KEY_USER = "keyUser";
}
