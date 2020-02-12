package co.kubo.dagger_kotlin_application.ui.dogs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import co.kubo.dagger_kotlin_application.R
import co.kubo.dagger_kotlin_application.R2
import co.kubo.dagger_kotlin_application.data.model.Dog


class DogsAdapter(dogsViewModel: DogsViewModel, lifecycleOwner: LifecycleOwner) :
    RecyclerView.Adapter<DogsAdapter.ViewHolder>() {

    private val data = ArrayList<Dog>()

    init {
        dogsViewModel.getDogs().observe(lifecycleOwner, Observer { dogs ->
            run {
                data.clear()
                if (dogs != null) {
                    data.addAll(dogs)
                    notifyDataSetChanged()
                }
            }
        })
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_dog, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @BindView(R.id.txtName)
        @Nullable
        lateinit var txtName: TextView
        @BindView(R.id.txtYears)
        @Nullable
        lateinit var txtYears: TextView

        init {
            ButterKnife.bind(this, itemView)
        }

        fun bind(dog: Dog) {
            txtName.text = dog.name
            txtYears.text = dog.years
        }
    }
}