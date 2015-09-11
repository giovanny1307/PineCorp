package headapp.digitalexperiences.com.headapp;

/**
 * Created by Giovanny on 9/1/2015.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import java.util.List;


public class Mensajes extends Fragment  {

    private View v;

    private RecyclerView mRecyclerView;
    private Adapter mAdapter;

    private ImageButton mButton;
    private EditText mText;
    BaseDeDatos miDb;

    private List<Data> mData = new ArrayList<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        miDb = new BaseDeDatos(getActivity().getBaseContext());
        Log.i("Left", "onCreate()");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (v == null){v =inflater.inflate(R.layout.tab_3,container,false);
            mText = (EditText) v.findViewById(R.id.iputmensajes);
            mButton = (ImageButton) v.findViewById(R.id.btn_send);

            mRecyclerView = (RecyclerView)v.findViewById(R.id.recycler);
            mRecyclerView.setHasFixedSize(true);

            LinearLayoutManager llm = new LinearLayoutManager(getActivity().getBaseContext());
            mRecyclerView.setLayoutManager(llm);

            mButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View w) {

                    Data dataToAdd = new Data(mText.getText().toString());
                    mData.add(dataToAdd);
                    mAdapter.updateList(mData);

                }
            });

            // Setting the adapter.
            mAdapter = new Adapter(mData);
            mRecyclerView.setAdapter(mAdapter);

            } else { ((ViewGroup) v.getParent()).removeView(v);}

        return v;

    }



    public void addItem(View view) {

        // Add data locally to the list.
        Data dataToAdd = new Data(
                mText.getText().toString());
        mData.add(dataToAdd);

        mAdapter.addItem(mData.size(),dataToAdd);


    }


}

