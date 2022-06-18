package com.wentao.githubapp.ui.profile

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.wentao.githubapp.databinding.FragmentProfileBinding
import com.wentao.githubapp.network.NetworkManager
import com.wentao.githubapp.network.Resource
import com.wentao.githubapp.ui.BaseFragment
import com.wentao.githubapp.ui.profile.model.UserRepository


class ProfileFragment : BaseFragment() {

    private var _binding: FragmentProfileBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var profileViewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        profileViewModel =
            ViewModelProvider(this).get(ProfileViewModel::class.java)

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.buttonLogin.setOnClickListener {
            if (!profileViewModel.isLogin) {
                val userNameTemp: String = binding.inputUserName.text.toString()
                val userPassTemp: String = binding.inputUserPass.text.toString()
                showUserProfile(userNameTemp, userPassTemp)
            } else {
                profileViewModel.isLogin = false
                binding.inputUserPass.visibility = View.VISIBLE
                binding.inputUserName.visibility = View.VISIBLE
                binding.textUserName.visibility = View.VISIBLE
                binding.textUserPass.visibility = View.VISIBLE
                binding.buttonLogin.text = "Login"
            }

        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showUserProfile(name: String, pass: String) {
        if (profileViewModel.checkUser(name, pass)) {
            profileViewModel.user.observe(viewLifecycleOwner) {
                if (it.status == Resource.Status.SUCCESS) {
                    binding.inputUserPass.visibility = View.GONE
                    binding.inputUserPass.text.clear()
                    binding.inputUserName.visibility = View.GONE
                    binding.inputUserName.text.clear()
                    binding.textUserName.visibility = View.GONE
                    binding.textUserPass.visibility = View.GONE
                    binding.buttonLogin.text = "Login Out"
                    profileViewModel.isLogin = true
                } else {
                    showErrorDialog()
                }
            }
        } else {
            // 显示错误Dialog
            showErrorDialog()
        }
    }

    private fun showErrorDialog() {
        context?.let {
            MaterialAlertDialogBuilder(it)
                .setTitle("提示")
                .setMessage("账号密码错误，请重新输入")
                .setPositiveButton("好的", /* listener = */ null)
                .show();
        }

    }
}