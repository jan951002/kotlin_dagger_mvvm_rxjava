package co.kubo.dagger_kotlin_application.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import butterknife.ButterKnife
import butterknife.Unbinder
import dagger.android.support.DaggerFragment


abstract class BaseFragment : DaggerFragment() {
    private var unBinder: Unbinder? = null
    private var baseActivity: AppCompatActivity? = null
        get() = field

    @LayoutRes
    protected abstract fun layoutRes(): Int

    override fun onCreateView(
        @NonNull inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(layoutRes(), container, false)
        unBinder = ButterKnife.bind(this, view)
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        baseActivity = context as AppCompatActivity?
    }


    override fun onDestroy() {
        super.onDestroy()
        if (unBinder != null) {
            unBinder!!.unbind()
            unBinder = null
        }
    }

}