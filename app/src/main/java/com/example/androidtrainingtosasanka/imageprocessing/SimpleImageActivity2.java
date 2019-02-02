package com.example.androidtrainingtosasanka.imageprocessing;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.androidtrainingtosasanka.R;
import com.example.androidtrainingtosasanka.appclass.AppConstants;
import com.example.androidtrainingtosasanka.appclass.ApplicationClass;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;

public class SimpleImageActivity2 extends Activity {

    ImageView viewImage;
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_image2);

        b = (Button) findViewById(R.id.btnSelectPhoto);
        viewImage = (ImageView) findViewById(R.id.viewImage);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new TedPermission(SimpleImageActivity2.this)
                        .setPermissionListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted() {
                                selectImage();
                            }

                            @Override
                            public void onPermissionDenied(ArrayList<String> arrayList) {
                                //Toast
                            }
                        })
                        .setDeniedMessage(R.string.denied_permission_message)
                        .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                        .check();
            }
        });
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds options to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }*/

    private void selectImage() {

        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(SimpleImageActivity2.this);
        builder.setTitle("Add Photo!");

        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int position) {

                if (options[position].equals("Take Photo")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                  /*  File f = new File(Environment.getExternalStorageDirectory(), "temp.jpg");
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));*/
                    startActivityForResult(intent, 1);
                    //startActivity(intent);

                } else if (options[position].equals("Choose from Gallery")) {
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 2);

                } else if (options[position].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {

                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                viewImage.setImageBitmap(bitmap);

                String base64Image = bitmapToBase64(bitmap);

                Log.e("base64 is ", base64Image);

                ApplicationClass.insertIntoPrefernces(AppConstants.base64Image, base64Image);

               /* Uri selectedImage = data.getData();
                String[] filePath = { MediaStore.Images.Media.DATA };
                Cursor c = getContentResolver().query(selectedImage,filePath, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                String picturePath = c.getString(columnIndex);
                c.close();
                Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));
                Drawable drawable=new BitmapDrawable(thumbnail);
               // backGroundImageLinearLayout.setBackgroundDrawable(drawable);
                viewImage.setImageBitmap(thumbnail);
*/

               String str= "fbfbuef ifhnif eif iefh fifbfed  ifh f";

              /*  File f = new File(Environment.getExternalStorageDirectory().toString());
                for (File temp : f.listFiles()) {
                    if (temp.getName().equals("temp.jpg")) {
                        f = temp;
                        Log.w("path image1 from galery", f.getAbsolutePath() + "");
                        break;
                    }
                }
                try {
                    Bitmap bitmap;
                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();

                    bitmap = BitmapFactory.decodeFile(f.getAbsolutePath(),
                            bitmapOptions);

                    viewImage.setImageBitmap(bitmap);

                    String base64Imahe = bitmapToBase64(generateBitMap(f.getAbsolutePath()));
                    Log.e("base 64 is ", bitmapToBase64(generateBitMap(f.getAbsolutePath())));

                    ApplicationClass.insertIntoPrefernces(AppConstants.base64Image, base64Imahe);

                   *//* String path = android.os.Environment
                            .getExternalStorageDirectory()
                            + File.separator
                            + "Phoenix" + File.separator + "default";
                    f.delete();
                    OutputStream outFile = null;
                    File file = new File(path, String.valueOf(System.currentTimeMillis()) + ".jpg");
                    try {
                        outFile = new FileOutputStream(file);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 85, outFile);
                        outFile.flush();
                        outFile.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }*//*
                } catch (Exception e) {
                    e.printStackTrace();
                }*/
            } else if (requestCode == 2) {

                Uri selectedImage = data.getData();
                String[] filePath = {MediaStore.Images.Media.DATA};
                Cursor c = getContentResolver().query(selectedImage, filePath, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                String picturePath = c.getString(columnIndex);
                c.close();
                Bitmap bitmap = (BitmapFactory.decodeFile(picturePath));
                Log.e("bitmap ", bitmap + "");

                viewImage.setImageBitmap(bitmap);

                Log.w("path image from gallery", picturePath + "");


                Bitmap bitmap2 = generateBitMap(picturePath);
                String base64Imahe = bitmapToBase64(bitmap2);  //base64 is string format

                Log.e("base 64 is ", base64Imahe);

                ApplicationClass.insertIntoPrefernces(AppConstants.base64Image, base64Imahe);
            }
        }
    }

    private String bitmapToBase64(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }

    private Bitmap generateBitMap(String filePath) {
        File imgFile = new File(filePath);
        Bitmap myBitmap = null;
        if (imgFile.exists()) {
            myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            //Drawable d = new BitmapDrawable(getResources(), myBitmap);
            // imgView.setImageBitmap(myBitmap);
        }
        return myBitmap;
    }
}