package com.rana.myweatherapp.presentation.weather

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.make
import com.rana.myweatherapp.R
import com.rana.myweatherapp.databinding.FragmentWeatherBinding
import com.rana.myweatherapp.presentation.details.DetailsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherFragment : Fragment() {

    private val weatherViewModel: WeatherViewModel by viewModels()

    lateinit var binding: FragmentWeatherBinding

    private var listAdapter = WeatherListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        binding = FragmentWeatherBinding.inflate(inflater, container, false)

        binding.weatherRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = listAdapter
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        weatherViewModel.viewState.observe(viewLifecycleOwner, Observer { state ->

            if (state.error != null) {
                make(binding.root, R.string.something_went_wrong, Snackbar.LENGTH_SHORT)
                    .show()
            } else {
                listAdapter.setItems(state.data)
                listAdapter.clickListener = {
                    findNavController().navigate(
                        R.id.DetailsFragment,
                        DetailsFragment.newInstanceBundle(it)
                    )
                }
            }
        })
    }

    override fun onStart() {
        super.onStart()
        weatherViewModel.showWeather("25.20", "55.27")
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.settings -> {
                findNavController().navigate(R.id.settingsFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
