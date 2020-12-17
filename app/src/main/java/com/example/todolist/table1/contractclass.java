package com.example.todolist.table1;

import android.provider.BaseColumns;

public final class contractclass {
    public static abstract class ListEntry implements BaseColumns {

        public static final String TABLE_NAME = "todo";
        public static final String COLUMN_ID = BaseColumns._ID;
        public static final String COLUMN_TASK = "TASK";
        public static final String COLUMN_TIME = "STYLE";

    }

}
