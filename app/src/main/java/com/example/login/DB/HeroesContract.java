package com.example.login.DB;

import android.provider.BaseColumns;

public class HeroesContract {
    private HeroesContract(){}
    public static class HeroesEntry implements BaseColumns {
        public static final String TABLE_NAME ="Heroes";
        public static final String ID = "id";
        public static final String COLUMN_NAME_TITLE = "Nombre";
        public static final String COLUMN_ROL_TITLE = "Rol";
        public static final String COLUMN_SUB_ROL_TITLE = "Subrol";
    }
}
