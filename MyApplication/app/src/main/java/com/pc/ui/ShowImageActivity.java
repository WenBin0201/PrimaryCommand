package com.pc.ui;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.pc.BaseActivity;
import com.pc.R;
import com.pc.utils.HttpManager;
import com.pc.utils.ImageUtil;


/**
 * Created by Administrator on 2018-03-21.
 */

public class ShowImageActivity extends BaseActivity implements View.OnClickListener{
    private ImageView img;
    private Bitmap bm;
    private RelativeLayout rl_show_image;
    private  String bitmapStr;
    private String imageUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);//不显示标题栏
        setContentView(R.layout.activity_show_image);
        rl_show_image = (RelativeLayout) findViewById(R.id.rl_show_image);
        img = (ImageView) findViewById(R.id.imageView1);
        img.setOnClickListener(this);
        rl_show_image.setOnClickListener(this);
        Bundle bundle = getIntent().getBundleExtra("bitmap");
        String type = bundle.getString("type");
        if (type.equals("1")){
            bitmapStr = bundle.getString("bitmap");
            setBitmapImg();
        }else{
            imageUrl = bundle.getString("bitmap");
            setUrlImage();
        }


    }
    private void setUrlImage(){
        HttpManager.getBitmap(imageUrl, img, new HttpManager.OnOkGoBitmapCallBack() {
            @Override
            public void OnSuccess(Bitmap bitmap, ImageView imageView) {
                setImageBitmap(bitmap,imageView);
            }
            @Override
            public void OnError(Exception e, ImageView imageView) {
                Toast.makeText(getApplicationContext(),"加载失败!!!",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
    private void setImageBitmap(Bitmap bitmap,ImageView imageView){
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        ViewGroup.LayoutParams params = imageView.getLayoutParams();
        params.height=height;
        params.width =width;
        imageView.setLayoutParams(params);
        imageView.setImageBitmap(bitmap);
    }
    private void setBitmapImg(){
        Bitmap bitmap = ImageUtil.convertStringToIcon(bitmapStr);
        setImageBitmap(bitmap,img);
//        bm =  big(bitmap);

//        img.setImageBitmap(bm);//设置图片为背景图
        rl_show_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    public Bitmap big(Bitmap bitmap) {                  //修改bitmap大小
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenWidth = dm.widthPixels;               //获取当前屏幕宽度
        int screenHeight = dm.heightPixels;             //获取当前屏幕高度
        float w = (float) screenWidth / bitmap.getWidth();  //计算当前图片要全屏幕，宽度需要放大尺寸
        float h = (float) screenHeight / bitmap.getHeight();//计算当前图片要全屏，高度需要放大尺寸
        if (w >= h)//选取较小尺寸进行放大
            w = h;
        Matrix matrix = new Matrix();
        matrix.postScale(w, w);//设置宽高放大比例（这里为等比例放大）
        Bitmap resizeBmp = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
                bitmap.getHeight(), matrix, true);//对现有bitmap进行放大
        return resizeBmp;
    }

    @Override
    public void onClick(View view) {
        this.finish();
    }
}
