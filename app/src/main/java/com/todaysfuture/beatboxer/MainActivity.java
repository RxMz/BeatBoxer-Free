package com.todaysfuture.beatboxer;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

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

        //Getting Ads
        MobileAds.initialize(getApplicationContext(), "ca-app-pub-3150466150343288~2301613454");
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);



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


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 0, 0, "BeatBoxer Pro coming soon!");
        menu.add(0, 1, 1, "Exit");
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
