package com.tibadev.alimansour.prophetstories.activities

import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.ShareActionProvider
import androidx.appcompat.widget.Toolbar
import androidx.core.view.MenuItemCompat
import com.tibadev.alimansour.prophetstories.BuildConfig
import com.tibadev.alimansour.prophetstories.R
import com.tibadev.alimansour.prophetstories.models.Story
import com.tibadev.alimansour.prophetstories.util.Network
import com.tibadev.alimansour.prophetstories.util.XMLPullParserHandler
import com.tibadev.alimansour.prophetstories.util.showInterstitialAd
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private lateinit var listView: ListView
    private lateinit var stories: List<Story>
    private lateinit var strProphet: String
    private lateinit var strContent: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        try {
            val network = Network(this)
            if (network.isConnected()) checkForUpdates()

            listView = findViewById<View>(R.id.list) as ListView
            try {
                val parser = XMLPullParserHandler()
                stories = parser.parse(assets.open("stories.xml"))
                listView.adapter = MyAdapter(this, android.R.layout.simple_list_item_1, stories)
            } catch (e: IOException) {
                e.printStackTrace()
            }
            val finalStories = stories
            listView.onItemClickListener =
                OnItemClickListener { _, _, position, _ ->
                    strProphet = finalStories[position].prophet
                    strContent = finalStories[position].content
                    showInterstitialAd(this)
                    showStory()
                }
        } catch (e: Exception) {
            Log.e(javaClass.simpleName, e.message.toString())
        }
    }

    private fun showStory() {
        val intent = Intent(this@MainActivity, StoryActivity::class.java)
        intent.putExtra("prophet", strProphet)
        intent.putExtra("content", strContent)
        startActivity(intent)
    }

    inner class MyAdapter internal constructor(
        context: Context?,
        resource: Int,
        objects: List<Story>?
    ) : ArrayAdapter<Story?>(
        context!!, resource, objects!!
    ) {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val inflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val view = inflater.inflate(R.layout.list_item, parent, false)
            val txtItemCompanion = view.findViewById<View>(R.id.txt_item_prophet) as TextView
            val (prophet) = stories[position]
            txtItemCompanion.text = prophet
            return view
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        // Retrieve the share menu item
        val shareItem = menu.findItem(R.id.action_share)

        // Now get the ShareActionProvider from the item
        val mShareActionProvider =
            MenuItemCompat.getActionProvider(shareItem) as ShareActionProvider
        mShareActionProvider.setShareIntent(doShare())
        val rateItem = menu.findItem(R.id.action_rate)
        rateItem.setOnMenuItemClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            //Try Google play
            intent.data = Uri.parse("market://details?id=com.tibadev.alimansour.prophetstories")
            if (!myStartActivity(intent)) {
                //Market (Google play) app seems not installed, let's try to open a webbrowser
                intent.data =
                    Uri.parse("https://play.google.com/store/apps/details?com.tibadev.alimansour.prophetstories")
                if (!myStartActivity(intent)) {
                    //Well if this also fails, we have run out of options, inform the user.
                    Toast.makeText(
                        this@MainActivity,
                        "عفواً لا يمكن فتح تطبيق جوجل بلاي ، برجاء تثبيت التطبيق.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            false
        }
        return true
    }

    private fun doShare(): Intent {
        // populate the share intent with data
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_SUBJECT, "تطبيق قصص الأنبياء - أندرويد")
        intent.putExtra(
            Intent.EXTRA_TEXT,
            "قصص الأنبياء - أندرويد\nطيبة ديف للبرمجيات\n\nhttps://play.google.com/store/apps/details?com.tibadev.alimansour.prophetstories"
        )
        return intent
    }

    private fun myStartActivity(aIntent: Intent): Boolean {
        return try {
            startActivity(aIntent)
            true
        } catch (e: ActivityNotFoundException) {
            false
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_privacy_policy ->
                Network(this).openLink(getString(R.string.privacy_policy_url))

            R.id.action_our_apps -> showOurApps()

            R.id.action_about -> {
                val intent = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showOurApps() {
        try {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(getString(R.string.our_apps_market_url))
                )
            )
        } catch (exception: ActivityNotFoundException) {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.our_apps_url))))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun checkForUpdates() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val currentVersion = BuildConfig.VERSION_NAME
                val latestVersion = CoroutineScope(Dispatchers.IO).async {
                    try {
                        // retrieve the latest version from play store at runtime
                        val urlOfAppFromPlayStore =
                            applicationContext.getString(R.string.app_url) + applicationContext
                                .packageName
                        val doc = Jsoup.connect(urlOfAppFromPlayStore).timeout(30000).get()
                        if (doc != null) {
                            val element = doc.getElementsContainingOwnText("Current Version")
                            for (ele in element) {
                                if (ele.siblingElements() != null) {
                                    val siblingElements = ele.siblingElements()
                                    for (sibElement in siblingElements) {
                                        return@async sibElement.text()
                                    }
                                }
                            }
                        } else {
                            return@async ""
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }.await()

                // View Update prompt if the installed version is not the latest published one
                if (currentVersion != latestVersion) {
                    val mDialog = Dialog(this@MainActivity)
                    mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
                    mDialog.setContentView(R.layout.custom_alert_dialog)
                    mDialog.setCancelable(false)
                    val titleTextView = mDialog.findViewById<TextView>(R.id.title_text_view)
                    val messageTextView = mDialog.findViewById<TextView>(R.id.message_text_view)
                    val applyButton = mDialog.findViewById<Button>(R.id.apply_button)
                    val cancelButton = mDialog.findViewById<Button>(R.id.cancel_button)
                    titleTextView.text = getString(R.string.update_alert_title)
                    messageTextView.text = getString(R.string.update_alert_message)
                    applyButton.text = getString(R.string.update_alert_update_button)
                    cancelButton.text = getString(R.string.alert_later_button)
                    applyButton.setOnClickListener {
                        startActivity(
                            Intent(
                                Intent.ACTION_VIEW, Uri.parse(
                                    applicationContext.getString(R.string.app_market_url) + applicationContext.packageName
                                )
                            )
                        )
                        mDialog.dismiss()
                    }
                    cancelButton.setOnClickListener { mDialog.dismiss() }
                    mDialog.show()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}