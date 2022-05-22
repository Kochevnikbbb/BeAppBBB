package kg.geektech.beappbbb.ui.home

import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import kg.geektech.beappbbb.MainActivity
import kg.geektech.beappbbb.base.BaseNavFragment
import kg.geektech.beappbbb.base.BaseViewModel
import kg.geektech.beappbbb.databinding.FragmentHomeBinding

class HomeFragment : BaseNavFragment<FragmentHomeBinding, BaseViewModel>() {

    override val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this)[HomeViewModel::class.java]
    }

    override fun inflateViewBinding(inflater: LayoutInflater): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater)
    }

    override fun initView() {
        binding.toolbar.setNavigationOnClickListener {
            (requireActivity() as MainActivity).openDrawer()
        }
    }

    override fun initListener() {

    }

}