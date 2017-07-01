package uzem.saucast.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import uzem.saucast.R;
import uzem.saucast.application.SauCastApplication;
import uzem.saucast.fragment.MediaFragment;
import uzem.saucast.fragment.MessageFragment;
import uzem.saucast.helper.SauCastClient;
import uzem.saucast.model.LikeModel;
import uzem.saucast.model.WatcherModel;

public class LiveActivity extends AppCompatActivity {

    AlertDialog dialog;
    private TextView mCounter;
    RelativeLayout msgCountLayout, layoutTxtCount;
    MenuItem item;
    ImageView imgMessage, imageLike;
    Button btnCloseReport, btnSendReport;
    public static boolean isOpen = false;
    Fragment liveFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(WListActivity.WEBINAR_TITLE);
        setContentView(R.layout.activity_live);
        startLiveStream();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        final SauCastApplication app = (SauCastApplication) this.getApplication();
        getMenuInflater().inflate(R.menu.menu_main, menu);

        item = menu.findItem(R.id.menu_count);
        MenuItemCompat.setActionView(item, R.layout.message_count_layout);
        msgCountLayout = (RelativeLayout) MenuItemCompat.getActionView(item);
        layoutTxtCount = (RelativeLayout) msgCountLayout.findViewById(R.id.layoutTxtCount);
        mCounter = (TextView) msgCountLayout.findViewById(R.id.txtCounter);
        imgMessage = (ImageView) msgCountLayout.findViewById(R.id.btnShow);
        imgMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCounter.setText(Integer.toString(0));
                msgCountLayout.setBackgroundColor(Color.TRANSPARENT);
                app.setCount(0);
                isOpen = true;
                FragmentManager fm = getFragmentManager();
                MessageFragment messageFragment = new MessageFragment();
                messageFragment.show(fm, "Message Fragment");
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.like) {
            final SauCastApplication app = (SauCastApplication) this.getApplication();
            app.Client.SendLike(3014);
            startLikeAnimation();
        } else if (item.getItemId() == R.id.report) {
            dialog = new AlertDialog.Builder(this).create();
            View view = LayoutInflater.from(this).inflate(R.layout.activity_report, null);
            dialog.setView(view);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            btnCloseReport = (Button) view.findViewById(R.id.btnCloseReport);
            btnSendReport = (Button) view.findViewById(R.id.btnSendReport);
            dialog.show();
            btnCloseReport.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            btnSendReport.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
        }
        return super.onOptionsItemSelected(item);
    }

    public void startLiveStream() {
        try {
            final SauCastApplication app = (SauCastApplication) this.getApplication();
            app.Client.setEventListener(new SauCastClient.SauCastListener() {

                @Override
                public void Load(WatcherModel watcherModel) {
                    app.setWatcherModel(watcherModel);
                }

                @Override
                public void statusOn(boolean started) {
                    if (!started) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                setContentView(R.layout.statuslayout);
                            }
                        });
                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(LiveActivity.this, LiveActivity.class);
                                startActivity(intent);
                            }
                        });
                    }
                }

                @Override
                public void presenterOn(String connectId) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "Sunucu giriş yaptı.", Toast.LENGTH_LONG).show();
                            MediaFragment fr= (MediaFragment)getFragmentManager().findFragmentById(R.id.fragment_place);
                            fr.presenterLoad();
                        }
                    });
                }

                @Override
                public void presenterOff(String connectId) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                                Toast.makeText(getApplicationContext(), "Sunucu çıkış yaptı.", Toast.LENGTH_LONG).show();
                        }
                    });

                }

                @Override
                public void webinarEndOn(int id) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "Webinar sonlandırıldı.", Toast.LENGTH_LONG).show();
                        }
                    });

                }

                @Override
                public void guestOn(String connectId) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "Konuk giriş yaptı.", Toast.LENGTH_LONG).show();
                            MediaFragment fr= (MediaFragment)getFragmentManager().findFragmentById(R.id.fragment_place);
                            fr.guestLoad();
                        }
                    });
                }

                @Override
                public void guestOff(String connectId) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "Konuk çıkış yaptı.", Toast.LENGTH_LONG).show();
                        }
                    });

                }

                @Override
                public void likedOn(LikeModel like) {
                    startLikeAnimation();
                }

                @Override
                public void descriptionOn(String d) {

                }

                @Override
                public void banned(String code) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "Webinara erişiminiz engellenmiştir.", Toast.LENGTH_LONG).show();
                        }
                    });

                }

                @Override
                public void limit(String code) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "İzleyici limiti dolmuştur.", Toast.LENGTH_LONG).show();
                        }
                    });

                }

                @Override
                public void messageCount() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mCounter.setText(Integer.toString(app.getCount()));
                        }
                    });
                }
            });
            app.Client.startSignalR();

        } catch (Exception e) {
            e.printStackTrace();
        }
        startFragment();
    }

    public void startFragment() {
        liveFragment = new MediaFragment();
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_place, liveFragment);
        fragmentTransaction.commit();
    }

    public void startLikeAnimation() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                imageLike = (ImageView) findViewById(R.id.img_clap);
                Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide);
                imageLike.startAnimation(animation1);
            }
        });

    }
}