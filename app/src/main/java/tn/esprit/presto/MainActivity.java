package tn.esprit.presto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import tn.esprit.presto.dao.UserDao;
import tn.esprit.presto.database.AppDataBase;
import tn.esprit.presto.entities.User;

public class MainActivity extends AppCompatActivity {

    AppDataBase database ;
    UserDao userDao;
    private List<User> userList = new ArrayList<User>();

    EditText usernameET;
    EditText passwordET;
    Button connexion;
    Button inscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}

        database = AppDataBase.getAppDatabase(this);
        userDao = database.userDao();

        SharedPreferences sp = getSharedPreferences("userSp",MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        if (sp.getString("username","").equals("")) {
            usernameET = findViewById(R.id.username);
            passwordET = findViewById(R.id.password);
            connexion = findViewById(R.id.registreBTN);
            inscription = findViewById(R.id.registrationBtn);


            connexion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // check if username exist
                    User u = userDao.getUser(usernameET.getText().toString(),passwordET.getText().toString());
                    if (u == null){
                        Toast.makeText(getApplicationContext(),"Vérifiez vos informations!",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        String role = u.getRole();
                        editor.putString("username", usernameET.getText().toString()).apply();
                        editor.putString("user_id", String.valueOf(u.getId())).apply();
                        if(role.equals("Restaurent")){
                            Intent i = new Intent(MainActivity.this, RestoActivity.class);
                            startActivity(i);}
                        else {
                            Intent i = new Intent(MainActivity.this, HomeActivity.class);
                            startActivity(i);
                        }

                    }
                    // end check
                }
            });

            inscription.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(MainActivity.this,RegistrationActivity.class);
                    startActivity(i);
                }
            });

        }
            else {
            Intent i = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(i);

            }




        }

}