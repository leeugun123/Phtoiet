package org.techtown.phtoiet

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.bumptech.glide.Glide
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import org.techtown.phtoiet.databinding.ActivityUploadMealBinding
import org.techtown.phtoiet.databinding.FragmentFriendsBinding
import java.io.File
import androidx.core.content.PermissionChecker as PermissionChecker1

private const val REQUEST_CODE_FOR_IMAGE_CAPTURE = 100

class Upload_meal : AppCompatActivity() {

    private lateinit var binding : ActivityUploadMealBinding
    private lateinit var photoFile: File
    //var는 변환할 수 있는 수 val은 바뀌지 않을 수

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadMealBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setPermission()//권한 허용 메소드


        binding.shot.setOnClickListener {

            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

            if(intent.resolveActivity(packageManager) != null){
                val dir = externalCacheDir
                val file = File.createTempFile("photo_",".jpg",dir)
                val uri = FileProvider.getUriForFile(this,"$packageName.provider",file)
                intent.putExtra(MediaStore.EXTRA_OUTPUT,uri)
                startActivityForResult(intent, REQUEST_CODE_FOR_IMAGE_CAPTURE)
                photoFile = file
            }

        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when(requestCode){
            REQUEST_CODE_FOR_IMAGE_CAPTURE ->{
                if(resultCode == RESULT_OK){
                    Glide.with(this).load(photoFile).into(binding.PictureImage)
                }
                else{
                    Toast.makeText(this,"취소 되었습니다.",Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    //테드 퍼미션 설정
    private fun setPermission() {
        val permission = object : PermissionListener{

            override fun onPermissionGranted() {//설정해높은 위험권한들이 허용 되었을 경우 이 곳을 수행함
                Toast.makeText(this@Upload_meal,"권한이 허용되었습니다",Toast.LENGTH_SHORT).show()
            }

            override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {//설정해높은 위험권한들 중 거부를 한 경우
                Toast.makeText(this@Upload_meal,"권한이 거부되었습니다",Toast.LENGTH_SHORT).show()
            }

        }

        TedPermission.with(this)
            .setPermissionListener(permission)
            .setRationaleMessage("카메라를 사용하시려면 권한을 허용해주세요,")
            .setDeniedMessage("권한을 거부하셨습니다. [앱 설정] -> [권한] 항목에서 허용해주세요")
            .setPermissions(android.Manifest.permission.WRITE_EXTERNAL_STORAGE,android.Manifest.permission.CAMERA)
            .check()

    }


}