package headapp.digitalexperiences.com.headapp;

/**
 * Created by Giovanny on 9/1/2015.
 */

import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.content.ContentUris;
import android.content.ContentValues;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import headapp.digitalexperiences.com.headapp.provider.TaskProvider;


public class Mensajes extends Fragment implements LoaderManager.LoaderCallbacks <Cursor>{

    private View v;

    private RecyclerView mRecyclerView;
    private Adapter mAdapter;

    private ImageButton mButton;
    private EditText mText;

    public static final String EXTRA_MENSAJEID = "mensajeId";

    long mensajeId;


    private List<Data> mData = new ArrayList<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAdapter = new Adapter(mData);

        getLoaderManager().initLoader(0, null, this);

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

                    save();

                }
            });

            // Setting the adapter.

            mRecyclerView.setAdapter(mAdapter);

            } else { ((ViewGroup) v.getParent()).removeView(v);}

        return v;

    }


    private void save() {

           String mensajeUser =    mText.getText().toString();

        ContentValues values = new ContentValues();
        values.put(TaskProvider.COL_MSG, mensajeUser);

        if(mensajeId == 0 ){
            Uri itemUri = getActivity().getContentResolver().insert(TaskProvider.CONTENT_URI, values);
            mensajeId = ContentUris.parseId(itemUri);
            Toast.makeText(getActivity(),"mensaje guardado 0",Toast.LENGTH_SHORT).show();


        } else {
            Uri itemUri = getActivity().getContentResolver().insert(TaskProvider.CONTENT_URI, values);
            mensajeId = ContentUris.parseId(itemUri);
            //Uri uri = ContentUris.withAppendedId(TaskProvider.CONTENT_URI, mensajeId);
            Toast.makeText(getActivity(),"mensaje guardado",Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getActivity(),TaskProvider.CONTENT_URI, null, null,null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.swapCursor(null);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

        mAdapter.swapCursor(null);

    }
}

