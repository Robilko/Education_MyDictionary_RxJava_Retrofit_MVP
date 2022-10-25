package com.example.mydictionary.view.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.example.model.data.AppState
import com.example.core.BaseActivity
import com.example.mydictionary.*
import com.example.mydictionary.databinding.ActivityMainBinding
import com.example.model.data.DataModel
import com.example.utils.network.isOnline
import com.example.mydictionary.view.descriptionscreen.DescriptionActivity
import com.example.historyscreen.HistoryActivity
import com.example.mydictionary.utils.convertMeaningsTranscriptionToString
import com.example.mydictionary.utils.convertMeaningsTranslationToString
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val BOTTOM_SHEET_FRAGMENT_DIALOG_TAG = "74a54328-5d62-46bf-ab6b-cbf5fgt0-092395"

class MainActivity : BaseActivity<AppState, MainInteractor>() {

    private lateinit var binding: ActivityMainBinding

    /**Создаём модель*/
    override lateinit var model: MainViewModel

    /** Передаем в адаптер ссылку на функцию высшего порядка ::onItemClick */
    private val adapter: MainAdapter by lazy { MainAdapter(::onItemClick) }
    private val fabClickListener: View.OnClickListener =
        View.OnClickListener {
            showNewSearchDialogFragment(onSearchClickListener)
        }

    /**Функция высшего порядка. Передается в адаптер. Запускает новый экран*/
    private fun onItemClick(data: DataModel) {
        startActivity(
            DescriptionActivity.getIntent(
                this@MainActivity,
                data.text!!,
                convertMeaningsTranscriptionToString(data.meanings!!),
                convertMeaningsTranslationToString(data.meanings!!),
                data.meanings!![0].imageUrl
            )
        )
    }

    private fun showNewSearchDialogFragment(onSearchClickListener: SearchDialogFragment.OnSearchClickListener) {
        val searchDialogFragment = SearchDialogFragment.newInstance()
        searchDialogFragment.setOnSearchClickListener(onSearchClickListener)
        searchDialogFragment.show(supportFragmentManager, BOTTOM_SHEET_FRAGMENT_DIALOG_TAG)
    }

    private val onSearchClickListener: SearchDialogFragment.OnSearchClickListener =
        object : SearchDialogFragment.OnSearchClickListener {
            override fun onClick(searchWord: String) {
                isNetworkAvailable = isOnline(applicationContext)
                if (isNetworkAvailable) {
                    model.getData(searchWord, isNetworkAvailable)
                } else {
                    showNoInternetConnectionDialog()
                }
            }
        }

    private val onHistorySearchClickListener: SearchDialogFragment.OnSearchClickListener =
        object : SearchDialogFragment.OnSearchClickListener {
            override fun onClick(searchWord: String) {
                CoroutineScope(
                    Dispatchers.Default
                            + SupervisorJob()
                ).launch {
                    model.getDataByWord(searchWord)?.let { data ->
                        startActivity(
                            DescriptionActivity.getIntent(
                                this@MainActivity,
                                data.text!!,
                                convertMeaningsTranscriptionToString(data.meanings!!),
                                convertMeaningsTranslationToString(data.meanings!!),
                                data.meanings!![0].imageUrl
                            )
                        )
                    } ?: model.handleError(Throwable("$searchWord ${getString(R.string.history_search_word_error)}"))
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        /**Инициализация ViewModel*/
        initViewModel()
        /** инициализаця элементов экрана*/
        initViews()
    }


    override fun setDataToAdapter(data: List<DataModel>) {
        adapter.setData(data)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.history_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_history -> {
                startActivity(Intent(this, HistoryActivity::class.java))
                true
            }
            R.id.menu_search_in_history -> {
                showNewSearchDialogFragment(onHistorySearchClickListener)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initViewModel() {
        /**Убедимся, что модель инициализируется раньше View*/
        if (binding.mainActivityRecyclerview.adapter != null) {
            throw IllegalStateException("The ViewModel should be initialised first")
        }
        /** Теперь ViewModel инициализируется через функцию by viewModel()
         * Это функция, предоставляемая Koin из коробки через зависимость
         * import org.koin.androidx.viewmodel.ext.android.viewModel*/
        val viewModel: MainViewModel by viewModel()
        model = viewModel
        model.subscribe()
            .observe(this@MainActivity, { renderData(it) }) //Observer<AppState> { renderData(it) }
    }

    private fun initViews() {
        binding.searchFab.setOnClickListener(fabClickListener)
        binding.mainActivityRecyclerview.adapter = adapter
    }

}