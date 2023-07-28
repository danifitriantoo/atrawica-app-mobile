package org.catrawi.atrawica.views

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialog
import org.catrawi.atrawica.MainActivity
import org.catrawi.atrawica.databinding.ActivityProfileBinding
import org.catrawi.atrawica.databinding.ModalSheetBinding
import org.catrawi.atrawica.models.User
import org.catrawi.atrawica.services.api.ApiService
import org.catrawi.atrawica.services.api.SessionManager
import org.catrawi.atrawica.viewmodels.ProfileViewModel
import org.catrawi.atrawica.viewmodels.factory.ProfileViewModelFatory
import org.catrawi.atrawica.viewmodels.repository.ProfileRespository


class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private lateinit var viewModel: ProfileViewModel
    private val apiService = ApiService.getService()
    private lateinit var modalSheetBinding: ModalSheetBinding
    private lateinit var bottomSheetDialog: BottomSheetDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProfileBinding.inflate(layoutInflater)
        modalSheetBinding = ModalSheetBinding.inflate(layoutInflater)
        bottomSheetDialog = BottomSheetDialog(this)

        setContentView(binding.root)

        viewModel = ViewModelProvider(this,
            ProfileViewModelFatory(
                ProfileRespository(apiService)
            )
        ).get(ProfileViewModel::class.java)


        binding.btnChange.setOnClickListener {
            showModalSheet(1,"Update Budget","Yakin untuk merubah nominal budget kamu?","Update")
        }

        binding.logoutButton.setOnClickListener {
            showModalSheet(2,"Logout","Yakin untuk keluar dari akun kamu?","Okay")
        }

        binding.headNameTextView.text = SessionManager.getPayloadData(this).name
        binding.nameTextView.text = SessionManager.getPayloadData(this).name
        binding.phoneTextView.text = SessionManager.getPayloadData(this).noHp
        binding.emailTextView.text = SessionManager.getPayloadData(this).email

    }

    private fun showModalSheet(index:Int,title:String,content:String,textButton: String) {
        bottomSheetDialog.setContentView(modalSheetBinding.root)

        modalSheetBinding.tvTitle.text = title
        modalSheetBinding.tvContent.text = content
        modalSheetBinding.btnConfirm.text = textButton

        modalSheetBinding.btnConfirm.setOnClickListener {

            if(index!=1) {
                val intent = Intent(this,LoginActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                SessionManager.clearData(this)
                startActivity(intent)
            } else {
                val user = User(
                    SessionManager.getPayload(this).id,
                    SessionManager.getPayload(this).name.toString(),
                    SessionManager.getPayload(this).email.toString(),
                    SessionManager.getPayload(this).noHp.toString(),
                    "keypass",
                    binding.etBudget.text.toString().toInt()
                )

                viewModel.updateBudget(SessionManager.getToken(this)!!,user)
                SessionManager.updateBudget(this,binding.etBudget.text.toString().toInt())
                val intent = Intent(this,MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                startActivity(intent)

                bottomSheetDialog.dismiss()
            }
        }

        bottomSheetDialog.show()
    }
}