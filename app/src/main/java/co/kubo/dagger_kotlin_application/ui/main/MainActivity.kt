package co.kubo.dagger_kotlin_application.ui.main

import android.os.Bundle
import co.kubo.dagger_kotlin_application.R
import co.kubo.dagger_kotlin_application.base.BaseActivity
import co.kubo.dagger_kotlin_application.ui.dogs.DogsFragment

class MainActivity : BaseActivity() {

    override fun layoutRes(): Int {
        return R.layout.activity_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*
        val dogsFragment = DogsFragment()
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.add(R.id.frameContent, dogsFragment)
        fragmentTransaction.commit()

        */
    }
}
