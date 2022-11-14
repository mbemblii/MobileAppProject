package tn.esprit.presto.dao;

import androidx.room.*;

import java.util.List;

import tn.esprit.presto.entities.User;

@Dao
public interface UserDao {

    @Insert
    void insertUser(User user);

    @Delete
    void deleteUser(User user);

    @Update
    void updateUser(User user);

    @Query("SELECT * FROM user_table")
    List<User> getAll();

    @Query("SELECT * FROM user_table WHERE username=:username" )
    User findByUsername(String username);

    @Query("SELECT * FROM user_table where username= :username and password= :password")
    User getUser(String username, String password);

    @Query("select * from user_table where id= :id")
    User getUserById(Long id);


}
