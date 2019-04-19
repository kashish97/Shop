package in.codecorp.ssgcp.shop;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import in.codecorp.ssgcp.shop.Fragments.CartFragment;

public class CartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        Fragment fragment = new CartFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        //final String BACK_STACK_ROOT_TAG = "root_fragment";
        //FragmentManager fragmentManager = getSupportFragmentManager();

        // fragmentManager.popBackStack(BACK_STACK_ROOT_TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        ft.replace(R.id.root, fragment);
        ft.commit();
    }

    public void re(){
        recreate();
    }
}
