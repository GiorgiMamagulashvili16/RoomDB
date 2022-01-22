package com.example.roomdatabaselecture.ui.projects_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdatabaselecture.R
import com.example.roomdatabaselecture.databinding.ProjectFragmentBinding

class ProjectsFragment : Fragment() {

    private var _binding: ProjectFragmentBinding? = null
    private val binding get() = _binding!!

    private val projectViewModel: ProjectsViewModel by viewModels()
    private val projectAdapter by lazy { ProjectAdapter() }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ProjectFragmentBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        projectViewModel.setAllProject()
        observeAllProjects()
        setListeners()
    }

    private fun setListeners() {
        binding.addProjectButton.setOnClickListener {
            findNavController().navigate(R.id.action_projectsFragment_to_addProjectFragment)
        }
    }

    private fun observeAllProjects() {
        projectViewModel.allProjectData.observe(viewLifecycleOwner, {
            initRecyclerView()
            projectAdapter.submitList(it)
        })
    }

    private fun initRecyclerView() {
        with(binding.projectRecyclerView) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = projectAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}