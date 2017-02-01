package com.example.pc2.Income_Expensehandler;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,AdapterView.OnItemSelectedListener {
    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;
    private ViewFlipper mViewFlipper;
    private Animation.AnimationListener mAnimationListener;
    private Context mContext;
   double income,expense,balance,cash_in,cash_out;
    TextView T_income,T_expense,T_balance;
    Button T_cash_in,T_cash_out;
    SharedPreferences myPreferences;
    String income_value,expense_value,balance_value;
    Spinner spinner_categories;
    RadioGroup Radio_income_mode;
    Button add_income;
    double income_amount;
    String income_categories,income_mode,income_date,income_time,income_desription,item;
    EditText amount_ed,date_ed,time_ed,description_ed;
    Spinner spinner_categories_ex;
    Button add_expense;
    double expense_amount;
    String expense_categories,expense_mode,expense_date,expense_time,expense_desription;
    EditText amount_ed_ex,date_ed_ex,time_ed_ex,description_ed_ex;
    @SuppressWarnings("deprecation")
    private final GestureDetector detector = new GestureDetector(new SwipeGestureDetector());
    public static final String MY_PREFERENCES = "MyPreferences";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        try {
            spinner_categories = (Spinner) findViewById(R.id.ux_dd_income_categories);
            spinner_categories.setOnItemSelectedListener(MainActivity.this);
            List<String> categories = new ArrayList<>();
            categories.add("Salary");
            categories.add("Interest Received");
            categories.add("Rent Received");
            categories.add("Cash");
            categories.add("Profit");
            categories.add("services");
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner_categories.setAdapter(dataAdapter);
            amount_ed = (EditText) findViewById(R.id.ux_ed_income_amount);
            date_ed = (EditText) findViewById(R.id.ux_ed_income_date);
            time_ed = (EditText) findViewById(R.id.ux_ed_income_time);
            description_ed = (EditText) findViewById(R.id.ux_ed_income_Description);
            Radio_income_mode = (RadioGroup) findViewById(R.id.ux_rdg_expense_mode);
            income_date = date_ed.getText().toString();
            income_time = time_ed.getText().toString();
            income_desription = description_ed.getText().toString();
            income_categories = item;
            income_mode = Radio_income_mode.toString();
            spinner_categories_ex = (Spinner) findViewById(R.id.ux_dd_expense_categories);
            spinner_categories_ex.setOnItemSelectedListener(MainActivity.this);
            List<String> categories_ex = new ArrayList<>();
            categories_ex.add("Rent");
            categories_ex.add("Grocery");
            categories_ex.add("Travel");
            categories_ex.add("Education");
            categories_ex.add("Personal");
            categories_ex.add("Food");
            ArrayAdapter<String> dataAdapter_ex = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories_ex);
            dataAdapter_ex.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner_categories_ex.setAdapter(dataAdapter_ex);
            amount_ed_ex = (EditText) findViewById(R.id.ux_ed_expense_amount);
            date_ed_ex = (EditText) findViewById(R.id.ux_ed_expense_date);
            time_ed_ex = (EditText) findViewById(R.id.ux_ed_expense_time);
            description_ed_ex = (EditText) findViewById(R.id.ux_ed_expense_Description);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        cash_in = getIntent().getDoubleExtra("IN_AMOUNT", 0.00);
        cash_out = getIntent().getDoubleExtra("OUT_AMOUNT", 0.00);
        income = income + cash_in;
        expense = expense + cash_out;
        balance = income - expense;
        income_value = String.valueOf(income) + " Rs";
        expense_value = String.valueOf(expense) + " Rs";
        balance_value = String.valueOf(balance) + " Rs";
        myPreferences = getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);
        T_income = (TextView) findViewById(R.id.ux_txt_income);
        T_expense = (TextView) findViewById(R.id.ux_txt_expense);
        T_balance = (TextView) findViewById(R.id.ux_txt_balance);
        T_cash_in = (Button) findViewById(R.id.ux_btn_add_income);
        T_cash_out = (Button) findViewById(R.id.ux_btn_add_expense);
        T_income.setText(income_value);
        T_expense.setText(expense_value);
        T_balance.setText(balance_value);
        SharedPreferences.Editor editor = myPreferences.edit();
        editor.putString( "abc",income_value);
        editor.putString("def",expense_value);
        editor.putString("ghi",balance_value);
        editor.apply();
        T_cash_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder adb = new AlertDialog.Builder(MainActivity.this);
                adb.setView(R.layout.activity_add_income);
                adb.setTitle("INCOME");
             //   adb.setIcon(android.R.drawable.ic_dialog_alert);
                adb.setPositiveButton("ADD INCOME", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        income+=income;
                        Toast.makeText(MainActivity.this, "gaggagagagagaagag", Toast.LENGTH_SHORT).show();
                    } });
                adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        finish();
                    } });
                adb.show();

            }
        });
        T_cash_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder adb = new AlertDialog.Builder(MainActivity.this);
                adb.setView(R.layout.activity_add__expense);
                adb.setTitle("Expense");
               // adb.setIcon(android.R.drawable.ic_dialog_alert);
                adb.setPositiveButton("ADD Expense", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        income+=income;
                        Toast.makeText(MainActivity.this, "gaggagagagagaagag", Toast.LENGTH_SHORT).show();
                    } });
                adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    } });
                adb.show();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        mContext = this;
        mViewFlipper = (ViewFlipper) this.findViewById(R.id.view_flipper);
        mViewFlipper.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(final View view, final MotionEvent event) {
                detector.onTouchEvent(event);
                return true;
            }
        });


        //animation listener
        mAnimationListener = new Animation.AnimationListener() {
            public void onAnimationStart(Animation animation) {
                //animation started event
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                //TODO animation stopped event
            }
        };
    }
    @Override
    protected void onActivityResult(int requestCode ,int resultCode ,Intent data ) {
        super.onActivityResult(requestCode, resultCode, data);
      //  double name = getIntent().getExtras().getDouble("IN_AMOUNT");

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 0:
                    if (resultCode == RESULT_OK) {
                    //   income= (name);
                    }
            }
        }
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        item = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    class SwipeGestureDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            try {

                if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(mContext, R.anim.left_in));
                    mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(mContext, R.anim.left_out));

                    mViewFlipper.getInAnimation().setAnimationListener(mAnimationListener);
                    mViewFlipper.showNext();
                    return true;
                } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(mContext, R.anim.right_in));
                    mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(mContext,R.anim.right_out));

                    mViewFlipper.getInAnimation().setAnimationListener(mAnimationListener);
                    mViewFlipper.showPrevious();
                    return true;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return false;
        }
    }

}
