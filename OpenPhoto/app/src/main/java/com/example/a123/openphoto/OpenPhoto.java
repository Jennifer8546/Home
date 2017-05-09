package com.example.a123.openphoto;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.graphics.Matrix;
import android.util.DisplayMetrics;
import android.util.Log;

import java.io.FileNotFoundException;

/**
 * Created by 123 on 2017/5/7.
 */

        public class OpenPhoto {
            public Intent Open() {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                return intent;
            }

            public Bitmap ScalePic(Bitmap bitmap, int phone) {
                //縮放比例預設為1
        float mScale = 1;
        //如果圖片寬度大於手機寬度則進行縮放，否則直接將圖片放入ImageView內
        if (bitmap.getWidth() > phone) {
            //判斷縮放比例
            mScale = (float) phone / (float) bitmap.getWidth();
            Matrix mMat = new Matrix();
            mMat.setScale(mScale, mScale);
            Bitmap mScaleBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), mMat, false);
            return mScaleBitmap;
        }
        return bitmap;
    }

    public final static int CAMERA = 66; //CAMERA 的代碼
    public final static int PHOTO = 99; //PHOTO 的代碼

    public int GetCAMERA() {
        return CAMERA;
    }

    public int GetPHOTO() {
        return PHOTO;
    }

    public Bitmap CheckActivityResult(Context context, int requestCode, Intent data, DisplayMetrics metrics) {
        Bitmap ScaleBitmap = null;
        //藉由requestCode判斷是否為開啟相機或開啟相簿而呼叫的，且data不為null
        if ((requestCode == CAMERA || requestCode == PHOTO) && data != null) {
            //取得照片路徑uri
            Uri uri = data.getData();
            ContentResolver cr = context.getContentResolver();

            try {
                //讀取照片，型態為Bitmap
                Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));

                //判斷照片為橫向或者為直向，並進入ScalePic判斷圖片是否要進行縮放

//Scale 是有問題的
                if (bitmap.getWidth() > bitmap.getHeight()) {
                    ScaleBitmap = ScalePic(bitmap, metrics.heightPixels);
                } else {
                    ScaleBitmap = ScalePic(bitmap, metrics.widthPixels);
                }
            } catch (FileNotFoundException e) {
                Log.e("Debug", "It's a FileNotFoundException On Make_Teaching_materials");
            }
            return ScaleBitmap;
        }
        return null;
    }
}

