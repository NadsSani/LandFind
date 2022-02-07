package com.nads.landfind.ui.sell

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.os.FileUtils
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.MimeTypeMap
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.view.drawToBitmap
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.nads.landfind.Event
import com.nads.landfind.EventObserver
import com.nads.landfind.R
import com.nads.landfind.databinding.FragSellerBinding
import com.nads.landfind.util.setupSnackbar
import dagger.hilt.android.AndroidEntryPoint

import java.io.*
import kotlin.properties.Delegates



@AndroidEntryPoint
class Seller: Fragment() {
    private val sellerViewModel: SellerViewModel by navGraphViewModels(R.id.login_nav)
    { defaultViewModelProviderFactory }
    private lateinit var fragSellerBinding: FragSellerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
   var ids by Delegates.notNull<Int>()

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        fragSellerBinding = FragSellerBinding.inflate(inflater, container, false).apply {
             sellerviewmodel = sellerViewModel

        }
        sellerViewModel.id.observe(viewLifecycleOwner,EventObserver{
            ids = it
            initimagebutton(it)
        })
        setUpSubmit()
        setHasOptionsMenu(true)
        return fragSellerBinding.root
    }

    private fun setUpSubmit() {
        sellerViewModel.navigated.observe(viewLifecycleOwner,EventObserver{
            findNavController().navigateUp()
        })

    }


    private fun setupSnackbar() {
        view?.setupSnackbar(this, sellerViewModel.snackbarText, Snackbar.LENGTH_SHORT)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupSnackbar()
    }



@RequiresApi(Build.VERSION_CODES.M)
fun initimagebutton(id:Int){
            if (requireActivity().checkSelfPermission
                    (Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
                val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE);
            requestPermissions(permissions,PERMISSION_CODE);
            }
            else{
                //permission already granted
                pickImageFromGallery(id);
            }

    }



    private fun pickImageFromGallery(id:Int) {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
       ids = id
        startActivityForResult(intent,IMAGE_PICK_CODE)
    }
    companion object {
        //image pick code
        private val IMAGE_PICK_CODE = 1212;
        //Permission code
        private val PERMISSION_CODE = 1213;
    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode){
            PERMISSION_CODE -> {
                if (grantResults.size >0 && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED){
                    //permission from popup granted
                    sellerViewModel._id.value = Event(ids)
                }
                else{
                    //permission from popup denied
                    Toast.makeText(requireActivity(), "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE){
        val image = fragSellerBinding.root.findViewById<ImageView>(ids)
            if (image != null) {
                Glide.with(requireActivity()).load(data?.data).into(image)
                val bitmap = image.drawToBitmap()
                val bytearrystream = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, bytearrystream)
                val bb = bytearrystream.toByteArray()
                when(ids){
                    R.id.imgseller1 -> converToString("image",bb)
                    R.id.imgseller2 ->  converToString("image1",bb)
                    R.id.imgseller3 ->  converToString("image2",bb)
                }
               

            }
        }
    }

    fun converToString(image:String,bb:ByteArray){
        val proimage = Base64.encodeToString(bb, 0)
        when(image){
            "image" -> sellerViewModel.image.value = proimage
            "image1" -> sellerViewModel.image2.value = proimage
            "image2" -> sellerViewModel.image3.value = proimage
        }

    }





   //fun converToBitmap(filename:String,bitmapdata:ByteArray,image2:MutableLiveData<MultipartBody.Part>,data: Intent?){

//       val file: File = FileUtils.getFile(requireActivity(), data?.data)
//       val f = File(requireActivity().cacheDir, filename)
//       f.createNewFile()
//
//
//
//
//       var fos: FileOutputStream? = null
//       try {
//           fos = FileOutputStream(f)
//       } catch (e: FileNotFoundException) {
//           e.printStackTrace()
//       }
//       try {
//           fos?.write(bitmapdata)
//           fos?.flush()
//           fos?.close()
//       } catch (e: IOException) {
//           e.printStackTrace()
//       }
//
//       val requestFile: ResponseBody = create(
//           MediaType.parse(getContentResolver().getType(fileUri)),
//           bitmapdata
//       )
//
//       //val reqFile: RequestBody = RequestBody.create("image/png".toMediaTypeOrNull(), f)
//       val reqFile: RequestBody = f.asRequestBody("image/jpg".toMediaTypeOrNull())
//       val body: MultipartBody.Part = MultipartBody.Part.createFormData("upload", f.name, reqFile)
//       image2.value = body
//
//
//
//
////       val mimeType = getMimeType(f);
////       if (mimeType == null) {
////           Log.e("file error", "Not able to get mime type")
////       }
////       val requestBody: RequestBody =
////           MultipartBody.Builder().setType(MultipartBody.MIXED)
////               .addFormDataPart("upload", filename,f.asRequestBody(mimeType?.toMediaType()))
////               .build()
//     //  val body: MultipartBody.Part = MultipartBody.Part.createFormData("upload", f.name, requestBody)
//
//
//
//
//   }

}