package tn.esprit.presto;

import androidx.appcompat.app.AppCompatActivity;

import tn.esprit.presto.dao.UserDao;
import tn.esprit.presto.database.AppDataBase;
import tn.esprit.presto.entities.User;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import tn.esprit.presto.entities.User;

public class RegistrationActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    AppDataBase database ;
    UserDao userDao;

    EditText usernameET;
    EditText passwordET;
    EditText fullnameET;
    EditText adresseET;
    EditText phoneET;
    String role;
    Button registreBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}

        database = AppDataBase.getAppDatabase(this);
        userDao = database.userDao();

        Spinner spinner = findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        usernameET = findViewById(R.id.username);
        passwordET = findViewById(R.id.password);
        fullnameET = findViewById(R.id.fullname);
        adresseET = findViewById(R.id.adresse);
        phoneET = findViewById(R.id.phone);
        registreBTN = findViewById(R.id.registreBTN);

        registreBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameET.getText().toString();
                String password = passwordET.getText().toString();
                String fullname = fullnameET.getText().toString();
                String adresse = adresseET.getText().toString();
                String phone = phoneET.getText().toString();
                if (username.equals("") || password.equals("")  || fullname.equals("") || adresse.equals("") || phone.equals("") ){
                    Toast.makeText(getApplicationContext(),"Complétez vos informations!",Toast.LENGTH_SHORT).show();
                }
                else {
                    // check if user already exist before registration
                    User u = userDao.findByUsername(username);
                    if (u != null){
                        Toast.makeText(getApplicationContext(),"Username Already exist!",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        // end check
                        // start addUser
                        User user = new User();
                        user.setRole(role);
                        user.setFullName(fullname);
                        user.setUsername(username);
                        user.setPassword(password);
                        user.setAddress(adresse);
                        user.setPhone(phone);
                        userDao.insertUser(user);
                        Toast.makeText(getApplicationContext(), "Done! Welcome", Toast.LENGTH_SHORT).show();
                        // end addUser
                        Intent i = new Intent(RegistrationActivity.this, MainActivity.class);
                        startActivity(i);
                    }
                }

            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        this.role = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(),"Role sélectionné est "+this.role,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}