package headapp.digitalexperiences.com.headapp;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Giovanny on 9/6/2015.
 */
public class ViewHolder extends RecyclerView.ViewHolder {

    public TextView texto;
    public CardView cv;

    public ViewHolder (View itemView){
        super(itemView);
        texto = (TextView)itemView.findViewById(R.id.mensaje_para_ti);
        cv = (CardView)itemView.findViewById(R.id.cv);

    }


}
