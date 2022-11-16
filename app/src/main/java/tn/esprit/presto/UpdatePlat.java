package tn.esprit.presto;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import tn.esprit.presto.dao.PlatDao;
import tn.esprit.presto.database.AppDataBase;
import tn.esprit.presto.entities.Plat;

public class UpdatePlat extends AppCompatActivity {

    private EditText nom, price,desc;
    private Button modif;
    private Plat plat;
    private AppDataBase database ;
    private PlatDao platDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_plat);

        database= AppDataBase.getAppDatabase(this);
        platDao= database.platDao();
        nom = findViewById(R.id.namePlat);
        price = findViewById(R.id.prix);
        desc = findViewById(R.id.description);
        modif = findViewById(R.id.modifier);

        plat = (Plat) getIntent().getSerializableExtra("model");
        nom.setText(plat.getName());
        price.setText(plat.getPrice());
        desc.setText(plat.getDescription());



        modif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plat.setName(nom.getText().toString());
                plat.setPrice(price.getText().toString());
                plat.setDescription(desc.getText().toString());

                Plat p = new Plat(plat.getId(),plat.getName(),plat.getPrice(),plat.getDescription(),plat.getRestoId());
                platDao.updatePlat(p);
                finish();
            }
        });
    }
}