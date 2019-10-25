package comp5216.sydney.edu.au.group5.lazygod.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user")
    List<UserDF> listAll();

    @Insert
    void insert(UserDF userDF);

    @Update
    public void update(UserDF userDF);


    @Delete
    public void delete(UserDF userDF);
}