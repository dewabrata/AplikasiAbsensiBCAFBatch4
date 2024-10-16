package com.juaracoding.aplikasiabsensi

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.BuildCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.juaracoding.aplikasiabsensi.viewmodel.PengajuanCreditViewModel
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.Request
import okhttp3.RequestBody
import java.io.File
import java.io.FileOutputStream

class UploadAllDocument : AppCompatActivity() {

    lateinit var txtUsername : EditText
    lateinit var txtTanggal : EditText
    lateinit var btnKTP : Button
    lateinit var btnKK : Button
    lateinit var btnNPWP : Button
    lateinit var btnSendData : Button
    lateinit var imgKtp : ImageView
    lateinit var imgKK : ImageView
    lateinit var imgNPWP : ImageView
    var imageUriKK : Bitmap? =null
    var imageUriKTP :Bitmap? =null
    var imageUriNPWP :Bitmap? =null

    //inisiasi PengajuanCreditViewModel
    private val viewModel: PengajuanCreditViewModel by viewModels()


    fun initComponent(){
        txtUsername = findViewById(R.id.txtUsernameCredit)
        txtTanggal = findViewById(R.id.txtTglCredit)
        btnKTP = findViewById(R.id.btnKTP)
        btnKK = findViewById(R.id.btnKK)
        btnNPWP = findViewById(R.id.btnNPWP)
        btnSendData = findViewById(R.id.btnSendData)
        imgKtp = findViewById(R.id.imgKTP)
        imgKK = findViewById(R.id.imgKK)
        imgNPWP = findViewById(R.id.imgNPWP)

        txtUsername.setText(viewModel.getSharePreferencesLogin("username").toString())
        btnKTP.setOnClickListener{
            selectImageKTP()
        }

        btnKK.setOnClickListener{
            selectImageKK()
        }
        btnNPWP.setOnClickListener{
            selectImageNPWP()
        }

        btnSendData.setOnClickListener{
            sendData()
        }

    }

    fun selectImageKTP(){
        val implicitIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        resultLauncherKtp.launch(implicitIntent)

    }

    private val resultLauncherKtp = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            imageUriKTP = result.data?.extras?.get("data") as Bitmap
            imgKtp.setImageBitmap(imageUriKTP)
        }

    }

    fun selectImageKK(){
        val implicitIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        resultLauncherKK.launch(implicitIntent)
    }

    private val resultLauncherKK = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            imageUriKK = result.data?.extras?.get("data") as Bitmap
            imgKK.setImageBitmap(imageUriKK)
        }

    }

    fun selectImageNPWP(){
        val implicitIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        resultLauncherNPWP.launch(implicitIntent)
    }

    private val resultLauncherNPWP = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            imageUriNPWP = result.data?.extras?.get("data") as Bitmap
            imgNPWP.setImageBitmap(imageUriNPWP)
        }

    }

    fun sendData(){

        val username = txtUsername.text.toString()
        val tanggal = txtTanggal.text.toString()

        if (username.isNotEmpty() && tanggal.isNotEmpty() && imageUriKTP != null && imageUriKK!=null && imageUriNPWP != null){

            val fileKTP = createFile(imageUriKTP!!,"ktp.png")
            val fileKK = createFile(imageUriKK!!,"kk.png")
            val fileNPWP = createFile(imageUriNPWP!!,"npwp.png")

            val rbKtp =   MultipartBody.Part.createFormData("foto_ktp", fileKTP.getName(), RequestBody.create("image/jpg".toMediaType(), fileKTP))

            val rbKK = MultipartBody.Part.createFormData("foto_kk", fileKTP.getName(), RequestBody.create("image/jpg".toMediaType(), fileKK))
            val rbNPWP = MultipartBody.Part.createFormData("foto_npwp", fileKTP.getName(), RequestBody.create("image/jpg".toMediaType(), fileNPWP))
            val rbUsername = RequestBody.create("text/plain".toMediaTypeOrNull(),username)
            val rbTanggal = RequestBody.create("text/plain".toMediaTypeOrNull(),tanggal)


            viewModel.postDataCredit(rbUsername,rbKK,rbKtp,rbNPWP,rbTanggal)




        }else{
            Toast.makeText(this, "Data belum lengkap", Toast.LENGTH_SHORT).show()
        }

    }

    fun createFile(bitmap: Bitmap, tempName :String):File{
        val file = File(cacheDir,tempName)
        val fos = FileOutputStream(file)
        bitmap.compress(Bitmap.CompressFormat.PNG,100,fos)
        fos.flush()
        fos.close()
        return file
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_upload_all_document)
        initComponent()


        viewModel.post.observe(this){
           if (it.status == true){
               finish()
           }
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}