package tn.esprit.presto;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import tn.esprit.presto.dao.UserDao;
import tn.esprit.presto.database.AppDataBase;
import tn.esprit.presto.entities.User;

public class HomeActivity extends AppCompatActivity {

    AppDataBase database ;
    UserDao userDao;
    int type_menu;
    String tyepUser;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        this.sp = getSharedPreferences("userSp",MODE_PRIVATE);

        setTitle("Hi,  "+sp.getString("username",""));

        database = AppDataBase.getAppDatabase(this);
        userDao = database.userDao();
        User user = userDao.findByUsername(sp.getString("username",""));
        // check if user is a simple client or restaurent
        if (user.getRole().equals("Client")){
            this.type_menu = R.menu.client_menu;
            this.tyepUser = "client";
        }
        else {
            this.type_menu = R.menu.restaurent_menu;
            this.tyepUser = "restaurent";
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(this.type_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Client options
        if (this.tyepUser.equals("client")){
            switch (item.getItemId()){
                case R.id.panier1:
                    Toast.makeText(this,"panier !!!",Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.ordersItemClient:
                    Toast.makeText(this,"client orders !!!",Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.logoutItem:
                    sp.edit().clear().apply();
                    finish();
                    return true;
            }
        }
        else {
            // restaurent options
            switch (item.getItemId()){
                case R.id.foodsItem:
                    Toast.makeText(this,"listes des plats !!!",Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.ordersItemRestau:
                    Toast.makeText(this,"les commandes des retau !!!",Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.logoutItem:
                    sp.edit().clear().apply();
                    finish();
                    return true;
            }

        }
        return super.onOptionsItemSelected(item);
    }
}