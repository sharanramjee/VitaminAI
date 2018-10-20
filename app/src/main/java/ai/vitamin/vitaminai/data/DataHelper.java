package ai.vitamin.vitaminai.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import ai.vitamin.vitaminai.data.DataContract.Food;
import ai.vitamin.vitaminai.data.DataContract.Charts;

public class DataHelper extends SQLiteOpenHelper {

    public static final String TAG = DataHelper.class.getSimpleName();

    private static final int VERSION = 1;

    public DataHelper(Context context) {
        super(context, TAG, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        final String USER_CREATE_TABLE = "CREATE TABLE " + DataContract.Food.TABLE_NAME + " (" +
                Food._ID + " INTEGER PRIMARY KEY, " +
                Food.COLUMN_TIME_ADDED + " LONG NOT NULL, " +
                Food.COLUMN_FOOD_NAME + " TEXT NOT NULL, " +
                Food.COLUMN_CONSUMED + " REAL NOT NULL, " +
                Food.COLUMN_CALORIES + " REAL NOT NULL);";
        db.execSQL(USER_CREATE_TABLE);

        final String TODAY_MENU_CREATE_TABLE = "CREATE TABLE " + Charts.TABLE_NAME + " (" +
                Charts._ID + " INTEGER PRIMARY KEY, " +
                Charts.COLUMN_MONTH + " INTEGER NOT NULL, " +
                Charts.COLUMN_YEAR + " INTEGER NOT NULL, " +
                Charts.COLUMN_COUNT + " INTEGER NOT NULL, " +
                Charts.COLUMN_WEIGHT + " REAL NOT NULL, "+
                Charts.COLUMN_HEIGHT + " REAL NOT NULL, "+
                Charts.COLUMN_CALORIES + " REAL NOT NULL);";
        db.execSQL(TODAY_MENU_CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Food.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Charts.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
