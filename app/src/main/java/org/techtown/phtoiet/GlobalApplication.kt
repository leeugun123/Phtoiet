package org.techtown.phtoiet

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class GlobalApplication : Application(){
    override fun onCreate() {
        super.onCreate()

        KakaoSdk.init(this,"b9e72f7cce0b5bd40c8ed8da1bdcc3a9")//카카오 로그인 초기화
    }
}