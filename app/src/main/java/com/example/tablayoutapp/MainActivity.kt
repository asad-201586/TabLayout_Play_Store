package com.example.tablayoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.tablayoutapp.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var selectedTabGames: Int = 0
    private var selectedTabApps: Int = 0
    private var currentFragment: CurrentFragment = CurrentFragment.GAMES
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tabLayout.apply {
            addTab(newTab().setText("For You"))
            addTab(newTab().setText("Top Charts"))
            addTab(newTab().setText("Kids"))
            addTab(newTab().setText("Premium"))
            addTab(newTab().setText("Categories"))
            addOnTabSelectedListener(tabSelectedListener)

        }

        binding.clearButton.setOnClickListener {
            binding.tabLayout.removeAllTabs()
        }
        binding.tabGames.setOnClickListener {
            binding.tabLayout.removeAllTabs()
            currentFragment = CurrentFragment.GAMES
            Log.d("my_tab", "onCreate: selected tab games: $selectedTabGames")
            binding.tabLayout.apply {
                removeOnTabSelectedListener(tabSelectedListener)
                addTab(newTab().setText("For You"))
                addTab(newTab().setText("Top Charts"))
                addTab(newTab().setText("Kids"))
                addTab(newTab().setText("Premium"))
                addTab(newTab().setText("Categories"))
                addOnTabSelectedListener(tabSelectedListener)
                getTabAt(selectedTabGames)?.select()
            }
        }
        binding.tabApps.setOnClickListener {
            binding.tabLayout.removeAllTabs()
            currentFragment = CurrentFragment.APPS
            Log.d("my_tab", "onCreate: selected tab apps: $selectedTabApps")
            binding.tabLayout.apply {
                removeOnTabSelectedListener(tabSelectedListener)
                addTab(newTab().setText("For You"))
                addTab(newTab().setText("Top Charts"))
                addTab(newTab().setText("Kids"))
                addTab(newTab().setText("Categories"))
                addOnTabSelectedListener(tabSelectedListener)
                getTabAt(selectedTabApps)?.select()
            }
        }

    }


    val tabSelectedListener = object : OnTabSelectedListener {
        override fun onTabSelected(tab: TabLayout.Tab?) {
            Log.d("my_tab", "onTabSelected: tab selected listener: ${tab?.position}")
            if (currentFragment == CurrentFragment.GAMES)
                selectedTabGames = tab?.position?:0
            else
                selectedTabApps = tab?.position?:0
        }

        override fun onTabUnselected(tab: TabLayout.Tab?) {

        }

        override fun onTabReselected(tab: TabLayout.Tab?) {

        }
    }
}



enum class CurrentFragment {
    GAMES,APPS
}