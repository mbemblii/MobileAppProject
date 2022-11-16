package tn.esprit.presto;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import tn.esprit.presto.dao.PlatDao;
import tn.esprit.presto.dao.UserDao;
import tn.esprit.presto.database.AdapterListener;
import tn.esprit.presto.database.AppDataBase;
import tn.esprit.presto.database.PlatAdapter;
import tn.esprit.presto.entities.Plat;
import tn.esprit.presto.entities.User;

import java.util.List;

public class RestoActivity extends AppCompatActivity implements AdapterListener {

    RecyclerView myrecyler;
    AppDataBase database ;
    UserDao userDao;
    PlatDao platDao;
    int type_menu;
    String tyepUser;
    SharedPreferences sp;

    EditText nom;

    EditText price;

    EditText desc;

    Button ajout;
    private PlatAdapter platAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resto);

        nom = findViewById(R.id.namePlat);
        price = findViewById(R.id.prix);
        desc = findViewById(R.id.description);
        ajout = findViewById(R.id.ajouter);
        myrecyler=findViewById(R.id.platsRecycler);

        database = AppDataBase.getAppDatabase(this);
        userDao = database.userDao();
        platDao = database.platDao();
        this.type_menu = R.menu.restaurent_menu;

        platAdapter = new PlatAdapter(this,this);
        myrecyler.setAdapter(platAdapter);
        myrecyler.setLayoutManager(new LinearLayoutManager(this));

        this.sp = getSharedPreferences("userSp",MODE_PRIVATE);

        setTitle("Hi,  "+sp.getString("username",""));
        ajout.setOnClickListener(new View.OnClickListener() {

            User u = userDao.findByUsername(sp.getString("username",""));
            @Override
            public void onClick(View v) {
                String nomOC = nom.getText().toString();
                String priceOC = price.getText().toString();
                String descOC = desc.getText().toString();

               if (nomOC.equals("") || priceOC.equals("")  || descOC.equals("") ){
                    Toast.makeText(getApplicationContext(),"Complétez vos informations!",Toast.LENGTH_SHORT).show();
                }
                else{
                    // we have to change the id  to the current connected user
                    Plat p = new Plat(0,nomOC,priceOC,descOC,u.getId());
                    platAdapter.addPlat(p);
                    platDao.addPlat(p);
                    nom.setText("");
                    price.setText("");
                    desc.setText("");
                    Toast.makeText(RestoActivity.this,"Plat ajouté!",Toast.LENGTH_SHORT).show();
                }}
        }

        );

    }
    private void fetchData(){
        platAdapter.clearData();
        List<Plat> platList= platDao.getAllPlats();
        for(int i=0;i<platList.size();i++)
        {
            Plat p = platList.get(i);
            platAdapter.addPlat(p);
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


        return super.onOptionsItemSelected(item);
    }


    @Override
    public void OnUpdate(Plat plat) {
    Intent intent = new Intent(this,UpdatePlat.class);
    intent.putExtra("model",plat);
    startActivity(intent);

    }

    @Override
    public void OnDelete(int id, int pos) {
        platDao.delete(id);
        platAdapter.removePlat(pos);

    }
    @Override
    public void onResume(){
        super.onResume();
        fetchData();
    }
}