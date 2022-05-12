package org.techtown.phtoiet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.FileProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.techtown.phtoiet.MealViewModel.MealAdapter
import org.techtown.phtoiet.MealViewModel.MealViewModel
import org.techtown.phtoiet.databinding.ActivityRegistrationBinding
import java.io.File

private const val REQUEST_CODE_FOR_IMAGE_CAPTRUE = 100

class Registration_Acitivity : AppCompatActivity() {

    private val model: MealViewModel by viewModels()
    private lateinit var adapter: MealAdapter
    private lateinit var mBinding: ActivityRegistrationBinding
    private lateinit var photoFile : File

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        mBinding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        setPermission()//권한 허용 메소드


       var Meal_uri = ""

        mBinding.takePicture.setOnClickListener{

            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

            if(intent.resolveActivity(packageManager) != null){

                val dir = externalCacheDir
                val file = File.createTempFile("photo_",".jpg",dir)
                val uri = FileProvider.getUriForFile(this,"$packageName.provider",file)

                Meal_uri = uri.toString()

                intent.putExtra(MediaStore.EXTRA_OUTPUT,uri)
                startActivityForResult(intent, REQUEST_CODE_FOR_IMAGE_CAPTRUE)
                photoFile = file

            }

        }

        mBinding.ADDButton.setOnClickListener{

            val mealName = mBinding.eatFood.text.toString()
            val mealTime = mBinding.eatTime.text.toString()
            val calories = mBinding.calories.text.toString()

           lifecycleScope.launch(Dispatchers.IO){
               model.insert(Meal(Meal_uri,mealName,mealTime,calories))//externalCacheDir로부터 image를 가져온다.
           }//코루틴으로 ViewModel 접근
           //main thread에서 접근하는 것이 아닌 코루틴을 이용하여 DB에 접근한다.(주의사항)

            finish()//액티비티 종료
      }

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when(requestCode){

            REQUEST_CODE_FOR_IMAGE_CAPTRUE ->{

                if(resultCode == RESULT_OK){
                    Glide.with(this).load(photoFile).into(mBinding.foodImage)
                }
                else{
                    Toast.makeText(this,"취소되었습니다",Toast.LENGTH_LONG).show()
                }
            }

        }

    }

    private fun setPermission(){

        val permission = object : PermissionListener{

            override fun onPermissionGranted() {
                Toast.makeText(this@Registration_Acitivity,"권한이 허용되었습니다.",Toast.LENGTH_SHORT).show()
            }

            override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
                Toast.makeText(this@Registration_Acitivity,"권한이 거부되었습니다.",Toast.LENGTH_SHORT).show()
            }

        }

        TedPermission.with(this)
            .setPermissionListener(permission)
            .setRationaleMessage("카메라 앱을 사용하시려면 권한을 허용해주세요.")
            .setDeniedMessage("권한을 거부하였습니다.")
            .setPermissions(android.Manifest.permission.WRITE_EXTERNAL_STORAGE,android.Manifest.permission.CAMERA)
            .check()

    }//권한 허용 메소드


}