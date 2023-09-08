package com.tibadev.alimansour.prophetstories.activities

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.ShareActionProvider
import androidx.core.view.MenuItemCompat
import com.google.android.gms.ads.AdView
import com.tibadev.alimansour.prophetstories.R
import com.tibadev.alimansour.prophetstories.util.Network
import com.tibadev.alimansour.prophetstories.util.getAdRequest

class StoryActivity : AppCompatActivity() {
    private lateinit var txtProphet: TextView
    private lateinit var txtContent: TextView
    private lateinit var bannerStory: AdView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_story)
        try {
            bannerStory = findViewById(R.id.banner_story)
            txtProphet = findViewById(R.id.txt_prophet)
            txtContent = findViewById(R.id.txt_content)
            bannerStory.loadAd(getAdRequest())
            intent.extras?.let {
                txtProphet.text = it.getString("prophet")
                txtContent.text = it.getString("content")
            }
        } catch (e: Exception) {
            Log.e(javaClass.simpleName, e.message.toString())
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_story, menu)
        val shareItem = menu.findItem(R.id.action_share)

        val mShareActionProvider =
            MenuItemCompat.getActionProvider(shareItem) as ShareActionProvider
        mShareActionProvider.setShareIntent(doShare())
        val rateItem = menu.findItem(R.id.action_rate)
        rateItem.setOnMenuItemClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            // Try Google play
            intent.data = Uri.parse("market://details?id=com.tibadev.alimansour.prophetstories")
            if (!myStartActivity(intent)) {
                // Market (Google play) app seems not installed, let's try to open a webbrowser
                intent.data =
                    Uri.parse("https://play.google.com/store/apps/details?com.tibadev.alimansour.prophetstories")
                if (!myStartActivity(intent)) {
                    // Well if this also fails, we have run out of options, inform the user.
                    Toast.makeText(
                        this@StoryActivity,
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
        intent.putExtra(Intent.EXTRA_SUBJECT, "قصص الأنبياء - " + txtProphet.text)
        intent.putExtra(
            Intent.EXTRA_TEXT, """
     قصص الأنبياء - أندرويد
     طيبة ديف للبرمجيات
     
     ${txtProphet.text}
     ${txtContent.text}
     """.trimIndent()
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
            R.id.action_size_up -> txtContent.setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                txtContent.textSize + 1
            )

            R.id.action_size_down -> txtContent.setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                txtContent.textSize - 1
            )

            R.id.action_about -> {
                val intent = Intent(this@StoryActivity, AboutActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}