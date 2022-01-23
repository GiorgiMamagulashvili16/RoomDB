package com.example.roomdatabaselecture.ui.project_detail_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdatabaselecture.data.model.Project
import com.example.roomdatabaselecture.databinding.ProjectDetailFragmentBinding
import com.example.roomdatabaselecture.ui.project_detail_screen.adapter.TaskAdapter

class ProjectDetailFragment : Fragment() {

    private var _binding: ProjectDetailFragmentBinding? = null
    private val binding get() = _binding!!
    private val projectDetailViewModel: ProjectDetailViewModel by viewModels()
    private val args: ProjectDetailFragmentArgs by navArgs()
    private val taskAdapter: TaskAdapter by lazy { TaskAdapter() }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ProjectDetailFragmentBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setProjectArgs()
        observeProjectId()
        observeProject()
        setListeners()
        observeTasks()
        initTaskRecycler()
    }

    private fun observeTasks() {
        projectDetailViewModel.tasksLiveData.observe(viewLifecycleOwner, {
            taskAdapter.submitList(it)
        })
    }

    private fun setProjectArgs() {
        projectDetailViewModel.setProjectId(args.projectid)
    }

    private fun observeProjectId() {
        projectDetailViewModel.projectIdLiveData.observe(viewLifecycleOwner, {
            projectDetailViewModel.setProject(it)
            projectDetailViewModel.setTasks(it)
        })
    }

    private fun observeProject() {
        projectDetailViewModel.projectLiveData.observe(viewLifecycleOwner, {
            setProjectData(it)
        })
    }

    private fun setProjectData(project: Project) {
        with(binding) {
            titleTextView.text = project.title
            descTextView.text = project.description
        }
    }

    private fun setListeners() {
        binding.addTaskButton.setOnClickListener {
            projectDetailViewModel.projectIdLiveData.value?.let { projectId ->
                ProjectDetailFragmentDirections.actionProjectDetailFragmentToAddTaskFragment(
                    projectId
                ).also {
                    findNavController().navigate(it)
                }
            }
        }
    }

    private fun initTaskRecycler() {
        with(binding.taskRecyclerView) {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = taskAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}