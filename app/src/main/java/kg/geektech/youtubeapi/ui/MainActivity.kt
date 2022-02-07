package kg.geektech.youtubeapi.ui

import android.app.ActionBar
import android.app.Dialog
import android.content.Intent

import android.util.Log
import android.view.LayoutInflater

import androidx.lifecycle.ViewModelProvider
import kg.geektech.youtubeapi.R
import kg.geektech.youtubeapi.adapters.PlaylistAdapter
import kg.geektech.youtubeapi.base.BaseActivity
import kg.geektech.youtubeapi.data.model.Playist
import kg.geektech.youtubeapi.databinding.ActivityMainBinding
import android.view.Window
import android.widget.LinearLayout
import com.google.android.material.button.MaterialButton


class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    override val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun checkInternet() {
        if (isNetworkAvailable()) {
            initViewModel()
        } else {
            val noInternetDialog = Dialog(this, android.R.style.Theme)
            noInternetDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            noInternetDialog.setContentView(R.layout.internet_connection)
            val window = noInternetDialog.window
            window?.setLayout(ActionBar.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
            val tryBtn = noInternetDialog.findViewById<MaterialButton>(R.id.tryAgain)
            tryBtn.setOnClickListener {
                if (isNetworkAvailable()) {
                    initViewModel()
                    noInternetDialog.dismiss()
                } else {
                    checkInternet()
                }
            }
            noInternetDialog.show()
        }
    }



    override fun initViewModel() {
        super.initViewModel()
        viewModel.playlists().observe(this) {
//            Toast.makeText(this, it..toString(), Toast.LENGTH_SHORT).show()
            initRV(it)
            Log.e("TAG", "initViewModel: $it")
        }
    }

    private fun initRV(model: Playist?) {
        binding.rvMain.adapter =
            PlaylistAdapter(model!!.items, object : PlaylistAdapter.OnItemClickListener {
                override fun onItemClick(id: String) {
                    Intent(this@MainActivity, SecondActivity::class.java).apply {
                     putExtra("channelId",id)
                     startActivity(this)
                    }
                }

            })
    }

    override fun inflateViewBinding(inflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }
}