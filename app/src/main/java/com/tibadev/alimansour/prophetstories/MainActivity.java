package com.tibadev.alimansour.prophetstories;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.startapp.android.publish.StartAppAd;
import com.startapp.android.publish.StartAppSDK;
import com.startapp.android.publish.model.AutoInterstitialPreferences;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    List<Story> stories = null;
    String strProphet = null;
    String strContent = null;
    NetworkCheck networkCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        try {
            // Check Device Connectivity & view Ads if it is connected:
            NetworkCheck networkCheck = new NetworkCheck();
            if (networkCheck.check(this)) {
                // Check for updates
                String latestVersion = "";
                String currentVersion = getCurrentVersion();
                try {
                    latestVersion = new GetLatestVersion().execute().get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }

                // Show Update Alert Dialog at startup if versions are not equal
                if (!currentVersion.equals(latestVersion)) {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setIcon(R.drawable.ic_launcher);
                    builder.setTitle(R.string.text_update_alert_title);
                    builder.setMessage(R.string.text_update_alert_message);
                    builder.setPositiveButton(R.string.btn_update_alert_update, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //Click button action
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.tibadev.alimansour.prophetstories")));
                            dialog.dismiss();
                        }
                    });
                    builder.setCancelable(false);
                    builder.show();
                }
                // View Ads
                StartAppSDK.init(this, "210823214", false);
                StartAppAd.disableSplash();
                StartAppAd.enableAutoInterstitial();
                StartAppAd.setAutoInterstitialPreferences(new AutoInterstitialPreferences().setActivitiesBetweenAds(5));
            }

            // Check for permissions
            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                    // Check for Access fine location permission
                    if (checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        // Permission is available, start the app
                    } else {
                        requestPermissions(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 0);
                    }

                    // Should we show an explanation?
                    if (shouldShowRequestPermissionRationale(android.Manifest.permission.ACCESS_FINE_LOCATION)) {
                        // Explain to the user why we need ACCESS_FINE_LOCATION
                        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setIcon(R.drawable.ic_my_location_black_24dp);
                        builder.setTitle("استخدام تحديد المواقع");
                        builder.setMessage("التطبيق يقوم باستخدام صلاحية تحديد المواقع في هاتفك لعرض إعلانات مناسبة لك.");
                        builder.setPositiveButton("إخفاء", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //Click button action
                                dialog.dismiss();
                            }
                        });
                        builder.setCancelable(true);
                        builder.show();
                    }
                }
            } catch (Exception EX) {
            }

            listView = (ListView) findViewById(R.id.list);
            try {
                XMLPullParserHandler parser = new XMLPullParserHandler();
                stories = parser.parse(getAssets().open("stories.xml"));
                listView.setAdapter(new MyAdapter(this, android.R.layout.simple_list_item_1, stories));
            } catch (IOException e) {
                e.printStackTrace();
            }
            final List<Story> finalStories = stories;
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    // Retrieve story data
                    strProphet = finalStories.get(position).getProphet();
                    strContent = finalStories.get(position).getContent();
                    showStory();
                }
            });
        } catch (Exception e) {
        }
    }

    private void showStory() {
        Intent intent = new Intent(MainActivity.this, StoryActivity.class);
        intent.putExtra("prophet", strProphet);
        intent.putExtra("content", strContent);
        startActivity(intent);
    }

    public class MyAdapter extends ArrayAdapter<Story> {


        MyAdapter(Context context, int resource, List<Story> objects) {
            super(context, resource, objects);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, @NonNull ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.list_item, parent, false);
            TextView txtItemCompanion = (TextView) view.findViewById(R.id.txt_item_prophet);
            Story story = stories.get(position);
            txtItemCompanion.setText(story.getProphet());
            return view;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        try {
            if (requestCode == 0) {
                // Request for call phone permission.
                if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                    // Permission request was denied.
                    Toast.makeText(this, R.string.text_location_permission_denied, Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception EX) {
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        // Retrieve the share menu item
        MenuItem shareItem = menu.findItem(R.id.action_share);

        // Now get the ShareActionProvider from the item
        ShareActionProvider mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(shareItem);
        mShareActionProvider.setShareIntent(doShare());

        MenuItem rateItem = menu.findItem(R.id.action_rate);
        rateItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                //Try Google play
                intent.setData(Uri.parse("market://details?id=com.tibadev.alimansour.prophetstories"));
                if (!MyStartActivity(intent)) {
                    //Market (Google play) app seems not installed, let's try to open a webbrowser
                    intent.setData(Uri.parse("https://play.google.com/store/apps/details?com.tibadev.alimansour.prophetstories"));
                    if (!MyStartActivity(intent)) {
                        //Well if this also fails, we have run out of options, inform the user.
                        Toast.makeText(MainActivity.this, "عفواً لا يمكن فتح تطبيق جوجل بلاي ، برجاء تثبيت التطبيق.", Toast.LENGTH_SHORT).show();
                    }
                }
                return false;
            }
        });
        return true;
    }

    // Check for updates
    private String getCurrentVersion() {
        PackageManager pm = this.getPackageManager();
        PackageInfo pInfo = null;

        try {
            pInfo = pm.getPackageInfo(this.getPackageName(), 0);

        } catch (PackageManager.NameNotFoundException e1) {
            e1.printStackTrace();
        }

        assert pInfo != null;
        return pInfo.versionName;
    }

    public Intent doShare() {
        // populate the share intent with data
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "تطبيق قصص الأنبياء - أندرويد");
        intent.putExtra(Intent.EXTRA_TEXT, "قصص الأنبياء - أندرويد\nطيبة ديف للبرمجيات\n\nhttps://play.google.com/store/apps/details?com.tibadev.alimansour.prophetstories");
        return intent;
    }

    private boolean MyStartActivity(Intent aIntent) {
        try {
            startActivity(aIntent);
            return true;
        } catch (ActivityNotFoundException e) {
            return false;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //ToDo: noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_our_apps:
                Intent intent = new Intent(Intent.ACTION_VIEW);
                //Try Google play
                intent.setData(Uri.parse("market://dev?id=8256417273842663752"));
                if (!MyStartActivity(intent)) {
                    //Market (Google play) app seems not installed, let's try to open a webbrowser
                    intent.setData(Uri.parse("https://play.google.com/store/apps/dev?id=8256417273842663752"));
                    if (!MyStartActivity(intent)) {
                        //Well if this also fails, we have run out of options, inform the user.
                        Toast.makeText(MainActivity.this, "عفواً لا يمكن فتح تطبيق جوجل بلاي ، برجاء تثبيت التطبيق.", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.action_settings:

                break;
            case R.id.action_about:
                Intent intentabout = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intentabout);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
