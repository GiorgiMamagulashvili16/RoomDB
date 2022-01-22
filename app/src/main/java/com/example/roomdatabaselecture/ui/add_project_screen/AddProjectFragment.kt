package com.example.roomdatabaselecture.ui.add_project_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.roomdatabaselecture.R
import com.example.roomdatabaselecture.databinding.AddProjectFragmentBinding

class AddProjectFragment : Fragment() {

    private var _binding: AddProjectFragmentBinding? = null
    private val binding get() = _binding!!
    private val addProjectViewModel: AddProjectViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = AddProjectFragmentBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        observeErrorLiveData()
        observeSuccessLiveData()
    }

    private fun observeSuccessLiveData() {
        addProjectViewModel.successLiveData.observe(viewLifecycleOwner, {
            findNavController().navigate(R.id.action_addProjectFragment_to_projectsFragment)
        })
    }

    private fun observeErrorLiveData() {
        addProjectViewModel.errorLiveData.observe(viewLifecycleOwner, { errorText ->
            Toast.makeText(requireContext(), errorText, Toast.LENGTH_SHORT).show()
        })
    }

    private fun setListeners() {
        binding.addProjectButton.setOnClickListener {
            addProject()
        }
    }

    private fun addProject() {
        with(binding) {
            addProjectViewModel.addProject(
                title = titleEditText.text.toString(),
                description = descEditText.text.toString()
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}