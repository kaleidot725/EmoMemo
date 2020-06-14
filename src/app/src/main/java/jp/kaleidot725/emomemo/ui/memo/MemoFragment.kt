package jp.kaleidot725.emomemo.ui.memo

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import jp.kaleidot725.emomemo.R
import jp.kaleidot725.emomemo.databinding.FragmentMemoBinding
import jp.kaleidot725.emomemo.extension.viewBinding
import jp.kaleidot725.emomemo.ui.core.MessageItemRecyclerViewController
import kotlinx.android.synthetic.main.fragment_home.recycler_view
import kotlinx.android.synthetic.main.fragment_memo.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MemoFragment : Fragment(R.layout.fragment_memo) {
    private val viewModel: MemoViewModel by viewModel()
    private val binding: FragmentMemoBinding by viewBinding()
    private val args: MemoFragmentArgs by navArgs()
    private val messageItemRecyclerViewController = MessageItemRecyclerViewController()
    private val navController: NavController get() = findNavController()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel

        recycler_view.apply {
            this.adapter = messageItemRecyclerViewController.adapter
            this.layoutManager = LinearLayoutManager(context).apply {
                orientation = LinearLayoutManager.VERTICAL
            }
        }

        voice_button.setOnClickListener {
            navController.navigate(R.id.action_memoFragment_to_audioRecognizerFragment)
        }
        
        viewModel.messageList.observe(viewLifecycleOwner, Observer {
            messageItemRecyclerViewController.setData(it, true)
        })

        viewModel.memoId = args.memoId.toInt()

        viewModel.fetch()
    }
}
