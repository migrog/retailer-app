package com.negomatic.retailer.ui;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PointF;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.SpannableString;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Toast;

import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetSequence;
import com.getkeepsafe.taptargetview.TapTargetView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;

import javax.inject.Inject;

import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

import com.negomatic.retailer.R;
import com.negomatic.retailer.ui.cart.CartActivity;
import com.negomatic.retailer.ui.catalog.CatalogFragment;
import com.negomatic.retailer.ui.inventory.newitem.NewItemActivity;
import com.negomatic.retailer.ui.menu.MenuFragment;
import com.negomatic.retailer.ui.menu.create.MenuCreateBottomSheetFragment;
import com.negomatic.retailer.ui.orders.OrdersFragment;

public class MainActivity extends AppCompatActivity implements HasSupportFragmentInjector,View.OnTouchListener {
    //FOR DESIGN
    private int mTouchAreaDiameter;
    private int mTapTouchSlop;
    private boolean mIsDragging;
    private View mDragView;
    private PointF mOriginalViewPosition = new PointF();
    private PointF mCurrentViewPosition = new PointF();
    private PointF mOriginalTouchPosition = new PointF();


    private Toolbar toolbar;
    private String menuSelected = "CATALOG";

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    private static String USER_LOGIN = "user1";
    private static final String MENU_CATALOG = "CATALOG";
    private static final String MENU_ORDERS = "ORDERS";
    private static final String MENU_MORE = "MORE";

    private static final String TAG = "MainActivity";

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;
            Bundle bundle = null;
            switch (item.getItemId()) {
                case R.id.navigation_catalog:
                    MainActivity.super.setTitle(getResources().getString(R.string.title_catalog));
                    toolbar.setVisibility(View.VISIBLE);
                    fragment = new CatalogFragment();
                    menuSelected = MENU_CATALOG;
                    break;
                case R.id.navigation_orders:
                    MainActivity.super.setTitle(getResources().getString(R.string.title_orders));
                    toolbar.setVisibility(View.VISIBLE);
                    fragment = new OrdersFragment();
                    menuSelected = MENU_ORDERS;
                    break;
                case R.id.navigation_menu:
                    MainActivity.super.setTitle("Menú");
                    fragment = new MenuFragment();
                    menuSelected = MENU_MORE;
                    break;

            }

            return showFragment(fragment);
        }
    };

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_NoActionBar);
        super.onCreate(savedInstanceState);

        //Redirección al Login
        /*if (!SessionPrefs.get(App.context).isLoggedIn()) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }*/

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        this.configureDagger();

        toolbar = (Toolbar) findViewById(R.id.mibarra);
        toolbar.inflateMenu(R.menu.tools_main);
        setSupportActionBar(toolbar);

        mDragView =  findViewById(R.id.draggable_view);
        mDragView.setOnTouchListener(this);
        //mTouchAreaDiameter = getResources().getDimensionPixelSize(io.mattcarroll.hover.R.dimen.hover_exit_radius);
        mTouchAreaDiameter = getResources().getDimensionPixelSize(R.dimen.hover_exit_radius);
        mTapTouchSlop = ViewConfiguration.get(this).getScaledTouchSlop();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_catalog);
        this.setTitle(getResources().getString(R.string.title_catalog));
        this.configureFmc();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        switch (menuSelected){
            case MENU_MORE:
                getMenuInflater().inflate(R.menu.tools_menu, menu);
                break;
            default:
                getMenuInflater().inflate(R.menu.tools_main, menu);
                break;
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.cart){
            Toast.makeText(this, "Carrito", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, CartActivity.class));
            return true;
        }

/*        if (id == R.id.notificacion) {
            Toast.makeText(this, "Notificación", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, NotificationActivity.class));
            return true;
        }
        if (id == R.id.configuracion) {
            //Toast.makeText(this, "Configuración", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (id == R.id.cerrarSesion) {
            //Toast.makeText(this, "Cerrar sesión", Toast.LENGTH_SHORT).show();
            SessionPrefs.get(App.context).logOut();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return true;
        }*/
        if(id == R.id.create){
            MenuCreateBottomSheetFragment b = MenuCreateBottomSheetFragment.newInstance();
            b.setStyle(DialogFragment.STYLE_NORMAL,R.style.BottomSheetDialogTheme);
            b.show(getSupportFragmentManager(),"Crear");
        }
      return super.onOptionsItemSelected(item);
    }
    @Override
    public DispatchingAndroidInjector<Fragment> supportFragmentInjector(){
        return dispatchingAndroidInjector;
    }
    public boolean showFragment(Fragment fragment){
        if(fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment, null)
                    .commit();
            return true;
        }
        return false;
    }

    private void configureDagger(){
        AndroidInjection.inject(this);
    }
    public void configureFmc(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create channel to show notifications.
            String channelId  = getString(R.string.default_notification_channel_id);
            String channelName = getString(R.string.default_notification_channel_name);
            NotificationManager notificationManager =
                    getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(new NotificationChannel(channelId,
                    channelName, NotificationManager.IMPORTANCE_LOW));
        }
        // If a notification message is tapped, any data accompanying the notification
        // message is available in the intent extras. In this sample the launcher
        // intent is fired when the notification is tapped, so any accompanying data would
        // be handled here. If you want a different intent fired, set the click_action
        // field of the notification message to the desired intent. The launcher intent
        // is used when no click_action is specified.
        //
        // Handle possible data accompanying notification message.
        // [START handle_data_extras]
        if (getIntent().getExtras() != null) {
            for (String key : getIntent().getExtras().keySet()) {
                Object value = getIntent().getExtras().get(key);
                Log.d(TAG, "Key: " + key + " Value: " + value);
            }
        }
        // [END handle_data_extras]

        //NOTIFICACION POR SUSCRIPCION A UN CANAL
        Log.d(TAG, "Subscribing to news topic");
        // [START subscribe_topics]
        FirebaseMessaging.getInstance().subscribeToTopic("news")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = getString(R.string.msg_subscribed);
                        if (!task.isSuccessful()) {
                            msg = getString(R.string.msg_subscribe_failed);
                        }
                        Log.d(TAG, msg);
                        //Toast.makeText(App.context, msg, Toast.LENGTH_SHORT).show(); //comment
                    }
                });
        // [END subscribe_topics]

        //NOTIFICACION POR TOKEN
        // Get token
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "getInstanceId failed", task.getException());
                            return;
                        }

                        // Get new Instance ID token
                        String token = task.getResult().getToken();

                        // Log and toast
                        String msg = getString(R.string.msg_token_fmt, token);
                        Log.d(TAG, msg);
                        //Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show(); //comment
                    }
                });

    }
    private void configureTapTargetView(){
        // We have a sequence of targets, so lets build it!
        final TapTargetSequence sequence = new TapTargetSequence(this)
                .targets(
                        TapTarget.forView(findViewById(R.id.navigation_orders), "Pedidos!", "Presione aquí para gestionar tus pedidos")
                                .tintTarget(false)
                                .id(1),
                        TapTarget.forView(findViewById(R.id.navigation_menu), "Opciones!", "Presione aquí para ver mas opciones")
                                .tintTarget(false)
                                .id(2),
/*                        TapTarget.forToolbarMenuItem(toolbar,R.id.notificacion,"Notificaciones!", "Presione aquí para ver las notificaciones de tus operaciones")
                                //.dimColor(android.R.color.black)
                                //.outerCircleColor(R.color.colorCloud)
                                //.targetCircleColor(android.R.color.black)
                                //.transparentTarget(true)
                                //.textColor(android.R.color.black)
                                .tintTarget(false)
                                .id(3),*/
                        // You can also target the overflow button in your toolbar
                        TapTarget.forToolbarOverflow(toolbar, "Mas opciones!", "Presiona aquí para ver mas opciones")
                                //.dimColor(android.R.color.black)
                                //.outerCircleColor(R.color.colorCloud)
                                //.targetCircleColor(android.R.color.black)
                                //.transparentTarget(true)
                                //.textColor(android.R.color.black)
                                .tintTarget(false)
                                .id(4)
                )
                .listener(new TapTargetSequence.Listener() {
                    // This listener will tell us when interesting(tm) events happen in regards
                    // to the sequence
                    @Override
                    public void onSequenceFinish() {
                        //((TextView) findViewById(R.id.educated)).setText("Congratulations! You're educated now!");
                    }

                    @Override
                    public void onSequenceStep(TapTarget lastTarget, boolean targetClicked) {
                        Log.d("TapTargetView", "Clicked on " + lastTarget.id());
                    }

                    @Override
                    public void onSequenceCanceled(TapTarget lastTarget) {
                        showDialogGuide();
                    }
                });
        // You don't always need a sequence, and for that there's a single time tap target
        final SpannableString spannedDesc = new SpannableString("Presione aquí para ver tus productos");
        //spannedDesc.setSpan(new UnderlineSpan(), spannedDesc.length() - "TapTargetView".length(), spannedDesc.length(), 0);
        TapTargetView.showFor(this, TapTarget.forView(findViewById(R.id.navigation_catalog), "Productos!", spannedDesc)
                .cancelable(true)
                .drawShadow(true)
                .titleTextDimen(R.dimen.title_text_size)
                .tintTarget(false), new TapTargetView.Listener() {
            @Override
            public void onTargetClick(TapTargetView view) {
                super.onTargetClick(view);
                // .. which evidently starts the sequence we defined earlier
                sequence.start();
            }

            @Override
            public void onOuterCircleClick(TapTargetView view) {
                super.onOuterCircleClick(view);
                //Toast.makeText(view.getContext(), "You clicked the outer circle!", Toast.LENGTH_SHORT).show();
                Log.d("TapTargetViewSample", "ou clicked the outer circle");
            }

            @Override
            public void onTargetDismissed(TapTargetView view, boolean userInitiated) {
                Log.d("TapTargetViewSample", "You dismissed me :(");
            }

            @Override
            public void onTargetCancel(TapTargetView view) {
                super.onTargetCancel(view);
                showDialogGuide();
            }
        });
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "ACTION_DOWN");
                mIsDragging = false;

                mOriginalViewPosition = getDragViewCenterPosition();
                mCurrentViewPosition = new PointF(mOriginalViewPosition.x, mOriginalViewPosition.y);
                mOriginalTouchPosition.set(motionEvent.getRawX(), motionEvent.getRawY());

                return true;
            case MotionEvent.ACTION_MOVE:
                Log.v(TAG, "ACTION_MOVE. motionX: " + motionEvent.getRawX() + ", motionY: " + motionEvent.getRawY());
                float dragDeltaX = motionEvent.getRawX() - mOriginalTouchPosition.x;
                float dragDeltaY = motionEvent.getRawY() - mOriginalTouchPosition.y;
                mCurrentViewPosition = new PointF(
                        mOriginalViewPosition.x + dragDeltaX,
                        mOriginalViewPosition.y + dragDeltaY
                );

                if (mIsDragging || !isTouchWithinSlopOfOriginalTouch(dragDeltaX, dragDeltaY)) {
                    if (!mIsDragging) {
                        // Dragging just started
                        Log.d(TAG, "MOVE Start Drag.");
                        mIsDragging = true;
                    } else {
                        moveDragViewTo(mCurrentViewPosition);
                    }
                }

                return true;
            case MotionEvent.ACTION_UP:
                if (!mIsDragging) {
                    Log.d(TAG, "ACTION_UP: Tap.");
                    //showGuide();
                    startActivity(new Intent(this, NewItemActivity.class));

                } else {
                    Log.d(TAG, "ACTION_UP: Released from dragging.");
                }

                return true;
            default:
                return false;
        }
    }
    public void showGuide() {
        //Toast.makeText(MainActivity.this, "Clicked!", Toast.LENGTH_SHORT).show();
        configureTapTargetView();
    }

    private boolean isTouchWithinSlopOfOriginalTouch(float dx, float dy) {
        double distance = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
        return distance < mTapTouchSlop;
    }
    private PointF getDragViewCenterPosition() {
        return convertCornerToCenter(new PointF(
                mDragView.getX(),
                mDragView.getY()
        ));
    }
    private void moveDragViewTo(PointF centerPosition) {
        Log.d(TAG, "Moving drag view (" + mDragView.hashCode() + ") to: " + centerPosition);
        PointF cornerPosition = convertCenterToCorner(centerPosition);
        mDragView.setX(cornerPosition.x);
        mDragView.setY(cornerPosition.y);
    }
    private PointF convertCornerToCenter(@NonNull PointF cornerPosition) {
        return new PointF(
                cornerPosition.x + (mTouchAreaDiameter / 2),
                cornerPosition.y + (mTouchAreaDiameter / 2)
        );
    }
    private PointF convertCenterToCorner(@NonNull PointF centerPosition) {
        return new PointF(
                centerPosition.x - (mTouchAreaDiameter / 2),
                centerPosition.y - (mTouchAreaDiameter / 2)
        );
    }

    private void showDialogGuide(){
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        //Yes button clicked
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        //No button clicked
                        break;
                }
            }
        };
        final AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                .setTitle("") //modify
                .setMessage("Desea quitar la guía?")
                .setPositiveButton("Quitar", dialogClickListener)
                .setNegativeButton("Cancelar", dialogClickListener)
                .setCancelable(false)
                .show();
    }
}
