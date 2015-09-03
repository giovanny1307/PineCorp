package headapp.digitalexperiences.com.headapp;

/**
 * Created by Giovanny on 9/1/2015.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class Mensajes extends Fragment  {

    ImageButton subir;
    ArrayList<String> addArray = new ArrayList<String>();
    EditText texto;
    ListView listaprueba;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View v =inflater.inflate(R.layout.tab_3,container,false);

        texto = (EditText)v.findViewById(R.id.iputmensajes);
        subir = (ImageButton)v.findViewById(R.id.btn_send);
        listaprueba = (ListView)v.findViewById(R.id.listView);
        subir.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View w) {

                String tomarMensaje = texto.getText().toString();

                if(addArray.contains(tomarMensaje)) {

                    Toast.makeText(getActivity().getBaseContext(),"sos un capo",Toast.LENGTH_LONG).show();
                }
                else if (tomarMensaje==null || tomarMensaje.trim().equals("                                                      ")) {

                    Toast.makeText(getActivity().getBaseContext(),"introduce un mensaje",Toast.LENGTH_LONG).show();
                }
                else {
                    addArray.add(tomarMensaje);
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getBaseContext(),android.R.layout.simple_list_item_1, addArray);
                    listaprueba.setAdapter(adapter);
                    ((EditText)v.findViewById(R.id.iputmensajes)).setText(" ");

                }
            }
        });

        return v;
    }
}

