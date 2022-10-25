package com.example.historyscreen

import android.os.Bundle
import com.example.model.data.AppState
import com.example.core.BaseActivity
import com.example.historyscreen.databinding.ActivityHistoryBinding
import com.example.model.data.DataModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.IllegalStateException

class HistoryActivity : BaseActivity<AppState, HistoryInteractor>() {

    private lateinit var binding: ActivityHistoryBinding
    override lateinit var model: HistoryViewModel
    private val adapter: HistoryAdapter by lazy { HistoryAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewModel()
        initViews()
    }
    /** Сразу запрашиваем данные из локального репозитория */
    override fun onResume() {
        super.onResume()
        model.getData("", false)
    }

    /** Вызовется из базовой Activity, когда данные будут готовы */
    override fun setDataToAdapter(data: List<DataModel>) {
        adapter.setData(data)
    }

    private fun initViewModel() {
        if (binding.historyActivityRecyclerview.adapter != null) {
            throw IllegalStateException("The ViewModel should be initialised first")
        }
        val viewModel: HistoryViewModel by viewModel()
        model = viewModel
        model.subscribe().observe(this@HistoryActivity, { renderData(it) }) //Observer<AppState> { renderData(it) }
    }

    private fun initViews() {
        binding.historyActivityRecyclerview.adapter = adapter
    }
}