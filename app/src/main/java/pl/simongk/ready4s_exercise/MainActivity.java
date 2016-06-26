package pl.simongk.ready4s_exercise;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private ListView drawerList;
    private ArrayAdapter < String > drawerAdapter;
    private ActionBarDrawerToggle drawerToggle;
    private DrawerLayout drawerLayout;
    private Fragment1 fragment1;
    private Fragment2 fragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerList = (ListView) findViewById(R.id.navList);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        changeFragments(fragment1);

        addDrawerItems();
        setupDrawer();

        getSupportActionBar().setTitle("Fragment 1");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }


    private void changeFragments(Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.main, fragment);
        transaction.commit();
    }


    /*
     * adding items to nav bar
     * */
    private void addDrawerItems() {
        final String[] fragmentArray = {
                "Fragment 1",
                "Fragment 2"
        };
        drawerAdapter = new ArrayAdapter <> (this, android.R.layout.simple_list_item_1, fragmentArray);
        drawerList.setAdapter(drawerAdapter);
        drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView < ? > parent, View view, int position, long id) {
                String item = drawerAdapter.getItem(position);
                if (item.equals("Fragment 1")) {
                    getSupportActionBar().setTitle("Fragment 1");
                    changeFragments(fragment1);
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else if (item.equals("Fragment 2")) {
                    getSupportActionBar().setTitle("Fragment 2");
                    changeFragments(fragment2);
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
            }
        });
    }

    private void setupDrawer() {
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open,
                R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle("Menu");
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
            }
        };
        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerLayout.addDrawerListener(drawerToggle);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return drawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);

    }
}