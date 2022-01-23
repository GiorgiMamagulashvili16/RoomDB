package com.example.roomdatabaselecture.ui.add_task_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.roomdatabaselecture.databinding.AddTaskFragmentBinding

class AddTaskFragment : Fragment() {

    private var _binding: AddTaskFragmentBinding? = null
    private val binding get() = _binding!!

    private val args: AddTaskFragmentArgs by navArgs()
    private val addTaskViewModel: AddTaskViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = AddTaskFragmentBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addTaskViewModel.setProjectId(args.projectId)
        observeErrorLiveData()
        observeSuccessLiveData()
        setListeners()
    }

    private fun setListeners() {
        binding.addTaskButton.setOnClickListener {
            addTask()
        }
    }

    private fun addTask() {
        addTaskViewModel.insertTask(binding.taskEditText.text.toString())
    }

    private fun observeSuccessLiveData() {
        addTaskViewModel.successLiveData.observe(viewLifecycleOwner, {
            findNavController().navigateUp()
        })
    }

    private fun observeErrorLiveData() {
        addTaskViewModel.errorLiveData.observe(viewLifecycleOwner, { errorText ->
            Toast.makeText(requireContext(), errorText, Toast.LENGTH_SHORT).show()
        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}