package com.anthole.commentest.addPic;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;



import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;

public class GalleryOrCameraCrop {
	
	public static final int TAKE_PICTURE=100;
	public static final int CHOOSE_PICTURE_AND_CROP_BIG=101;
	public static final int CHOOSE_PICTURE_AND_CROP_SMALL=102;
	public static final int CROP_PICTURE=103;
	public static final int CHOOSE_PICTURE = 104;
	
//	static String TempFile="file:///sdcard/temp.jpg";
	
	private static final String JPEG_FILE_PREFIX = "IMG_";
	private static final String JPEG_FILE_SUFFIX = ".jpg";
	private static AlbumStorageDirFactory mAlbumStorageDirFactory = null;
	public static String mCurrentPhotoPath;
	public static Uri tempFileUri;
	public static File tempFile;

	static{
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) {
			mAlbumStorageDirFactory = new FroyoAlbumDirFactory();
		} else {
			mAlbumStorageDirFactory = new BaseAlbumDirFactory();
		}
	}
	
	/**
	 * 获取图库图片 �?裁剪    不返回图片数�?
	 * @param a
	 * @param width  output宽度          0表示任意大小
	 * @param height   output高度     0表示任意大小
	 */
	public static void getBigPictureAndCrop(Activity a,int width,int height){
		try {
			setUpPhotoFile("");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Intent intent = new Intent(Intent.ACTION_PICK,
			    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//		intent.setType("image/*");
		intent.putExtra("crop", "true");
		if(width != 0||height != 0){
			if(width == height){
				intent.putExtra("aspectX", 1);
				intent.putExtra("aspectY", 1);
			}
			intent.putExtra("outputX", width);
			intent.putExtra("outputY", height);
		}
		intent.putExtra("scale", true);
		intent.putExtra("return-data", false);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, tempFileUri);
		intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
		intent.putExtra("noFaceDetection", true); // no face detection
		Intent wrapperIntent = Intent.createChooser(intent, null);
		a.startActivityForResult(wrapperIntent, CHOOSE_PICTURE_AND_CROP_BIG);
	}
	
	/**
	 * 获取图库图片 �?裁剪    不返回图片数�?
	 * @param a
	 * @param width  output宽度          0表示任意大小
	 * @param height   output高度     0表示任意大小
	 */
	public static void chooseImage(Activity a){
		try {
			setUpPhotoFile("");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Intent intent = new Intent(Intent.ACTION_PICK);
		intent.setType("image/*");
//		intent.putExtra("crop", "true");
//		intent.putExtra("scale", true);
//		intent.putExtra("return-data", false);
//		intent.putExtra(MediaStore.EXTRA_OUTPUT, tempFileUri);
//		intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
//		intent.putExtra("noFaceDetection", true); // no face detection
//		Intent wrapperIntent = Intent.createChooser(intent, null);
		a.startActivityForResult(intent, CHOOSE_PICTURE);
	}
	
	/**
	 * 获取图库图片 �?裁剪    返回图片数据
	 * @param a 
	 * @param width    output宽度      0表示任意大小
	 * @param height   output高度      0表示任意大小
	 */
	public static void getSmallPictureAndCrop(Activity a,int width,int height){
		try {
			setUpPhotoFile("");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Intent intent = new Intent(Intent.ACTION_GET_CONTENT, null);
		intent.setType("image/*");
		intent.putExtra("crop", "true");
		if(width != 0||height != 0){
			if(width == height){
				intent.putExtra("aspectX", 1);
				intent.putExtra("aspectY", 1);
			}
			intent.putExtra("outputX", width);
			intent.putExtra("outputY", height);
		}
		intent.putExtra("scale", true);
		intent.putExtra("return-data", true);
		intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
		intent.putExtra("noFaceDetection", true); // no face detection
		a.startActivityForResult(intent, CHOOSE_PICTURE_AND_CROP_SMALL);
	}
	
	/**
	 * 拍照
	 * @param a
	 */
	public static void takePicture(Activity a){
		try {
			setUpPhotoFile("");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//action is capture
		intent.putExtra(MediaStore.EXTRA_OUTPUT, tempFileUri);
		intent.putExtra("return-data", false);
		a.startActivityForResult(intent, TAKE_PICTURE);
	}
	
	/**
	 * 裁剪图片根据Uri
	 * @param a
	 * @param outputX    输出图片的宽�?   0表示任意大小
	 * @param outputY    输出图片的高�?   0表示任意大小
	 */
	public static void cropImageUri(Activity a,int outputX, int outputY){
		
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(tempFileUri, "image/*");
		intent.putExtra("crop", "true");
		if(outputX != 0||outputY != 0){
			if(outputX == outputY){
				intent.putExtra("aspectX", 1);
				intent.putExtra("aspectY", 1);
			}
			intent.putExtra("outputX", outputX);
			intent.putExtra("outputY", outputY);
		}
		intent.putExtra("scale", true);
		try {
			setUpPhotoFile("");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		intent.putExtra(MediaStore.EXTRA_OUTPUT, tempFileUri);
		intent.putExtra("return-data", false);
		intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
		intent.putExtra("noFaceDetection", true); // no face detection
		a.startActivityForResult(intent, CROP_PICTURE);
	}
	
	
	/**
	 * 得到裁剪过后的图�?
	 * @param a
	 * @return  uri对应的图�?
	 */
	public static Bitmap getBitmap(Activity a){
		try {
			return BitmapFactory.decodeStream(a.getContentResolver().openInputStream(tempFileUri));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	private static File getAlbumDir() {
		File storageDir = null;

		if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
			
			storageDir = mAlbumStorageDirFactory.getAlbumStorageDir(getAlbumName());

			if (storageDir != null) {
				if (! storageDir.mkdirs()) {
					if (! storageDir.exists()){
						Log.d("CameraSample", "failed to create directory");
						return null;
					}
				}
			}
			
		} else {
			
		}
		
		return storageDir;
	}

	private static File createImageFile(String name) throws IOException {
		// Create an image file name
		if(TextUtils.isEmpty(name)){
			String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
			String imageFileName = JPEG_FILE_PREFIX + timeStamp + "_";
			File albumF = getAlbumDir();
			File imageF = File.createTempFile(imageFileName, JPEG_FILE_SUFFIX, albumF);
			return imageF;
		}else{
			String timeStamp = "";
			String imageFileName = name + timeStamp + "_";
			File albumF = getAlbumDir();
			File imageF = File.createTempFile(imageFileName, JPEG_FILE_SUFFIX, albumF);
			return imageF;
		}
		
	}

	public static File setUpPhotoFile(String name) throws IOException {
		
		tempFile = createImageFile(name);
		mCurrentPhotoPath = tempFile.getAbsolutePath();
		tempFileUri = Uri.fromFile(tempFile);
		return tempFile;
	}
	
	/* Photo album for this application */
	private static String getAlbumName() {
		return "tongliao";
	}
}
