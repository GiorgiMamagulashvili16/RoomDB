package com.example.roomdatabaselecture.ui.projects_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdatabaselecture.R
import com.example.roomdatabaselecture.data.model.Project
import com.example.roomdatabaselecture.data.utils.Constants.PROJECT_BUNDLE_KEY
import com.example.roomdatabaselecture.data.utils.Constants.PROJECT_REQUEST_KEY
import com.example.roomdatabaselecture.databinding.ProjectFragmentBinding
import com.example.roomdatabaselecture.ui.projects_screen.adapter.OnProjectItemClick
import com.example.roomdatabaselecture.ui.projects_screen.adapter.ProjectAdapter
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ProjectsFragment : Fragment(), OnProjectItemClick {

    private var _binding: ProjectFragmentBinding? = null
    private val binding get() = _binding!!

    private val projectViewModel: ProjectsViewModel by viewModels()
    private val projectAdapter by lazy { ProjectAdapter(this) }
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
        initRecyclerView()
    }

    private fun setListeners() {
        binding.addProjectButton.setOnClickListener {
            findNavController().navigate(R.id.action_projectsFragment_to_addProjectFragment)
        }
    }

    private fun observeAllProjects() {
        projectViewModel.allProjectData.observe(viewLifecycleOwner, {
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

    override fun onProjectClick(projectId: Int) {
       ProjectsFragmentDirections.actionProjectsFragmentToProjectDetailFragment(projectId).also {
           findNavController().navigate(it)
       }
    }
}