package comp5216.sydney.edu.au.group5.lazygod.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {UserDF.class}, version = 1, exportSchema = false)
public abstract class UserDB extends RoomDatabase {

    private static final String DATABASE_NAME = "user_db";
    private static UserDB DBINSTANCE;

    public abstract UserDao userDao();

    public static UserDB getDatabase(Context context) {
        if (DBINSTANCE == null) {
            synchronized (UserDB.class) {
                DBINSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        UserDB.class, DATABASE_NAME).build();
            }
        }
        return DBINSTANCE;
    }

    public static void destroyInstance() {
        DBINSTANCE = null;
    }
}