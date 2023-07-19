package com.negomatic.retailer.ui;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import android.content.Intent;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import com.negomatic.retailer.R;

public class LoginActivity extends AppCompatActivity implements HasSupportFragmentInjector {
    //FOR DATA
    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private LoginViewModel viewModel;

    // FOR DESIGN

    @BindView(R.id.et_user) TextInputEditText et_user;
    @BindView(R.id.et_password) TextInputEditText et_password;
    @BindView(R.id.login_progress)
    ProgressBar mProgressView;
    @BindView(R.id.image_logo)
    ImageView mLogoView;
    @BindView(R.id.login_form)
    ScrollView mLoginFormView;
    @BindView(R.id.bt_login)
    MaterialButton bt_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_new);
        ButterKnife.bind(this);
        this.configureDagger();

        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login(view);
            }
        });

    }
    public void login(View v) {
        String user = et_user.getText().toString();
        String password = et_password.getText().toString();
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(()->{
            //mostrar indicador de carga
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    showProgress(true);
                }
            });

            viewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel.class);
            //Log.e("TAG", "antes de la peticion");
            viewModel.init(user, password);

            //ocultar indicacador de carga
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    showProgress(false);
                }
            });
            showMainScreen();

        });

    }

    private void configureDagger(){
        AndroidInjection.inject(this);
    }

    @Override
    public DispatchingAndroidInjector<Fragment> supportFragmentInjector(){
        return dispatchingAndroidInjector;
    }

    private void showMainScreen() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
    private void showProgress(boolean show) {
        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
        mProgressView.setIndeterminate(true);
        int visibility = show ? View.GONE : View.VISIBLE;
        mLogoView.setVisibility(visibility);
        mLoginFormView.setVisibility(visibility);
    }


}
