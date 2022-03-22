package kr.feliz.tutorial_collection.common
//
//import android.annotation.SuppressLint
//import android.os.Bundle
//import android.util.Log
//import android.view.View
//import androidx.core.content.ContextCompat
//import androidx.fragment.app.activityViewModels
//import androidx.lifecycle.Observer
//import androidx.recyclerview.widget.DividerItemDecoration
//import com.google.android.material.snackbar.Snackbar
//import com.lemonfox.exchange.R
//import com.lemonfox.exchange.common.BaseFragment
//import com.lemonfox.exchange.databinding.FragmentHomeCurrencySimpleListBinding
//import com.lemonfox.exchange.scene.market.adapter.CurrencyListAdapter
//import com.lemonfox.exchange.scene.market.item.CurrencyListItem
//import com.lemonfox.exchange.scene.market.viewmodel.CurrencyListViewModel
//
//
//class CurrencyListFragmentExample: BaseFragment<FragmentHomeCurrencySimpleListBinding>() {
//
//    override val resId = R.layout.fragment_home_currency_simple_list
//    private lateinit var binding: FragmentHomeCurrencySimpleListBinding
//
//    private val viewModel: CurrencyListViewModel by activityViewModels()
//    private lateinit var currencyListAdapter: CurrencyListAdapter
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        /** 전달받은 인스턴스를 호출하는 방법 */
//        // val someInt = requireArguments().getInt("some_int")
//        super.onViewCreated(view, savedInstanceState)
//
//        binding = getViewBinding()
//
//        val itemDecorator: DividerItemDecoration =
//            DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
//        itemDecorator.setDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.item_grayline)!!)
//
//        currencyListAdapter = CurrencyListAdapter(requireContext())
//        binding.currencySimpleListview.addItemDecoration(itemDecorator)
//        binding.currencySimpleListview.adapter = currencyListAdapter
//
//        viewModel.currencyListDatas.observe(viewLifecycleOwner, currencyObserver)
//        viewModel.request()
//    }
//
//    @SuppressLint("NotifyDataSetChanged")
//    val currencyObserver =
//        Observer<List<CurrencyListItem>> { data ->
//            // Log.d("orion","update log $data")
//            Snackbar.make(binding.root,"데이터 갱신", Snackbar.LENGTH_SHORT).show()
//            currencyListAdapter.datas = data
//            currencyListAdapter.notifyDataSetChanged()
//        }
//
//    override fun onResume() {
//        super.onResume()
//        Log.d("orion","fragment onResume")
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        Log.d("orion","fragment Destroyed")
//    }
//
//
//
//}