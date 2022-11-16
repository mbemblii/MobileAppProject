package tn.esprit.presto.database;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.jetbrains.annotations.NotNull;
import tn.esprit.presto.R;
import tn.esprit.presto.entities.Plat;

import java.util.ArrayList;
import java.util.List;

public class PlatAdapter extends RecyclerView.Adapter<PlatAdapter.MyViewHolder>{
    private Context context;
    private List<Plat> plats;
    private AdapterListener adapterListener;

    public PlatAdapter(Context context, AdapterListener listener) {
        this.context = context;
        this.adapterListener=listener;
        plats = new ArrayList<>();
    }

    public void addPlat(Plat plat){
        plats.add(plat);
        notifyDataSetChanged();
    }
    public void removePlat(int position){
        plats.remove(position);
        notifyDataSetChanged();
    }
    public void clearData(){
        plats.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PlatAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_plat,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  PlatAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Plat plat = plats.get(position);
        holder.name.setText(plat.getName());
        holder.price.setText(plat.getPrice());
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapterListener.OnUpdate(plat);

            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapterListener.OnDelete(plat.getId(),position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return plats.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView name, price;
        private ImageView delete, edit;
        public  MyViewHolder(@NonNull View itemView){
            super(itemView);
            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
            edit = itemView.findViewById(R.id.edit);
            delete = itemView.findViewById(R.id.delete);
        }

    }


}
