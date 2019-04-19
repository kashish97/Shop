package in.codecorp.ssgcp.shop;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import in.codecorp.ssgcp.shop.Fragments.CartFragment;
import in.codecorp.ssgcp.shop.Fragments.CategoryFragment;
import in.codecorp.ssgcp.shop.Fragments.HomeFragment;
import in.codecorp.ssgcp.shop.Fragments.ProfileFragment;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            displaySelectedScreen(item.getItemId());
            return true;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        displaySelectedScreen(R.id.navigation_home);
    }

    public void displaySelectedScreen(int id) {
        Fragment fragment = null;

        if (id == R.id.navigation_home) {

            fragment= new HomeFragment();


        } else if (id == R.id.navigation_dashboard) {
            fragment = new CartFragment();

        } else if (id == R.id.navigation_notifications) {

            fragment = new ProfileFragment();}
             else if (id == R.id.cat) {

                fragment = new CategoryFragment();
             }



        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            //final String BACK_STACK_ROOT_TAG = "root_fragment";
            //FragmentManager fragmentManager = getSupportFragmentManager();

            // fragmentManager.popBackStack(BACK_STACK_ROOT_TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE);

            ft.replace(R.id.root, fragment);
            ft.commit();



        }
    }

}
