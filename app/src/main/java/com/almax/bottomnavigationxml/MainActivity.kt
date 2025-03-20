package com.almax.bottomnavigationxml

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.updatePadding
import androidx.fragment.app.Fragment
import com.almax.bottomnavigationxml.databinding.ActivityMainBinding
import com.almax.bottomnavigationxml.first.FirstFragment
import com.almax.bottomnavigationxml.second.SecondFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUi()
    }

    private fun setupUi() {
        val bottomNavigationView = binding.navView
        val firstFragment = FirstFragment.newInstance()
        val secondFragment = SecondFragment.newInstance()

        bottomNavigationView.setOnApplyWindowInsetsListener { view, insets ->
            view.updatePadding(0, 0, 0, 0)
            insets
        }

        setCurrentFragment(firstFragment)

        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navFirst -> {
                    setCurrentFragment(firstFragment)
                }

                R.id.navSecond -> {
                    setCurrentFragment(secondFragment)
                }
            }
            true
        }
    }

    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainer, fragment)
                .commit()
        }
    }
}