package headapp.digitalexperiences.com.headapp;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import headapp.digitalexperiences.com.headapp.provider.TaskProvider;

/**
 * Created by Giovanny on 9/6/2015.
 */
public class Adapter extends RecyclerView.Adapter<ViewHolder> {

    private List<Data> mData = new ArrayList<Data>();

    Cursor cursor;
    int mensajeColIndex;
    int idColIndex;


    public void swapCursor(Cursor c){
        cursor = c;
        if(cursor != null) {
            cursor.moveToFirst();
            mensajeColIndex = cursor.getColumnIndex(TaskProvider.COL_MSG);

            idColIndex = cursor.getColumnIndex(TaskProvider.COL_ID);
        }

        notifyDataSetChanged();

        }

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

        Context context = holder.texto.getContext();
        long id = getItemId(position);

        cursor.moveToPosition(position);

            holder.texto.setText(cursor.getString(mensajeColIndex));

            holder.cv.setOnClickListener();

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


}
