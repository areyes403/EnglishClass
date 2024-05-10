package mx.edu.itm.link.englishclass.call_feature.presentation.call_history

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import mx.edu.itm.link.englishclass.core.domain.model.ResponseStatus
import mx.edu.itm.link.englishclass.core.utils.snackBar
import mx.edu.itm.link.englishclass.databinding.FragmentHistoryCallsBinding

@AndroidEntryPoint
class HistoryCallsFragment : Fragment() {

    private var _binding:FragmentHistoryCallsBinding?=null
    private val binding get() = _binding!!

    private val model by viewModels<CallHistoryViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding=FragmentHistoryCallsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observers()

    }

    private fun observers() {
        model.realtimeCallHistory.onEach { response->
            when(response){
                is ResponseStatus.Loading->{

                }
                is ResponseStatus.Success->{
                    val adapter = CallsAdapter(response.data)
                    binding.rvCalls.layoutManager = LinearLayoutManager(requireContext())
                    binding.rvCalls.adapter = adapter
                }
                is ResponseStatus.Error->{
                    snackBar(response.error)
                }
            }

        }.launchIn(lifecycleScope)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

}

