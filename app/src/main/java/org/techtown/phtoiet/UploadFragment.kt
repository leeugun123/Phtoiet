package org.techtown.phtoiet

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.bumptech.glide.Glide
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import org.techtown.phtoiet.databinding.ActivityUploadMealBinding
import org.techtown.phtoiet.databinding.FragmentFriendsBinding
import java.io.File



class UploadFragment : Fragment() {

    private lateinit var binding : ActivityUploadMealBinding

    val REQUEST_IMAGE_CAPTURE = 1 // 카메라 사진 촬영 요청코드
    lateinit var curPhotoPath: String // 문자열 형태의 사진 경로 값(초기 값을 null로 시작하고 싶을 때)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadMealBinding.inflate(layoutInflater)


        setPermission()//권한 허용 메소드

        binding.shot.setOnClickListener {

            takeCapture() // 기본 카메라앱을 실행하여 사진 촬영



        }


    }

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?) : View?{
        return binding?.root
    }

    //카메라 촬영 기능
   private fun takeCapture(){
       // 기본 카메라 앱 실행

       Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
          takePictureIntent.resolveActivity()?.also {
               val photoFile: File? = try {

               }
           }
       }

   }

    //테드 퍼미션 설정
    private fun setPermission() {
        val permission = object : PermissionListener {

            override fun onPermissionGranted() {//설정해높은 위험권한들이 허용 되었을 경우 이 곳을 수행함
                Toast.makeText(activity,"권한이 허용되었습니다", Toast.LENGTH_SHORT).show()
            }

            override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {//설정해높은 위험권한들 중 거부를 한 경우
                Toast.makeText(activity,"권한이 거부되었습니다", Toast.LENGTH_SHORT).show()
            }

        }

        TedPermission.with(activity)
            .setPermissionListener(permission)
            .setRationaleMessage("카메라를 사용하시려면 권한을 허용해주세요,")
            .setDeniedMessage("권한을 거부하셨습니다. [앱 설정] -> [권한] 항목에서 허용해주세요")
            .setPermissions(android.Manifest.permission.WRITE_EXTERNAL_STORAGE,android.Manifest.permission.CAMERA)
            .check()

    }


}

