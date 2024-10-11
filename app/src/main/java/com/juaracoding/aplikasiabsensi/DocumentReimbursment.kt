package com.juaracoding.aplikasiabsensi

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.poifs.filesystem.POIFSDocument
import org.apache.poi.poifs.filesystem.POIFSFileSystem
import org.apache.poi.xwpf.extractor.XWPFWordExtractor
import org.apache.poi.xwpf.usermodel.XWPFDocument
import java.io.InputStream
import java.util.Calendar

class DocumentReimbursment : AppCompatActivity() {
    lateinit var btnCaptureCamera : Button
    lateinit var txtUsername :EditText
    lateinit var fotoSurat :ImageView
    lateinit var txtTanggalReimbursment: EditText

    //sharedpreference
    private val PREF_NAME = "LOGIN"
    private val USER_IS_LOGIN = "username"
    private lateinit var sharedPreferences: SharedPreferences


    lateinit var bmpFoto:Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_document_reimbursment)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE)

        txtUsername = findViewById(R.id.txtUsername)

        txtUsername.setText( sharedPreferences.getString(USER_IS_LOGIN,"").toString())

        fotoSurat = findViewById(R.id.fotoSuratReimbursment)

        txtTanggalReimbursment = findViewById(R.id.txtTglReimbursment)
        txtTanggalReimbursment.setOnClickListener{
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
            val timeDialog = DatePickerDialog(this,{_,year,month,dayOfMonth ->
                txtTanggalReimbursment.setText("$year-${month+1}-$dayOfMonth")},year,month,dayOfMonth)

            timeDialog.show()
        }


        btnCaptureCamera = findViewById(R.id.btnCaptureCamera)

        btnCaptureCamera.setOnClickListener{

            val implicitIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(implicitIntent,88)
        }

    }

    fun openFile(){
        val intent = Intent().apply {
            type = "application/msword"
            action =  Intent.ACTION_OPEN_DOCUMENT
            addCategory(Intent.CATEGORY_OPENABLE)
        }
        val chooser = Intent.createChooser(intent,"Pilih Aplikasi untuk membuka Document",null)
        startActivityForResult(chooser,1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 1 && resultCode == RESULT_OK){
           val fileUri: Uri? = data?.data
            if (fileUri != null) {
                readContentDoc(fileUri)
            }
        }else if (requestCode == 88){
            bmpFoto= data?.extras?.get("data") as Bitmap
            fotoSurat.setImageBitmap(data?.extras?.get("data") as Bitmap)
        }

    }


    fun readContentDoc(fileUri:Uri){

        val inputStream : InputStream? = contentResolver.openInputStream(fileUri)


        if(inputStream != null){
            val mimeType = contentResolver.getType(fileUri)
            val textContent = when (mimeType){
                "application/msword" -> {

                    val doc = XWPFDocument(inputStream)
                    val extractor = XWPFWordExtractor(doc)
                    val paragraphs = doc.paragraphs.joinToString (separator = "\n" )
                    {it.text}

                    Log.d("Isi Doc" , paragraphs)
//                    txtUploadDoc.setText(paragraphs)




            }
                else -> "Unsupported file type"
            }

        }else{
//            txtUploadDoc.setText("File tidak ditemukan")
        }





    }

}