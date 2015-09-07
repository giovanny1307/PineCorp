package headapp.digitalexperiences.com.headapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Giovanny on 9/6/2015.
 */
public class Adapter extends RecyclerView.Adapter<ViewHolder> {

    private List<Data> mData = new ArrayList<Data>();

    //constructor
    Adapter(List<Data> datos){

        this.mData = datos;

    }

    public void updateList(List<Data> data) {
        this.mData = data;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.lista_tarjetas, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.texto.setText(mData.get(position).text);

    }

    public void addItem(int position, Data data) {
        mData.add(position, data);
        notifyItemInserted(position);
    }

    public void removeItem(int position) {
        mData.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

   /* @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }*/
}
