package headapp.digitalexperiences.com.headapp;

/**
 * Created by Giovanny on 9/1/2015.
 */
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;

/**
 * Created by hp1 on 21-01-2015.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private int[] imageResId = {
            R.drawable.ic_launchersmall,
            R.drawable.ic_launcher,
            R.drawable.abc_ab_share_pack_mtrl_alpha
    };

    CharSequence Titles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created
    int NumbOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerAdapter is created


    // Build a Constructor and assign the passed Values to appropriate values in the class
    public ViewPagerAdapter(FragmentManager fm,CharSequence mTitles[], int mNumbOfTabsumb) {
        super(fm);

        this.Titles = mTitles;
        this.NumbOfTabs = mNumbOfTabsumb;

    }

    //This method return the fragment for the every position in the View Pager
    @Override
    public Fragment getItem(int position) {

        if(position == 0) // if the position is 0 we are returning the First tab
        {
            AjusteTiempos tab1 = new AjusteTiempos();
            return tab1;
        }
        else if (position == 1)         // As we are having 2 tabs if the position is now 0 it must be 1 so we are returning second tab
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

    // This method return the titles for the Tabs in the Tab Strip

    @Override
    public CharSequence getPageTitle(int position) {

        Drawable image = HeadAppApplication.getContext().getResources().getDrawable(imageResId[position]);
        ImageSpan imageSpan = new ImageSpan(image);
        SpannableString sS = new SpannableString(" ");
        sS.setSpan(imageSpan,0,sS.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return sS;
    }

    // This method return the Number of tabs for the tabs Strip

    @Override
    public int getCount() {
        return NumbOfTabs;
    }
}