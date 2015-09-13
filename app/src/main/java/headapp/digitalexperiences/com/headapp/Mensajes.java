package headapp.digitalexperiences.com.headapp;

/**
 * Created by Giovanny on 9/1/2015.
 */

import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
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



import headapp.digitalexperiences.com.headapp.provider.TaskProvider;



public class Mensajes extends Fragment implements LoaderManager.LoaderCallbacks <Cursor>{

    private View v;

    private RecyclerView mRecyclerView;
    private Adapter mAdapter;

    private ImageButton mButton;
    private EditText mText;

    TaskProvider.DatabaseHelper databaseHelper;

    public static final String EXTRA_MENSAJEID = "mensajeId";

    long mensajeId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mAdapter = new Adapter(getActivity().getBaseContext(),databaseHelper);

        getLoaderManager().initLoader(0, null, this);

        Log.i("Left", "onCreate()");

    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

       v =inflater.inflate(R.layout.tab_3,container,false);
            mText = (EditText) v.findViewById(R.id.iputmensajes);
            mButton = (ImageButton) v.findViewById(R.id.btn_send);

            mRecyclerView = (RecyclerView)v.findViewById(R.id.recycler);
            mRecyclerView.setHasFixedSize(true);

            LinearLayoutManager llm = new LinearLayoutManager(getActivity().getBaseContext());
            mRecyclerView.setLayoutManager(llm);
            //mRecyclerView.setItemAnimator(new SlideInLeftAnimator());
            mRecyclerView.setAdapter(mAdapter);

            mButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View w) {

                    String mensajePara_ti = mText.getText().toString();

                    if (mensajePara_ti.matches("")) {
                        Toast.makeText(getActivity(), "introduce un mensaje valido", Toast.LENGTH_SHORT).show();
                    } else {
                        save();
                    }
                    mText.setText("");
                }
            });

        return v;

    }

    private void save() {

        String mensajeUser =    mText.getText().toString();

        ContentValues values = new ContentValues();
        values.put(TaskProvider.COL_MSG, mensajeUser);

            Uri itemUri = getActivity().getContentResolver().insert(TaskProvider.CONTENT_URI, values);
            mensajeId = ContentUris.parseId(itemUri);
            //mAdapter.notifyItemInserted(mAdapter.cursor.getPosition());

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getActivity(),TaskProvider.CONTENT_URI, null, null,null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

        mAdapter.swapCursor(null);

    }
}

