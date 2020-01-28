package co.kubo.dagger_kotlin_application.ui.dogs

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import co.kubo.dagger_kotlin_application.R
import co.kubo.dagger_kotlin_application.base.BaseFragment
import co.kubo.dagger_kotlin_application.factory.ViewModelFactory
import javax.inject.Inject


class DogsFragment : BaseFragment() {

    @BindView(R.id.txtError)
    lateinit var txtError: TextView
    @BindView(R.id.txtLoading)
    lateinit var txtLoading: TextView
    @BindView(R.id.txtSuccess)
    lateinit var txtSuccess: TextView
    @BindView(R.id.recyclerDogs)
    lateinit var recyclerDogs: RecyclerView

    var viewModelFactory: ViewModelFactory? = null
        @Inject set
    private lateinit var dogsViewModel: DogsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun layoutRes(): Int {
        return R.layout.fragment_dogs
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dogsViewModel = ViewModelProviders.of(this, viewModelFactory).get(DogsViewModel::class.java)
        recyclerDogs.layoutManager = LinearLayoutManager(activity!!)
        recyclerDogs.adapter = DogsAdapter(dogsViewModel, viewLifecycleOwner)
        observableViewModel()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
    }

    @SuppressLint("SetTextI18n")
    private fun observableViewModel() {

        dogsViewModel.getDogs().observe(viewLifecycleOwner, Observer { dogs ->
            run {
                if (dogs != null) {
                    recyclerDogs.visibility = View.VISIBLE
                    txtSuccess.visibility = View.VISIBLE
                    txtSuccess.text = "Success"
                } else {
                    txtSuccess.visibility = View.INVISIBLE
                    txtSuccess.text = null
                }
            }
        })

        dogsViewModel.getError().observe(viewLifecycleOwner, Observer { isError ->
            run {
                if (isError) {
                    recyclerDogs.visibility = View.GONE
                    txtError.visibility = View.VISIBLE
                    txtError.text = "Error"
                } else {
                    txtError.visibility = View.INVISIBLE
                    txtError.text = null
                }
            }
        })

        dogsViewModel.getLoading().observe(viewLifecycleOwner, Observer { isLoading ->
            run {
                if (isLoading) {
                    recyclerDogs.visibility = View.GONE
                    txtLoading.visibility = View.VISIBLE
                    txtLoading.text = "Loading..."
                } else {
                    txtLoading.visibility = View.INVISIBLE
                    txtLoading.text = null
                }
            }
        })

    }

}
