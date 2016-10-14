package com.ivanob.puntalradio;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.ivanob.puntalradio.fragments.ParrillaFragment;
import com.ivanob.puntalradio.fragments.PortadaFragment;
import com.ivanob.puntalradio.fragments.ProgramasFragment;
import com.ivanob.puntalradio.helper.RadioManager;
import com.ivanob.puntalradio.model.ConfigBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import com.ivanob.puntalradio.Consts;

import com.ivanob.puntalradio.Consts.*;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private RadioManager rm;
    private ConfigBean config = new ConfigBean();
    private final CharSequence[] items = {" Ninguno "," Parar en 15 min "," Parar en 30 min "," Parar en 1 h "};
    private int lastOptionTemp=0;
    private Timer timer=new Timer();
    private TimerSleepMode timerTask;
    private AlertDialog levelDialog;
    private boolean _doubleBackToExitPressedOnce = false;


    private void switchPlaystopButton(FloatingActionButton fab){
        if(rm.isPlaying()){ //It is playing, so I have to stop it
            rm.pausePlayer();
            fab.setImageDrawable(getResources().getDrawable(R.drawable.ic_play_arrow_white_48dp));
        }else{ //It is stopped, so I have to play it
            rm.resumePlayer();
            fab.setImageDrawable(getResources().getDrawable(R.drawable.ic_stop_white_48dp));
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createToolbar();

        Context context = this.getApplicationContext();
    //    rm = RadioManager.getInstance(context);
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                switchPlaystopButton(fab);
            }
        });

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
        try {
            this.loadConfiguration(context);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    private void loadConfiguration(Context ctx) throws IOException, JSONException {
        InputStream is = ctx.getAssets().open("info_general.json");
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 8);
        StringBuilder sb = new StringBuilder();

        String line = null;
        while ((line = reader.readLine()) != null)
        {
            sb.append(line + "\n");
        }
        String result = sb.toString();
        JSONObject jObject = new JSONObject(result);
        jObject = jObject.getJSONObject("informacion");
        config.setBlog(jObject.getString("blog"));
        config.setEmail(jObject.getString("email"));
        config.setFacebook(jObject.getString("facebook"));
        config.setTwitter(jObject.getString("twitter"));
        config.setUrlConnection(jObject.getString("url_conexion"));
    }

    private void createToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.title_actionbar);
        toolbar.setSubtitle(R.string.subtitle_actionbar);
        toolbar.setLogo(R.mipmap.ic_launcher_logo);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void setTimerSleepMode(long delay){
        if(timerTask!=null){
            timerTask.cancel(); //Cancelo el temporizador anterior
        }
        timerTask = new TimerSleepMode(this);
        timer.schedule(timerTask, delay);
    }

    private void showSleepPopup() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Modo sleep");
        builder.setSingleChoiceItems(items, lastOptionTemp, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                if(lastOptionTemp != item){ //Si se marco la misma opcion entonces no se hace nada
                    lastOptionTemp = item;
                    switch(item){
                        case Consts.NO_SLEEP:
                            if(timerTask!=null){
                                timerTask.cancel(); //Cancelo el temporizador anterior
                            }
                            break;
                        case Consts.SLEEP_15MIN:
                            setTimerSleepMode(Consts.MIN15_MILISEC);
                            break;
                        case Consts.SLEEP_30MIN:
                            setTimerSleepMode(Consts.MIN30_MILISEC);
                            break;
                        case Consts.SLEEP_1H:
                            setTimerSleepMode(Consts.H1_MILISEC);
                            break;

                    }
                }
                levelDialog.dismiss();
                //Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
            }
        });
        levelDialog = builder.create();
        levelDialog.show();
    }

    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_sleep:
                showSleepPopup();
                return true;
            case R.id.menu_about:
                showAboutDialog();
                return true;
            case R.id.menu_exit:
                //rm.pausePlayer();
                closeApp();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void closeApp(){
        //rm.pausePlayer();
        finish();
    }

    private void showAboutDialog(){
        AboutDialog about = new AboutDialog(this);
        about.setTitle("Acerca de");
        about.show();
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new PortadaFragment(config), "");
        adapter.addFragment(new ParrillaFragment(), "");
        adapter.addFragment(new ProgramasFragment(), "");
        viewPager.setAdapter(adapter);
    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_date_range);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_list);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

    }

    public void onBackPressed() {
        if (_doubleBackToExitPressedOnce) {
            //super.onBackPressed();
            closeApp();
            return;
        }
        this._doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Pulsa de nuevo para salir", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            public void run() {
                _doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }
}
