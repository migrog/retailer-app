package com.negomatic.retailer.ui.inventory.viewitem;

import static com.negomatic.retailer.App.context;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


import com.negomatic.retailer.R;
import com.negomatic.retailer.ui.cart.CartActivity;
import com.negomatic.retailer.ui.inventory.newitem.NewItemActivity;
import com.negomatic.retailer.ui.inventory.newitem.NewItemFragment;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class ViewItemActivity extends AppCompatActivity  implements HasSupportFragmentInjector {

    private Toolbar toolbar;

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_view_item);
        this.configureDagger();

        toolbar = findViewById(R.id.toolbar_view_item);
        setSupportActionBar(toolbar);

        //Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, ViewItemFragment.class,null)
                    .commitNow();
        }
    }
    @Override
    public DispatchingAndroidInjector<Fragment> supportFragmentInjector(){
        return dispatchingAndroidInjector;
    }
    private void configureDagger(){
        AndroidInjection.inject(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tools_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case  R.id.edit:
                Intent intent = new Intent(this, NewItemActivity.class);
                Bundle bundle = this.getIntent().getExtras();
                int itemId = bundle.getInt("itemId");

                intent.putExtra("itemId", itemId);
                startActivity(intent);
                return true;

            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

