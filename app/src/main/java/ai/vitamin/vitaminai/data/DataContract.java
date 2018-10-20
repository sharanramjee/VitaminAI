package ai.vitamin.vitaminai.data;

import android.net.Uri;
import android.provider.BaseColumns;

public class DataContract {

    public static final String AUTHORITY = "ai.vitamin.vitaminai";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    //name of the table
    public static final String PATH_DATA = "userData";

    public static class Food implements BaseColumns {

        public static final String TABLE_NAME = "listFood";

        //content uri with the appended path
        public static final Uri EVENT_CONTENT_URI = CONTENT_URI.buildUpon().appendPath(PATH_DATA).appendPath(TABLE_NAME).build();

        //column name
        public static final String COLUMN_TIME_MONTH = "month";
        public static final String COLUMN_TIME_DAY = "day";
        public static final String COLUMN_TIME_YEAR = "year";
        public static final String COLUMN_TIME_HOUR = "hour";
        public static final String COLUMN_FOOD_NAME = "name";
        public static final String COLUMN_CONSUMED ="amountcosumned";
        public static final String COLUMN_CALORIES ="totalcalories";
        public static final String COLUMN_FAT = "totalfat";


        //the array for the database
        public static final String[] PROJECTION_ARRAY = {
                Food._ID,
                COLUMN_TIME_MONTH,
                COLUMN_TIME_DAY,
                COLUMN_TIME_YEAR,
                COLUMN_TIME_HOUR,
                COLUMN_FOOD_NAME,
                COLUMN_CONSUMED,
                COLUMN_CALORIES,
                COLUMN_FAT
        };

        //The int index
        public static final int COLUMN_ID_ARRAY_INDEX = 0;
        public static final int COLUMN_TIME_MONTH_INDEX = 1;
        public static final int COLUMN_TIME_DAY_INDEX = 2;
        public static final int COLUMN_TIME_YEAR_INDEX = 3;
        public static final int COLUMN_TIME_HOUR_INDEX = 4;
        public static final int COLUMN_FOOD_NAME_ARRAY_INDEX = 5;
        public static final int COLUMN_AMOUNT_CONSUMED_INDEX = 6;
        public static final int COLUMN_CALORIES_INDEX = 7;
        public static final int COLUMN_COLUMN_FAT = 8;

    }

    public static class Charts implements BaseColumns{

        public static final String TABLE_NAME = "charts";

        //content uri with the appended path
        public static final Uri EVENT_CONTENT_URI = CONTENT_URI.buildUpon().appendPath(PATH_DATA).appendPath(TABLE_NAME).build();

        //column name
        public static final String COLUMN_MONTH = "month";
        public static final String COLUMN_YEAR ="year";
        public static final String COLUMN_COUNT = "count";
        public static final String COLUMN_WEIGHT ="weight";
        public static final String COLUMN_HEIGHT = "height";
        public static final String COLUMN_CALORIES ="calories";

        //the array for the database
        public static final String[] PROJECTION_ARRAY = {
                Charts._ID,
                COLUMN_MONTH,
                COLUMN_YEAR,
                COLUMN_COUNT,
                COLUMN_WEIGHT,
                COLUMN_HEIGHT,
                COLUMN_CALORIES
        };

        //The int index
        public static final int COLUMN_ID_ARRAY_INDEX = 0;
        public static final int COLUMN_MONTH_INDEX = 1;
        public static final int COLUMN_YEAR_INDEX = 2;
        public static final int COLUMN_COUNT_INDEX = 3;
        public static final int COLUMN_WEIGNT_INDEX = 4;
        public static final int COLUMN_HEIGHT_INDEX = 5;
        public static final int COLUMN_CALORIES_INDEX = 6;

    }
}
