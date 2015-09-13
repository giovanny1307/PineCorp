package headapp.digitalexperiences.com.headapp;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import headapp.digitalexperiences.com.headapp.provider.TaskProvider;

/**
 * Created by Giovanny on 9/6/2015.
 */
public class Adapter extends RecyclerView.Adapter<ViewHolder> {



    Cursor cursor;
    int mensajeColIndex;
    int idColIndex;
    private Context contexto;
    private int lastPosition = -1;
    TaskProvider.DatabaseHelper databaseHelper;

    //constructor
    Adapter(Context context , TaskProvider.DatabaseHelper data){
        this.contexto = context;
        this.databaseHelper = data;

    }

    public void swapCursor(Cursor c){
        cursor = c;
        if(cursor != null) {
            cursor.moveToFirst();

            mensajeColIndex = cursor.getColumnIndex(TaskProvider.COL_MSG);

            idColIndex = cursor.getColumnIndex(TaskProvider.COL_ID);

            notifyItemInserted(cursor.getPosition() + 1);

        }

        notifyDataSetChanged();




        }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      CardView v = (CardView)LayoutInflater.from(parent.getContext()).inflate(

      R.layout.lista_tarjetas,parent,false) ;

        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final Context context = holder.texto.getContext();
        final long id = getItemId(position);

        cursor.moveToPosition(position);


        holder.texto.setText(cursor.getString(mensajeColIndex));

        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteMensaje(context, id);

            }
           });

        setAnimation(holder.cv,position);

    }

    public void deleteMensaje(Context context, long id) {

        context.getContentResolver().delete(ContentUris.withAppendedId(TaskProvider.CONTENT_URI, id), null, null);



    }

    public long getItemId(int position){
        cursor.moveToPosition(position);
        return cursor.getLong(idColIndex);
    }

    @Override
    public int getItemCount() {
       return cursor!= null ? cursor.getCount():0;
    }



    private void setAnimation(View viewToAnimate, int position)
    {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition)
        {
            Animation animation = AnimationUtils.loadAnimation(contexto, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }




}
