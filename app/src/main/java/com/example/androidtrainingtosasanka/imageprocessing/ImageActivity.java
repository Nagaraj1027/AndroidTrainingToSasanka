package com.example.androidtrainingtosasanka.imageprocessing;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.androidtrainingtosasanka.R;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class ImageActivity extends AppCompatActivity {

    ImageView viewImage;
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        b=(Button)findViewById(R.id.btnSelectPhoto);
        viewImage=(ImageView)findViewById(R.id.viewImage);

        b.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                new TedPermission(ImageActivity.this)
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
                selectImage();

            }

        });

    }

    private File savebitmap(Bitmap bmp) {
        String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
        OutputStream outStream = null;
        // String temp = null;
        File file = new File(extStorageDirectory, "temp.png");
        if (file.exists()) {
            file.delete();
            file = new File(extStorageDirectory, "temp.png");

        }

        try {
            outStream = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 100, outStream);
            outStream.flush();
            outStream.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return file;
    }

    private void selectImage() {



        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };



        AlertDialog.Builder builder = new AlertDialog.Builder(ImageActivity.this);

        builder.setTitle("Add Photo!");

        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override

            public void onClick(DialogInterface dialog, int item) {

                if (options[item].equals("Take Photo"))
                {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File f = new File(android.os.Environment.getExternalStorageDirectory(), "temp.jpg");
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                    startActivityForResult(intent, 1);
                }

                else if (options[item].equals("Choose from Gallery"))
                {
                    Intent intent = new   Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 2);
                }

                else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }

            }

        });

        builder.show();

    }



    @Override

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                //h=0;
                File f = new File(Environment.getExternalStorageDirectory().toString());
                for (File temp : f.listFiles()) {
                    if (temp.getName().equals("temp.jpg")) {
                        f = temp;
                        File photo = new File(Environment.getExternalStorageDirectory(), "temp.jpg");

                        break;
                    }
                }
                try {
                    Bitmap bitmap;
                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
                    bitmap = BitmapFactory.decodeFile(f.getAbsolutePath(),
                            bitmapOptions);

                    viewImage.setImageBitmap(bitmap);
                    String path = android.os.Environment
                            .getExternalStorageDirectory()
                            + File.separator
                            + "Phoenix" + File.separator + "default";
                    //p = path;
                    f.delete();
                    OutputStream outFile = null;
                    File file = new File(path, String.valueOf(System.currentTimeMillis()) + ".jpg");
                    try {
                        outFile = new FileOutputStream(file);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 85, outFile);
                        //pic=file;
                        outFile.flush();
                        outFile.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();

                    } catch (Exception e) {

                        e.printStackTrace();

                    }

                } catch (Exception e) {

                    e.printStackTrace();

                }

            } else if (requestCode == 2) {
                Uri selectedImage = data.getData();
                // h=1;
                //imgui = selectedImage;
                String[] filePath = { MediaStore.Images.Media.DATA };
                Cursor c = getContentResolver().query(selectedImage,filePath, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                String picturePath = c.getString(columnIndex);
                c.close();

                Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));
                Log.w("image path from gallery", picturePath+"");
                viewImage.setImageBitmap(thumbnail);
            }
        }
}}
