package headapp.digitalexperiences.com.headapp;


import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.view.ViewPager;
import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;


public class PantallaDeEntrada extends AppCompatActivity implements MaterialTabListener {


    private Toolbar toolbar;
    private ViewPager pager;
    private MaterialTabHost tabs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_de_entrada);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setLogo(R.mipmap.ic_launcherhapp);
        getSupportActionBar().setTitle("");




        tabs= (MaterialTabHost) findViewById(R.id.materialTabHost);
        pager= (ViewPager) findViewById(R.id.pager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        pager.setAdapter(adapter);
        pager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){

            @Override
            public void onPageSelected(int position) {
                tabs.setSelectedNavigationItem(position);
            }
        });

        for (int i = 0; i < adapter.getCount(); i++) {
            tabs.addTab(
                    tabs.newTab()
                            .setIcon(adapter.getIcon(i))
                            .setTabListener(this)
            );
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pantalla_de_entrada, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(MaterialTab materialTab) {

        pager.setCurrentItem(materialTab.getPosition());

    }

    @Override
    public void onTabReselected(MaterialTab materialTab) {

    }

    @Override
    public void onTabUnselected(MaterialTab materialTab) {


    }

    private class ViewPagerAdapter extends FragmentStatePagerAdapter {

        int icons[] = {R.mipmap.horadecristo,
                R.mipmap.gafas,
                R.mipmap.ic_create_white_48dp};


        public ViewPagerAdapter(android.support.v4.app.FragmentManager fm){

            super(fm);
        }

        public android.support.v4.app.Fragment getItem(int num){
            if(num == 0) // if the position is 0 we are returning the First tab
            {
                AjusteTiempos tab1 = new AjusteTiempos();
                return tab1;
            }
            else if(num == 1)             // As we are having 2 tabs if the position is now 0 it must be 1 so we are returning second tab
            {
                HeadApp tab2 = new HeadApp();
                return tab2;
            }
            else
            {
                Mensajes tab3 = new Mensajes();
                return tab3;
            }
        }

        public int getCount() {
            return 3;
        }

        private Drawable getIcon(int position){

            return getResources().getDrawable(icons[position]);
        }

    }
}
