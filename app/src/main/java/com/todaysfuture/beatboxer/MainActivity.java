package com.todaysfuture.beatboxer;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends Activity {
    int i = 0;
    EditText mEdit;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final EditText mEdit = (EditText) findViewById(R.id.editText1);
        final EditText speed = (EditText) findViewById(R.id.speed);

        final SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(this);
        mEdit.setText(prefs.getString("autoSave", "kssscssskskscsss"));
        mEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                prefs.edit().putString("autoSave", s.toString()).apply();
            }
        });

        //Getting Ads
        MobileAds.initialize(getApplicationContext(), "ca-app-pub-3150466150343288~2301613454");
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        //Get Data Back
        SharedPreferences sometune = getSharedPreferences("autoSave", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sometune.edit();
        editor.putString("autoSave", mEdit.getText().toString()).commit();



        ((Button) findViewById(R.id.b)).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                MediaPlayer mp = MediaPlayer.create(MainActivity.this, R.raw.b);
                mp.setOnCompletionListener(new OnCompletionListener() {
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                    }
                });
                mp.start();
            }
        });
        ((Button) findViewById(R.id.t)).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                MediaPlayer mp = MediaPlayer.create(MainActivity.this, R.raw.t);
                mp.setOnCompletionListener(new OnCompletionListener() {
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                    }
                });
                mp.start();

            }
        });
        ((Button) findViewById(R.id.k)).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                MediaPlayer mp = MediaPlayer.create(MainActivity.this, R.raw.k);
                mp.setOnCompletionListener(new OnCompletionListener() {
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                    }
                });
                mp.start();

            }
        });
        ((Button) findViewById(R.id.start)).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                MainActivity.this.i = 0;
                final EditText editText = mEdit;
                final EditText editText2 = speed;
                new Thread() {
                    public void run() {
                        try {
                            if (editText.getText().toString().toCharArray()[MainActivity.this.i] == 'k'||editText.getText().toString().toCharArray()[MainActivity.this.i] == 'K') {
                                playb();
                            } else if (editText.getText().toString().toCharArray()[MainActivity.this.i] == 's'||editText.getText().toString().toCharArray()[MainActivity.this.i] == 'S') {
                                playt();
                            } else if (editText.getText().toString().toCharArray()[MainActivity.this.i] == 'c'||editText.getText().toString().toCharArray()[MainActivity.this.i] == 'C') {
                                playk();
                            }
                            Thread.sleep(Long.parseLong(editText2.getText().toString()));
                            MainActivity access$0 = MainActivity.this;
                            access$0.i++;
                            if (editText.getText().toString().toCharArray()[MainActivity.this.i] != '\u0000') {
                                run();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    private void playt() {
                        MediaPlayer mp = MediaPlayer.create(MainActivity.this, R.raw.t);
                        mp.setOnCompletionListener(new OnCompletionListener() {
                            public void onCompletion(MediaPlayer mp) {
                                mp.release();
                            }
                        });
                        mp.start();
                    }

                    private void playk() {
                        MediaPlayer mp = MediaPlayer.create(MainActivity.this, R.raw.k);
                        mp.setOnCompletionListener(new OnCompletionListener() {
                            public void onCompletion(MediaPlayer mp) {
                                mp.release();
                            }
                        });
                        mp.start();
                    }

                    private void playb() {
                        MediaPlayer mp = MediaPlayer.create(MainActivity.this, R.raw.b);
                        mp.setOnCompletionListener(new OnCompletionListener() {
                            public void onCompletion(MediaPlayer mp) {
                                mp.release();
                            }
                        });
                        mp.start();
                    }
                }.start();
            }
        });
        Button reset=(Button)findViewById(R.id.reset);
        reset.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mEdit.setText("kssscssskskscsss");
                AppRater.showRateDialog(MainActivity.this,null);
            }
        });

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 0, 0, "BeatBoxer Pro coming soon!");
        menu.add(0,1,1,"Rate Us!");
        menu.add(0, 1, 1, "Exit");
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Context context=getApplicationContext();
        switch (item.getItemId()) {
            case 1:
                Uri uri = Uri.parse("market://details?id=" + context.getPackageName());
                Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                // To count with Play market backstack, After pressing back button,
                // to taken back to our application, we need to add following flags to intent.
                goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                        Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET |
                        Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                try {
                    startActivity(goToMarket);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://play.google.com/store/apps/details?id=" + context.getPackageName())));
                }
                break;
            case 2:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }


}
