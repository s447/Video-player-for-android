package com.example.video;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.OpenableColumns;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public static int REQUEST_TAKE_GALLERY_VIDEO=200;
    public static Uri FILE_URI;
    public static String VIDEO_NAME;
    Button play;
    TextView videoName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button selectVideo=findViewById(R.id.button);
        selectVideo.setOnClickListener(this);
        play=findViewById(R.id.button2);
        play.setOnClickListener(this);
        videoName=findViewById(R.id.textView);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode==RESULT_OK){
            if(requestCode==REQUEST_TAKE_GALLERY_VIDEO){
                Uri selectedVideoUri=data.getData();
                FILE_URI=selectedVideoUri;
                VIDEO_NAME=getFileName(FILE_URI);
                Log.d("Name",VIDEO_NAME);
                videoName.setText(VIDEO_NAME);

            }
        }
    }

    private String getFileName(Uri uri) {
        String result=null;
        if(uri.getScheme().equals("content")){
            Cursor cursor=getContentResolver().query(uri,null,null,null,null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            }finally {
                cursor.close();
            }
            }
        if(result==null){
            result=uri.getPath();
            int cut=result.lastIndexOf('/');
            if(cut!=-1){
                result=result.substring(cut+1);
            }
        }
        return result;

    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button:
                Intent intent=new Intent();
                intent.setType("video/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select Video"),REQUEST_TAKE_GALLERY_VIDEO);
                break;
            case R.id.button2:
                if(FILE_URI==null){
                    break;
                }
                Intent intent2=new Intent(MainActivity.this,VideoActivity.class);
                intent2.putExtra("VIDEO_PATH",FILE_URI.toString());
                startActivity(intent2);
                break;
        }
    }
}
