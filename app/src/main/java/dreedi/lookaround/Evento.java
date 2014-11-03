package dreedi.lookaround;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;

import dreedi.lookaround.fragments.CercaFragment;
import dreedi.lookaround.fragments.Inicio;
import dreedi.lookaround.fragments.SalidasFragment;
import dreedi.lookaround.fragments.TodoFragment;


public class Evento extends Activity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    private NavigationDrawerFragment mNavigationDrawerFragment;

    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventos_drawer);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getFragmentManager();
        Fragment fragment = null;
            switch (position) {
                case 0:
                    mTitle = getString(R.string.title_section1);
                    fragment = new Inicio();
                    break;

                case 1:
                    mTitle = getString(R.string.title_section2);
                    fragment = new TodoFragment();
                    break;

                case 2:
                    mTitle = getString(R.string.title_section3);
                    fragment = new CercaFragment();
                    break;

                case 3:
                    mTitle = getString(R.string.title_section4);
                    fragment = new CercaFragment();
                    break;

                case 4:
                    mTitle = getString(R.string.title_section5);
                    fragment = new SalidasFragment();
                    break;

                case 5:
                    mTitle = getString(R.string.title_section6);
                    fragment = new CercaFragment();
                    break;
            }

        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }





    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.eventos_drawer, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



}
