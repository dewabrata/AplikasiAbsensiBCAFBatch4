package com.juaracoding.aplikasiabsensi

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
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

class DocumentReimbursment : AppCompatActivity() {
    lateinit var btnUploadDoc: Button
    lateinit var txtUploadDoc :EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_document_reimbursment)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        btnUploadDoc.setOnClickListener{

            openFile()
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
                    txtUploadDoc.setText(paragraphs)




            }
                else -> "Unsupported file type"
            }

        }else{
            txtUploadDoc.setText("File tidak ditemukan")
        }





    }
}