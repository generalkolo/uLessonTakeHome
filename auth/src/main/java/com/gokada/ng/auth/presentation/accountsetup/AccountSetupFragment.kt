package com.gokada.ng.auth.presentation.accountsetup

import android.Manifest
import android.app.Activity.RESULT_CANCELED
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.provider.MediaStore.ACTION_IMAGE_CAPTURE
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.gokada.core.base.BaseFragment
import com.gokada.core.navigation.GokadaSuperAppNavigator
import com.gokada.core.utils.encodeBitmapIntoBase64
import com.gokada.core.utils.getFileName
import com.gokada.ng.auth.BR
import com.gokada.ng.auth.R
import com.gokada.ng.auth.databinding.FragmentAccountSetupBinding
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import util.Utility.Companion.Constants.CAMERA_REQUEST_CODE
import util.Utility.Companion.Constants.GALLERY_REQUEST_CODE
import javax.inject.Inject

class AccountSetupFragment : BaseFragment<FragmentAccountSetupBinding, AccountSetupViewModel>() {
    @Inject
    lateinit var gokadaSuperAppNavigator: GokadaSuperAppNavigator

    private lateinit var binding: FragmentAccountSetupBinding
    private var disposable = CompositeDisposable()
    private lateinit var galleryPermissionDisposable: Disposable
    private lateinit var rxPermissions: RxPermissions

    override fun getLayoutBinding(binding: FragmentAccountSetupBinding) {
        this.binding = binding
    }

    @Inject
    lateinit var accountSetupViewModel: AccountSetupViewModel

    override fun getViewModel() = accountSetupViewModel

    override fun getLayoutId() = R.layout.fragment_account_setup

    override fun getBindingVariable() = BR.viewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        rxPermissions = RxPermissions(this)
        setUIElements()
        setActionBarTitle()
        setObservers()
    }

    private fun setUIElements() {
        val arguments = AccountSetupFragmentArgs.fromBundle(arguments!!)
        arguments.apply {
            accountSetupViewModel.setPhoneNumber(phoneNumber)
        }
    }

    private fun setActionBarTitle() {
        (activity as AppCompatActivity).supportActionBar?.title =
            getString(R.string.set_up_your_account_text)
    }

    private fun setObservers() {
        accountSetupViewModel.showImageSelector.observe(this, Observer { showImageSelector ->
            showImageSelector?.let {
                val chooseDisplayImageDialog = AlertDialog.Builder(activity!!)
                chooseDisplayImageDialog.setTitle("Choose your profile picture")
                val chooseDisplayImageDialogItems =
                    arrayOf("Take Photo", "Choose from Gallery", "Cancel")
                chooseDisplayImageDialog.setItems(
                    chooseDisplayImageDialogItems
                ) { dialog, which ->
                    when (which) {
                        0 -> takePhotoFromCamera()
                        1 -> choosePhotoFromGallery()
                        3 -> dialog.dismiss()
                    }
                }
                chooseDisplayImageDialog.show()
                accountSetupViewModel.imageSelectorShown()
            }
        })
    }

    private fun takePhotoFromCamera() {
        galleryPermissionDisposable =
            rxPermissions.request(Manifest.permission.CAMERA)
                .subscribe { granted ->
                    if (granted!!) {
                        val takePicture =
                            Intent(ACTION_IMAGE_CAPTURE)
                        startActivityForResult(takePicture, CAMERA_REQUEST_CODE)
                    } else {
                        Toast.makeText(
                            context,
                            "Permission Not Granted",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        disposable.add(galleryPermissionDisposable)
    }

    private fun choosePhotoFromGallery() {
        galleryPermissionDisposable =
            rxPermissions.request(Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe { granted ->
                    if (granted!!) {
                        val pickPhoto = Intent(
                            Intent.ACTION_PICK,
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                        )
                        startActivityForResult(pickPhoto, GALLERY_REQUEST_CODE)
                    } else {
                        Toast.makeText(
                            context,
                            "Permission Not Granted",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        disposable.add(galleryPermissionDisposable)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            GALLERY_REQUEST_CODE -> {
                if (data != null) {
                    val contentURI = data.data
                    val bitmap =
                        MediaStore.Images.Media.getBitmap(activity?.contentResolver, contentURI)
                    binding.profileImage.setImageBitmap(bitmap)
                    setBase64ImageValue(bitmap, contentURI)
                }
            }
            CAMERA_REQUEST_CODE -> {
                val contentURI = data!!.data
                val thumbnail = data.extras!!.get("data") as Bitmap
                binding.profileImage.setImageBitmap(thumbnail)
                setBase64ImageValue(thumbnail, contentURI)
            }
            RESULT_CANCELED -> {
                showSnackBar(binding.root, "Process Cancelled", true)
            }
        }
    }

    private fun setBase64ImageValue(
        thumbnail: Bitmap,
        contentURI: Uri?
    ) {
        accountSetupViewModel.setBaseImageInBase64(
            thumbnail.encodeBitmapIntoBase64(
                Bitmap.CompressFormat.PNG,
                10
            ),
            contentURI!!.getFileName(activity!!)
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}
