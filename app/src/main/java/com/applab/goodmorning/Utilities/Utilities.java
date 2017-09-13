package com.applab.goodmorning.Utilities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.Uri;
import android.opengl.GLES10;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Transformation;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.applab.goodmorning.Account.activity.AccountDetailsActivity;
import com.applab.goodmorning.Account.provider.AccountProvider;
import com.applab.goodmorning.Checkout.activity.CartActivity;
import com.applab.goodmorning.Checkout.webapi.HttpHelper;
import com.applab.goodmorning.Enquiry.activity.EnquiryActivity;
import com.applab.goodmorning.HomeScreen.activity.SplashScreenActivity;
import com.applab.goodmorning.Login.activity.LoginActivity;
import com.applab.goodmorning.Login.database.CRUDHelper;
import com.applab.goodmorning.Login.model.Token;
import com.applab.goodmorning.Login.provider.TokenProvider;

import com.applab.goodmorning.Menu.database.MenuDBHelper;
import com.applab.goodmorning.Menu.fragment.NavigationDrawerFragment;
import com.applab.goodmorning.News.activity.NewsActivity;
import com.applab.goodmorning.News.fragment.NewsFragment;
import com.applab.goodmorning.Order.activity.OrderHistoryActivity;
import com.applab.goodmorning.R;
import com.applab.goodmorning.Register.activity.RegisterActivity;
import com.applab.goodmorning.Register.model.Country;
import com.applab.goodmorning.Welcome.activity.WelcomeActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import org.joda.time.DateTimeZone;

import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import eu.janmuller.android.simplecropimage.CropImage;
import eu.janmuller.android.simplecropimage.Util;

/**
 * -- =============================================
 * -- Author     : Edwin Cheong
 * -- Create date: 19/1/2016
 * -- Description: AssigneeList .java
 * -- =============================================
 * HISTORY OF UPDATE
 * <p/>
 * NO     DEVELOPER         DATETIME                      DESCRIPTION
 * *******************************************************************************
 * 1
 * 2
 */
public class Utilities {
    public static final int TIMEOUT = 30000;
    public static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    public static final int SIZE_DEFAULT = 2048;
    public static final int SIZE_LIMIT = 4096;
    public static final int SELECT_PHOTO_REQUEST_CODE = 100;
    public static final int CROP_IMAGE_REQUEST_CODE = 32;
    public static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 101;
    private static DownloadManager mgr;

    //System Message (Not in Server)
    public static final String CONNECTION = "No connection, please try again.";
    public static final String CONNECTION_TIMEOUT = "Sorry, connection timeout";
    public static final String PROCEED_FAILED = "Unable to proceed, please try again.";
    public static final String CAMERA_SUPPORT = "Sorry, you don't have supported camera.";
    public static final String LOCAL_SIZE_EXCEED = "The file is exceeded the local storage size.";
    public static final String SUCCESS_UPDATED = "Success Updated.";
    public static final String UPLOAD_PROGRESS = "Upload in progress.";
    public static final String UPLOAD_COMPLETE = "Upload complete.";
    public static final String IMAGE_TYPE = "The image must be jpg or png.";
    public static final String IMAGE_LARGE = "The image is too large.";
    public static final String EMAIL_FIELD = "Email/Username field is required.";
    public static final String PASSWORD_FIELD = "Password field is required.";

    public static final String NO_FILE = "NO file is present";
    public static final String ForgotPassword = "Password has been succefully changed";

    //System Message (In Server)
    public static final String CODE_CONNECTION = "101";
    public static final String CODE_CONNECTION_TIMEOUT = "122";
    public static final String CODE_PROCEED_FAILED = "145";
    public static final String CODE_CAMERA_SUPPORT = "169";
    public static final String CODE_LOCAL_SIZE_EXCEEDED = "124";
    public static final String CODE_SUCCESS_UPDATED = "170";
    public static final String CODE_UPLOAD_PROGRESS = "171";
    public static final String CODE_UPLOAD_COMPLETE = "172";
    public static final String CODE_IMAGE_TYPE = "173";
    public static final String CODE_IMAGE_LARGE = "174";
    public static final String CODE_EMAIL_FIELD = "177";
    public static final String CODE_PASSWORD_FIELD = "178";
    public static final String CODE_NO_PICTURE = "179";
    public static final String CODE_SUCCESS = "100";

    private static String TAG = Utilities.class.getSimpleName();

    public static DownloadManager getMgr() {
        return mgr;
    }

    public static void setMgr(DownloadManager mgr) {
        Utilities.mgr = mgr;
    }


    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            if (children.length > 0) {
                for (int i = 0; i < children.length - 1; i++) {
                    boolean success = deleteDir(new File(dir, children[i]));
                    if (!success) {
                        return false;
                    }
                }
            }
        }
        assert dir != null;
        return dir.delete();
    }

    public static void showError(Context context, String code, String error) {
        String errorMessage = Utilities.getMessage(context, code);
        if (errorMessage != null) {
            Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
        }
    }

    public static void serverHandlingError(Context context, VolleyError error) {
        if (error instanceof AuthFailureError || error instanceof ServerError) {
            if (!context.getClass().getSimpleName().equals(NewsActivity.class.getSimpleName()) && !context.getClass().getSimpleName().equals(NewsFragment.class.getSimpleName())) {
                NetworkResponse response = error.networkResponse;
                String json = new String(response.data);
                Log.i(TAG, "Server Error: " + json);
                if (response.statusCode != 200) {
                    if (json.contains("Authorization has been denied for this request.")) {
                        Utilities.alertDialog("Authorization has been denied for this request.", context);
                    } else if (json.contains("Incorrect password.")) {
                        Toast.makeText(context, "Incorrect Password", Toast.LENGTH_SHORT).show();
                    } else {
                        //Utilities.showError(context, Utilities.CODE_PROCEED_FAILED, Utilities.PROCEED_FAILED);
                    }
                } else if (error instanceof NetworkError) {
                    String errorConnectionMessage = Utilities.getMessage(context, Utilities.CODE_CONNECTION);
                    if (errorConnectionMessage != null) {
                        Toast.makeText(context, errorConnectionMessage, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, Utilities.CONNECTION, Toast.LENGTH_SHORT).show();
                    }
                } else if (error instanceof TimeoutError) {
                    String errorTimeOutMessage = Utilities.getMessage(context, Utilities.CODE_CONNECTION_TIMEOUT);
                    if (errorTimeOutMessage != null) {
                        Toast.makeText(context, errorTimeOutMessage, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, Utilities.CONNECTION_TIMEOUT, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }

    public static void serverHandlingError(Context context, VolleyError error, TextView txtError) {
        if (error instanceof AuthFailureError || error instanceof ServerError) {
            if (!context.getClass().getSimpleName().equals(NewsActivity.class.getSimpleName()) && !context.getClass().getSimpleName().equals(NewsFragment.class.getSimpleName())) {
                NetworkResponse response = error.networkResponse;
                String json = new String(response.data);
                Log.i(TAG, "Server Error: " + json);
                if (response.statusCode != 200) {
                    if (json.contains("Authorization has been denied for this request.")) {
                        Utilities.alertDialog("Authorization has been denied for this request.", context);
                    } else {
                        //txtError.setText(Utilities.getDialogMessage(context, Utilities.CODE_PROCEED_FAILED, Utilities.PROCEED_FAILED));
                        //txtError.setVisibility(View.VISIBLE);
                    }
                }
            }
        } else if (error instanceof NetworkError) {
            if (!context.getClass().getSimpleName().equals(NewsActivity.class.getSimpleName()) && !context.getClass().getSimpleName().equals(NewsFragment.class.getSimpleName())) {
                String errorConnectionMessage = Utilities.getMessage(context, Utilities.CODE_CONNECTION);
                if (errorConnectionMessage != null) {
                    txtError.setText(errorConnectionMessage);
                    txtError.setVisibility(View.VISIBLE);
                    NetworkResponse response = error.networkResponse;
                    String json = new String(response.data);
                    Log.i("Error:", "Server Error: " + json);
                    if (response.statusCode != 200) {
                        if (json.contains("Authorization has been denied for this request.")) {
                            Utilities.alertDialog("Authorization has been denied for this request.", context);
                        } else {
                            txtError.setText(Utilities.getDialogMessage(context, Utilities.CODE_PROCEED_FAILED, Utilities.PROCEED_FAILED));
                            txtError.setVisibility(View.VISIBLE);
                        }
                    }
                } else if (error instanceof TimeoutError) {
                    String errorTimeOutMessage = Utilities.getMessage(context, Utilities.CODE_CONNECTION_TIMEOUT);
                    if (errorTimeOutMessage != null) {
                        txtError.setText(errorTimeOutMessage);
                    } else {
                        txtError.setText(Utilities.CONNECTION_TIMEOUT);
                        txtError.setVisibility(View.VISIBLE);
                    }
                }
            }
        }
    }

    public static String getDialogMessage(Context context, String code, String error) {
        String errorMessage = Utilities.getMessage(context, code);
        if (errorMessage != null) {
            return errorMessage;
        } else {
            return error;
        }
    }

    public static void alertDialog(String errorMessage, final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        builder.setMessage(errorMessage);
        builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //context.getContentResolver().delete(TokenProvider.CONTENT_URI, null, null);
                //Intent intent = new Intent(context, LoginPage.class);
                //context.startActivity(intent);
                ((Activity) context).finish();
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public static String getMessage(Context context, String args) {
        String message = null;
        /*String[] projection = {DBHelper.MESSAGE_COLUMN_MESSAGE};
        String selection = DBHelper.MESSAGE_COLUMN_CODE + "=?";
        String[] selectionArgs = {args};
        String sorting = DBHelper.MESSAGE_COLUMN_ID + " DESC LIMIT 1";
        Cursor cursor = null;
        try {
            cursor = context.getContentResolver().query(MessageProvider.CONTENT_URI, projection, selection, selectionArgs, sorting);
            if (cursor != null && cursor.moveToFirst()) {
                cursor.moveToFirst();
                message = cursor.getString(cursor.getColumnIndex(DBHelper.MESSAGE_COLUMN_MESSAGE));
            }
        } catch (Exception ex) {
            ex.fillInStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }*/
        return message;
    }

    //region Set Date Time
    public static String setToLocalDateTime(String prevFormat, String newFormat, String
            data) {
        String result = null;
        SimpleDateFormat format = new SimpleDateFormat(prevFormat, Locale.getDefault());
        format.setTimeZone(TimeZone.getTimeZone("UTC"));
        SimpleDateFormat print = new SimpleDateFormat(newFormat, Locale.getDefault());
        print.setTimeZone(TimeZone.getDefault());
        if (data != null) {
            Date date = null;
            try {
                date = format.parse(data);
            } catch (ParseException e) {
                e.printStackTrace();
            } finally {
                if (date == null) {
                    try {
                        org.joda.time.DateTime dt = new org.joda.time.DateTime(data, DateTimeZone.UTC);
                        dt.toDateTime(DateTimeZone.getDefault());
                        result = dt.toString(newFormat);
                    } catch (Exception e) {
                        e.fillInStackTrace();
                    } finally {
                        if (data.contains("GMT")) {
                            org.joda.time.DateTime dt = new org.joda.time.DateTime();
                            dt.plusDays(3);
                            result = dt.toString(newFormat);
                        }
                    }
                } else {
                    result = print.format(date);
                }
            }
        } else {
            result = null;
        }
        return result;
    }

    public static String setToUTCDate(String prevFormat, String newFormat, String data) {
        SimpleDateFormat format = new SimpleDateFormat(prevFormat, Locale.getDefault());
        format.setTimeZone(TimeZone.getDefault());
        SimpleDateFormat print = new SimpleDateFormat(newFormat, Locale.getDefault());
        print.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = null;
        try {
            date = format.parse(data);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (date == null) {
                org.joda.time.DateTime dt = new org.joda.time.DateTime(data, DateTimeZone.getDefault());
                dt.toDateTime(DateTimeZone.UTC);
                date = Utilities.setCalendarDate(Utilities.DATE_FORMAT, dt.toString(Utilities.DATE_FORMAT));
            }
        }
        return print.format(date);
    }

    public static Date setCalendarDate(String dateFormat, String data) {
        SimpleDateFormat format = new SimpleDateFormat(dateFormat, Locale.getDefault());
        Date date = null;
        try {
            date = format.parse(data);
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            if (date == null) {
                org.joda.time.DateTime dt = new org.joda.time.DateTime(data);
                date = dt.toDate();
            }
        }
        return date;
    }

    public static String setCalendarDate(String prevFormat, String newFormat, String data) {
        String result = null;
        SimpleDateFormat format = new SimpleDateFormat(prevFormat, Locale.getDefault());
        SimpleDateFormat print = new SimpleDateFormat(newFormat, Locale.getDefault());
        Date date = null;
        try {
            date = format.parse(data);
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            if (data == null) {
                org.joda.time.DateTime dt = new org.joda.time.DateTime(date);
                result = dt.toString(newFormat);
            } else {
                result = print.format(date);
            }
        }
        return result;
    }

    public static String setCalendarDate(String dateFormat, Date date) {
        String result = null;
        SimpleDateFormat format = new SimpleDateFormat(dateFormat, Locale.getDefault());
        try {
            result = format.format(date);
        } catch (Exception ex) {
            ex.fillInStackTrace();
        } finally {
            if (result == null) {
                org.joda.time.DateTime dt = new org.joda.time.DateTime(date);
                result = dt.toString(dateFormat);
            }
        }
        return result;
    }
    //endregion

    //region Load Image
    public static Uri getOutputMediaFileUri(Context context, boolean isDelete, File file) {
        return Uri.fromFile(getOutputMediaFile(context, isDelete, file));
    }

    public static File getOutputMediaFile(Context context, boolean isDelete, File
            mediaStorageDir) {
        if (mediaStorageDir == null) {
            mediaStorageDir = new File(Environment.getExternalStorageDirectory() + "/" + context.getResources().getString(R.string.folder_name) + "/" +
                    context.getResources().getString(R.string.temp));
        }

        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null;
            }
        } else {
            if (isDelete) {
                boolean isDirDeleted = Utilities.deleteDir(mediaStorageDir);
                if (isDirDeleted) {
                    mediaStorageDir.mkdirs();
                }
            }
        }
        String timeStamp = Utilities.setCalendarDate("yyyyMMdd_HHmmss_SSS", new Date());
        return new File(mediaStorageDir.getPath() + File.separator + "IMG_" + timeStamp + ".jpg");
    }

    public static File previewCapturedImage(Context context, Uri fileUri,
                                            final ImageView img, final boolean isCrop, int requestedCode, boolean isDelete, File
                                                    direct, int maxLength) {
        ImageUtilities.normalizeImageForUri(context, fileUri);
        File file = new File(fileUri.getPath());
        file = Utilities.convertImageToSmall(context, file, isDelete, direct, maxLength);
        DisplayMetrics mDisplayMetrics = context.getResources().getDisplayMetrics();
        int mHeight = mDisplayMetrics.heightPixels;
        int mWidth = mDisplayMetrics.widthPixels;
        if (!isCrop) {
            if (img != null) {
                Glide.with(context)
                        .load(file.getAbsolutePath())
                        .asBitmap()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .placeholder(R.drawable.empty_photo)
                        .into(new SimpleTarget<Bitmap>(mWidth, mHeight) {
                            @Override
                            public void onResourceReady(Bitmap bitmap, GlideAnimation anim) {
                                img.setImageBitmap(bitmap);
                            }
                        });
            }
        } else {
            boolean isSuccess = performCrop(fileUri, context, requestedCode);
            if (!isSuccess) {
                if (img != null) {
                    Glide.with(context)
                            .load(file.getAbsolutePath())
                            .asBitmap()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .placeholder(R.drawable.empty_photo)
                            .into(new SimpleTarget<Bitmap>(mWidth, mHeight) {
                                @Override
                                public void onResourceReady(Bitmap bitmap, GlideAnimation anim) {
                                    img.setImageBitmap(bitmap);
                                }
                            });
                }
            }
        }
        return file;
    }

    public static File previewSelectedImage(Context context, Uri selectedImage,
                                            final ImageView img, final boolean isCrop, int requestedCode, boolean isDelete, File
                                                    direct, int maxLength) {
        if (getRealPathFromURI(context, selectedImage) != null) {
            File file = new File(getRealPathFromURI(context, selectedImage));
            file = Utilities.convertImageToSmall(context, file, isDelete, direct, maxLength);
            DisplayMetrics mDisplayMetrics = context.getResources().getDisplayMetrics();
            int mHeight = mDisplayMetrics.heightPixels;
            int mWidth = mDisplayMetrics.widthPixels;
            if (!isCrop) {
                if (img != null) {
                    Glide.with(context)
                            .load(file.getAbsolutePath())
                            .asBitmap()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .placeholder(R.drawable.empty_photo)
                            .into(new SimpleTarget<Bitmap>(mWidth, mHeight) {
                                @Override
                                public void onResourceReady(Bitmap bitmap, GlideAnimation anim) {
                                    img.setImageBitmap(bitmap);
                                }
                            });
                }
            } else {
                boolean isSuccess = performCrop(selectedImage, context, requestedCode);
                if (!isSuccess) {
                    if (img != null) {
                        Glide.with(context)
                                .load(file.getAbsolutePath())
                                .asBitmap()
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .placeholder(R.drawable.empty_photo)
                                .into(new SimpleTarget<Bitmap>(mWidth, mHeight) {
                                    @Override
                                    public void onResourceReady(Bitmap bitmap, GlideAnimation anim) {
                                        img.setImageBitmap(bitmap);
                                    }
                                });
                    }
                }
            }
            return file;
        } else {
            return null;
        }
    }

    /**
     * this function does the crop operation.
     */
    public static boolean performCrop(Uri picUri, Context context, int requestCode) {
        boolean isSuccess = false;
        // take care of exceptions
        try {
            ImageUtilities.normalizeImageForUri(context, picUri);
            Intent cropIntent = new Intent(context, CropImage.class);
            // tell CropImage activity to look for image to crop
            cropIntent.putExtra(CropImage.IMAGE_PATH, new File(getRealPathFromURI(context, picUri)).getAbsolutePath());

            // allow CropImage activity to rescale image
            cropIntent.putExtra(CropImage.SCALE, true);

            // if the aspect ratio is fixed to ratio 3/2
            cropIntent.putExtra(CropImage.ASPECT_X, 2);
            cropIntent.putExtra(CropImage.ASPECT_Y, 2);
            // start the activity - we handle returning in onActivityResult
            ((Activity) context).startActivityForResult(cropIntent, requestCode);
            isSuccess = true;
        }
        // respond to users whose devices do not support the crop action
        catch (ActivityNotFoundException anfe) {
            Utilities.showError(context, Utilities.CODE_CAMERA_SUPPORT, Utilities.CAMERA_SUPPORT);
        }
        return isSuccess;
    }

    public static String getRealPathFromURI(Context context, Uri contentUri) {
        Cursor cursor = null;
        String result = null;
        try {
            cursor = context.getContentResolver().query(contentUri, null, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                result = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
        } catch (Exception ex) {
            ex.fillInStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        if (contentUri != null && result == null) {
            try {
                File file = new File(new URI(contentUri.toString()));
                result = file.getAbsolutePath();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static File convertImageToSmall(Context context, File file,
                                           boolean isDelete, File direct, int maxLength) {
        if (file != null) {
            InputStream is = null;
            int sampleSize = 0;
            try {
                sampleSize = Utilities.calculateBitmapSampleSize(Uri.fromFile(file), context, maxLength);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                is = context.getContentResolver().openInputStream(Uri.fromFile(file));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            BitmapFactory.Options option = new BitmapFactory.Options();
            option.inSampleSize = sampleSize;
            Bitmap b = BitmapFactory.decodeStream(is, null, option);
            return getFile(context, b, isDelete, direct);
        } else return null;
    }

    public static File getFile(Context mContext, Bitmap result, boolean isDelete, File
            direct) {
        OutputStream fOut = null;
        File file = getOutputMediaFile(mContext, isDelete, direct);
        assert file != null;
        try {
            fOut = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            assert result != null;
            result.compress(Bitmap.CompressFormat.JPEG, 85, fOut);// saving the Bitmap to a file compressed as a JPEG with 85% compression rate
            try {
                assert fOut != null;
                fOut.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fOut.close(); // do not forget to close the stream
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception ex) {
            ex.fillInStackTrace();
        }
        return file;
    }

    public static int calculateBitmapSampleSize(Uri bitmapUri, Context context,
                                                int maxLength) throws IOException {
        InputStream is = null;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        try {
            is = context.getContentResolver().openInputStream(bitmapUri);
            BitmapFactory.decodeStream(is, null, options); // Just get image size
        } finally {
            closeSilently(is);
        }

        int maxSize = getMaxImageSize(maxLength);
        int sampleSize = 1;
        while (options.outHeight / sampleSize > maxSize || options.outWidth / sampleSize > maxSize) {
            sampleSize = sampleSize << 1;
        }
        return sampleSize;
    }

    public static void closeSilently(@Nullable Closeable c) {
        if (c == null) return;
        try {
            c.close();
        } catch (Throwable t) {
            // Do nothing
        }
    }

    public static int getMaxImageSize(int max) {
        int textureLimit = getMaxTextureSize();
        if (textureLimit == 0) {
            return SIZE_DEFAULT;
        } else {
            return Math.min(textureLimit, max);
        }
    }

    public static int getMaxTextureSize() {
        // The OpenGL texture size is the maximum size that can be drawn in an ImageView
        int[] maxSize = new int[1];
        GLES10.glGetIntegerv(GLES10.GL_MAX_TEXTURE_SIZE, maxSize, 0);
        return maxSize[0];
    }

    public static boolean isDeviceSupportCamera(Context context) {
        return context.getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_CAMERA);
    }

    public static long getUsableSpace() {
        File savePath = Environment.getExternalStorageDirectory();
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.GINGERBREAD) {
            return savePath.getUsableSpace();

        } else {
            StatFs stats = new StatFs(savePath.getAbsolutePath());
            return stats.getAvailableBlocksLong() * stats.getBlockSizeLong();
        }
    }

    public static boolean isValidImage(File file, Context context) {
        boolean isValid = false;
        if (file != null) {
            if (file.length() < 3 * 1000000) {
                String[] result = file.getName().split("\\.");
                if (result[result.length - 1].equalsIgnoreCase("jpg") || result[result.length - 1].
                        equalsIgnoreCase("png") || result[result.length - 1].equalsIgnoreCase("jpeg")) {
                    isValid = true;
                } else {
                    Utilities.showError(context, Utilities.CODE_IMAGE_TYPE, Utilities.IMAGE_TYPE);
                }
            } else {
                Utilities.showError(context, Utilities.CODE_IMAGE_LARGE, Utilities.IMAGE_LARGE);
            }
        }
        return isValid;
    }
    //endregion

    public static void setFadeProgressBarVisibility(boolean isVisible, View rl, View
            progressBar) {
        rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        if (isVisible) {
            progressBar.setVisibility(View.VISIBLE);
            rl.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
            rl.setVisibility(View.GONE);
        }
    }

    public static void setSideMenuOnClickListener(final Context context, TextView
            txtProfile, View btnSignUp, View btnRegister, View btnAccount, View btnHistory, View
                                                          btnLogout, final View sideMenu, final View rl) {
        setSideMenuVisible(false, sideMenu, rl);
        refreshMenu(context, txtProfile, btnSignUp, btnRegister, btnAccount, btnHistory, btnLogout, sideMenu, rl);
        btnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!context.getClass().getSimpleName().equals(AccountDetailsActivity.class.getSimpleName())) {
                    Intent intent = new Intent(context, AccountDetailsActivity.class);
                    context.startActivity(intent);
                    if (!context.getClass().getSimpleName().equals(WelcomeActivity.class.getSimpleName()))
                        ((Activity) context).finish();
                }
                setSideMenuVisible(false, sideMenu, rl);
            }
        });

        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!context.getClass().getSimpleName().equals(OrderHistoryActivity.class.getSimpleName())) {
                    Intent intent = new Intent(context, OrderHistoryActivity.class);
                    context.startActivity(intent);
                    if (!context.getClass().getSimpleName().equals(WelcomeActivity.class.getSimpleName())) {
                        ((Activity) context).finish();
                    }
                }
                setSideMenuVisible(false, sideMenu, rl);
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, LoginActivity.class);
                context.startActivity(intent);
                if (!context.getClass().getSimpleName().equals(WelcomeActivity.class.getSimpleName())) {
                    ((Activity) context).finish();
                }
                setSideMenuVisible(false, sideMenu, rl);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.getContentResolver().delete(TokenProvider.CONTENT_URI, null, null);
                context.getContentResolver().delete(AccountProvider.CONTENT_URI, null, null);
                Intent intent = new Intent(context, LoginActivity.class);
                context.startActivity(intent);
                if (!context.getClass().getSimpleName().equals(WelcomeActivity.class.getSimpleName())) {
                    ((Activity) context).finish();
                }
                setSideMenuVisible(false, sideMenu, rl);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, RegisterActivity.class);
                context.startActivity(intent);
                if (!context.getClass().getSimpleName().equals(WelcomeActivity.class.getSimpleName())) {
                    ((Activity) context).finish();
                }
                setSideMenuVisible(false, sideMenu, rl);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.getContentResolver().delete(TokenProvider.CONTENT_URI, null, null);
                Intent intent = new Intent(context, LoginActivity.class);
                context.startActivity(intent);
                if (!context.getClass().getSimpleName().equals(WelcomeActivity.class.getSimpleName())) {
                    ((Activity) context).finish();
                }
                setSideMenuVisible(false, sideMenu, rl);
            }
        });

        rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSideMenuVisible(false, sideMenu, rl);
            }
        });
    }

    public static void refreshMenu(final Context context, TextView
            txtProfile, View btnSignUp, View btnRegister, View btnAccount, View btnHistory, View
                                           btnLogout, final View sideMenu, final View rl) {
        Token token = CRUDHelper.getToken(context);

        if (token != null) {
            if (new Date().before(Utilities.setCalendarDate("yyyy-MM-dd", Utilities.setToLocalDateTime("yyyy-MM-dd", "yyyy-MM-dd", token.getExpiryDate())))) {
                btnSignUp.setVisibility(View.GONE);
                btnRegister.setVisibility(View.GONE);

                String profile = context.getString(R.string.hi) + " " + token.getFirstName() + " " + token.getLastName();
                txtProfile.setText(profile);
                txtProfile.setVisibility(View.VISIBLE);
                btnAccount.setVisibility(View.VISIBLE);
                btnHistory.setVisibility(View.VISIBLE);
                btnLogout.setVisibility(View.VISIBLE);

            } else {
                btnSignUp.setVisibility(View.VISIBLE);
                btnRegister.setVisibility(View.VISIBLE);
                txtProfile.setVisibility(View.VISIBLE);
                btnAccount.setVisibility(View.GONE);
                btnHistory.setVisibility(View.GONE);
                btnLogout.setVisibility(View.GONE);
            }
        } else {
            txtProfile.setText(context.getString(R.string.welcome));
            btnSignUp.setVisibility(View.VISIBLE);
            btnRegister.setVisibility(View.VISIBLE);
            txtProfile.setVisibility(View.VISIBLE);
            btnAccount.setVisibility(View.GONE);
            btnHistory.setVisibility(View.GONE);
            btnLogout.setVisibility(View.GONE);
        }

    }

    public static void setSideMenuVisible(boolean isVisible, final View sideMenu,
                                          final View rl) {
        if (isVisible) {
            rl.setVisibility(View.VISIBLE);
            sideMenu.setVisibility(View.VISIBLE);
        } else {
            rl.setVisibility(View.GONE);
            sideMenu.setVisibility(View.GONE);
        }
    }

    public static void backPage(Context context) {
        if (context.getClass().getSimpleName().equals(WelcomeActivity.class.getSimpleName())) {
            ((Activity) context).moveTaskToBack(true);
        } else {
            ((Activity) context).finish();
        }
    }

    public static void checkToCloseSideMenu(Context context, final View sideMenu,
                                            final View rl, NavigationDrawerFragment drawer) {
        if (sideMenu.getVisibility() == View.VISIBLE) {
            setSideMenuVisible(false, sideMenu, rl);
        } else {
            if (drawer != null) {
                drawer.setCloseDrawer(context);
            } else {
                backPage(context);
            }
        }
    }

    public static void checkToCloseSideMenuForOnPause(Context context, final View sideMenu,
                                                      final View rl, NavigationDrawerFragment drawer) {
        if (sideMenu.getVisibility() == View.VISIBLE) {
            setSideMenuVisible(false, sideMenu, rl);
        } else {
            if (drawer != null) {
                drawer.setCloseDrawerForOnPause(context);
            }
        }
    }

    public static void checkSideMenu(Context context, final View sideMenu, final View rl) {
        if (sideMenu.getVisibility() == View.VISIBLE) {
            setSideMenuVisible(false, sideMenu, rl);
        } else {
            backPage(context);
        }
    }

    public static void checkSideMenuOnPause(Context context, final View sideMenu,
                                            final View rl) {
        if (sideMenu.getVisibility() == View.VISIBLE) {
            setSideMenuVisible(false, sideMenu, rl);
        }
    }

    public static void setLocale(String lang, Context context) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        context.getApplicationContext().getResources().updateConfiguration(config, null);
        Intent refresh = new Intent(context, SplashScreenActivity.class);
        context.startActivity(refresh);
        ((Activity) context).finish();
    }

    public static void saveLanguage(String lang, Context context) {
        String langPref = "Language";
        SharedPreferences.Editor editor = context.getSharedPreferences("CommonPrefs", Activity.MODE_PRIVATE).edit();
        editor.putString(langPref, lang);
        editor.apply();

        MenuDBHelper.updateMenu(context);
    }

    public static String getDefaultLanguage(Context context) {
        return context.getString(R.string.english);
    }

    public static void loadLanguage(Context context) {
        String langPref = "Language";
        SharedPreferences preferences = context.getSharedPreferences("CommonPrefs", Activity.MODE_PRIVATE);
        String language = preferences.getString(langPref, null);
        if (language != null) {
            Locale locale = new Locale(language);
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            context.getApplicationContext().getResources().updateConfiguration(config, null);
        } else {
            String languageToLoad = "en_US";
            Locale locale = new Locale(languageToLoad);
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            context.getApplicationContext().getResources().updateConfiguration(config, null);
        }
    }

    public static String getLanguage(Context context) {
        String langPref = "Language";
        String languageToLoad = "en_US";
        String languageToLoadMs = "ms";
        String languageToLoadZh = "zh";
        String lang = "";
        SharedPreferences preferences = context.getSharedPreferences("CommonPrefs", Activity.MODE_PRIVATE);
        String language = preferences.getString(langPref, "");
        if (language.equals(languageToLoad)) {
            lang = context.getString(R.string.english);
        } else if (language.equals(languageToLoadMs)) {
            lang = context.getString(R.string.malay);
        } else if (language.equals(languageToLoadZh)) {
            lang = context.getString(R.string.chinese);
        }
        return lang;
    }

    public static void saveCountry(Context context, int selectedCountry) {
        String countryPrefs = "Country";
        SharedPreferences.Editor editor = context.getSharedPreferences("CommonPrefs", Activity.MODE_PRIVATE).edit();
        editor.putInt(countryPrefs, selectedCountry);
        editor.apply();
        Intent refresh = new Intent(context, SplashScreenActivity.class);
        context.startActivity(refresh);
        ((Activity) context).finish();
    }

    public static void setCountryId(Context context, int selectedCountry) {
        String countryPrefs = "Country";
        SharedPreferences.Editor editor = context.getSharedPreferences("CommonPrefs", Activity.MODE_PRIVATE).edit();
        editor.putInt(countryPrefs, selectedCountry);
        editor.apply();
    }

    public static String getCountry(Context context) {
        String countryPrefs = "Country";
        SharedPreferences sharedPreferences = context.getSharedPreferences("CommonPrefs", Activity.MODE_PRIVATE);
        Integer country = sharedPreferences.getInt(countryPrefs, 0);
        String selectedCountry = null;

        if (country == 2) {
            selectedCountry = context.getString(R.string.country_singapore);
        } else if (country == null) {
            selectedCountry = context.getString(R.string.country_malaysia);
        } else {
            selectedCountry = context.getString(R.string.country_malaysia);
        }
        return selectedCountry;
    }

    public static int getCountryID(Context context) {
        String countryPrefs = "Country";
        SharedPreferences sharedPreferences = context.getSharedPreferences("CommonPrefs", Activity.MODE_PRIVATE);
        Integer country = sharedPreferences.getInt(countryPrefs, 1);

        return country;
    }

    public static int getCountryId(Context context) {
        String countryPrefs = "Country";
        SharedPreferences sharedPreferences = context.getSharedPreferences("CommonPrefs", Activity.MODE_PRIVATE);
        Integer country = sharedPreferences.getInt(countryPrefs, 1);

        return country;
    }

    public static void expand(final View v) {
        v.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        final int targetHeight = v.getMeasuredHeight();

        v.getLayoutParams().height = 0;
        v.setVisibility(View.VISIBLE);
        android.view.animation.Animation a = new android.view.animation.Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                v.getLayoutParams().height = interpolatedTime == 1
                        ? ViewGroup.LayoutParams.WRAP_CONTENT
                        : (int) (targetHeight * interpolatedTime);
                v.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        a.setDuration((int) (targetHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
    }

    public static void collapse(final View v) {
        final int initialHeight = v.getMeasuredHeight();

        android.view.animation.Animation a = new android.view.animation.Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1) {
                    v.setVisibility(View.GONE);
                } else {
                    v.getLayoutParams().height = initialHeight - (int) (initialHeight * interpolatedTime);
                    v.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        a.setDuration((int) (initialHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
    }

    public static boolean isConnectingToInternet(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Network[] networks = connectivityManager.getAllNetworks();
            NetworkInfo networkInfo;
            for (Network mNetwork : networks) {
                networkInfo = connectivityManager.getNetworkInfo(mNetwork);
                if (networkInfo.getState().equals(NetworkInfo.State.CONNECTED)) {
                    return true;
                }
            }
        } else {
            if (connectivityManager != null) {
                //noinspection deprecation
                NetworkInfo[] info = connectivityManager.getAllNetworkInfo();
                if (info != null) {
                    for (NetworkInfo anInfo : info) {
                        if (anInfo.getState() == NetworkInfo.State.CONNECTED) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public static void downloadFile(String url, Context context, String folderName) {
        File direct = new File(Environment.getExternalStorageDirectory()
                + "/" + context.getResources().getString(R.string.folder_name) + "/" + folderName);
        if (!direct.exists()) {
            direct.mkdirs();
        }
        String[] result = url.split("/");
        String fileName = result[result.length - 1];
        if (!Utilities.localFileExists(direct.getAbsolutePath() + "/" + fileName)) {
            if (getMgr() == null) {
                DownloadManager mgr = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
                setMgr(mgr);
            }
            Uri downloadUri = Uri.parse(url);
            DownloadManager.Request request = new DownloadManager.Request(
                    downloadUri);
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            request.setAllowedNetworkTypes(
                    DownloadManager.Request.NETWORK_WIFI
                            | DownloadManager.Request.NETWORK_MOBILE)
                    .setAllowedOverRoaming(false)
                    .setDestinationInExternalPublicDir("/" + context.getResources().getString(R.string.folder_name) + "/" + folderName, fileName);
            getMgr().enqueue(request);
            String txt = context.getString(R.string.download_file);
            Utilities.showError(context, "", txt);
        } else {
            String txt = context.getString(R.string.file_exist) + " " + direct.getAbsolutePath() + "/" + fileName;
            Utilities.showError(context, "", txt);
        }
    }

    public static void checkDownloadStatus(long id, Context context) {
        DownloadManager.Query query = new DownloadManager.Query();
        query.setFilterById(id);
        Cursor cursor = getMgr().query(query);
        if (cursor != null && cursor.moveToFirst()) {
            int columnIndex = cursor
                    .getColumnIndex(DownloadManager.COLUMN_STATUS);
            int status = cursor.getInt(columnIndex);
            int columnReason = cursor
                    .getColumnIndex(DownloadManager.COLUMN_REASON);
            int reason = cursor.getInt(columnReason);
            String title = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_TITLE));

            switch (status) {
                case DownloadManager.STATUS_FAILED:
                    String failedReason = "";
                    switch (reason) {
                        case DownloadManager.ERROR_CANNOT_RESUME:
                            failedReason = context.getString(R.string.error_cannot_resume);
                            break;
                        case DownloadManager.ERROR_DEVICE_NOT_FOUND:
                            failedReason = context.getString(R.string.error_device_no_found);
                            break;
                        case DownloadManager.ERROR_FILE_ALREADY_EXISTS:
                            failedReason = context.getString(R.string.error_file_already_exists);
                            break;
                        case DownloadManager.ERROR_FILE_ERROR:
                            failedReason = context.getString(R.string.error_file_error);
                            break;
                        case DownloadManager.ERROR_HTTP_DATA_ERROR:
                            failedReason = context.getString(R.string.error_http_data_error);
                            break;
                        case DownloadManager.ERROR_INSUFFICIENT_SPACE:
                            failedReason = context.getString(R.string.error_insufficient_space);
                            break;
                        case DownloadManager.ERROR_TOO_MANY_REDIRECTS:
                            failedReason = context.getString(R.string.error_too_many_redirects);
                            break;
                        case DownloadManager.ERROR_UNHANDLED_HTTP_CODE:
                            failedReason = context.getString(R.string.error_unhandled_HTTP_code);
                            break;
                        case DownloadManager.ERROR_UNKNOWN:
                            failedReason = context.getString(R.string.error_unknown);
                            break;
                    }

                    Toast.makeText(context, context.getString(R.string.download) + " " + title + " " + context.getString(R.string.is_failed) + " " + failedReason,
                            Toast.LENGTH_LONG).show();
                    break;
                case DownloadManager.STATUS_PAUSED:
                    String pausedReason = "";
                    switch (reason) {
                        case DownloadManager.PAUSED_QUEUED_FOR_WIFI:
                            pausedReason = context.getString(R.string.paused_queued_for_wifi);
                            break;
                        case DownloadManager.PAUSED_UNKNOWN:
                            pausedReason = context.getString(R.string.paused_unknown);
                            break;
                        case DownloadManager.PAUSED_WAITING_FOR_NETWORK:
                            pausedReason = context.getString(R.string.paused_waiting_for_network);
                            break;
                        case DownloadManager.PAUSED_WAITING_TO_RETRY:
                            pausedReason = context.getString(R.string.paused_waiting_to_retry);
                            break;
                    }
                    Toast.makeText(context, context.getString(R.string.download) + " " + title + context.getString(R.string.is_paused) + " " + pausedReason,
                            Toast.LENGTH_LONG).show();
                    break;
                case DownloadManager.STATUS_PENDING:
                    Toast.makeText(context, context.getString(R.string.pending), Toast.LENGTH_LONG).show();
                    break;
                case DownloadManager.STATUS_RUNNING:
                    Toast.makeText(context, context.getString(R.string.running), Toast.LENGTH_LONG).show();
                    break;
                case DownloadManager.STATUS_SUCCESSFUL:
                    Toast.makeText(context, context.getString(R.string.download) + " " + title + context.getString(R.string.is_completed), Toast.LENGTH_SHORT).show();
                    break;
            }
            cursor.close();
        }
    }


    public static boolean localFileExists(String path) {
        if (path == null) {
            return false;
        }
        File direct = new File(path);
        return direct.exists();
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static String encode(Object obj) {
    /*try {
        return URLEncoder.encode(String.valueOf(obj), "UTF-8").replace("+", "%20");
    } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
        return String.valueOf(obj);
    }*/
        return String.valueOf(obj);
    }

    public static Date getFirstDate(Object month, Object year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, Integer.valueOf(String.valueOf(year)));
        calendar.set(Calendar.MONTH, Integer.valueOf(String.valueOf(month)));
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    public static Date getLastDate(Object month, Object year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, Integer.valueOf(String.valueOf(year)));
        calendar.set(Calendar.MONTH, Integer.valueOf(String.valueOf(month)));
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }

    public static Date getSpecificDate(Object day, Object month, Object year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, Integer.valueOf(String.valueOf(year)));
        calendar.set(Calendar.MONTH, Integer.valueOf(String.valueOf(month)));
        calendar.set(Calendar.DAY_OF_MONTH, Integer.valueOf(String.valueOf(day)));
        return calendar.getTime();
    }

    public static int getMonth(Object month) {
        int m = Integer.valueOf(String.valueOf(month)) - 1;
        if (m < 0) {
            m = 11;
        }
        return m;
    }

    public static void openWeb(Context context, String url) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        context.startActivity(browserIntent);
    }


    public static void openDocument(String path, Context context) {
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW);
        File file = new File(path);
        String extension = android.webkit.MimeTypeMap.getFileExtensionFromUrl(Uri.fromFile(file).toString());
        String mimetype = android.webkit.MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
        if (extension.equalsIgnoreCase("") || mimetype == null) {
            // if there is no extension or there is no definite mimetype, still try to open the file
            intent.setDataAndType(Uri.fromFile(file), "text/*");
        } else {
            intent.setDataAndType(Uri.fromFile(file), mimetype);
        }
        // custom message for the intent
        Intent intentChooser = Intent.createChooser(intent, context.getString(R.string.choose_an_application));
        intentChooser.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intentChooser);
    }

    public static void watchYoutubeVideo(Context context, String url, String id) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + id));
            context.startActivity(intent);
        } catch (ActivityNotFoundException ex) {
            Intent intent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(url));
            context.startActivity(intent);
        }

    }

    public static void openGoogleMap(Context context, String latitude, String longitude) {
        String url = context.getString(R.string.google_map_url) + latitude + "," + longitude;
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        context.startActivity(intent);
    }

    public static void openWaze(Context context, String latitude, String longitude) {
        try {
            String url = context.getString(R.string.waze_url) + latitude + "," + longitude;
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            context.startActivity(intent);
        } catch (ActivityNotFoundException ex) {
            String url = context.getString(R.string.google_map_url) + latitude + "," + longitude;
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            context.startActivity(intent);
        }
    }

    public static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    public static void showKeyboard(Activity activity, View view) {
        InputMethodManager keyboard = (InputMethodManager)
                activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        keyboard.showSoftInput(view, 0);
    }

    public static void setAddCartOnClickListener(final Context context, View actionShop) {
        actionShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLogin(context)) {
                    Intent intent = new Intent(context, CartActivity.class);
                    context.startActivity(intent);
                }
            }
        });
        if (CRUDHelper.getToken(context) != null) {
            HttpHelper.getCount(context, context.getClass().getSimpleName(), actionShop);
        }
    }

    public static void refreshAddCart(final Context context, View actionShop) {
        if (CRUDHelper.getToken(context) != null) {
            HttpHelper.getCount(context, context.getClass().getSimpleName(), actionShop);
        }
    }

    public static void refreshActionShop(final Context context, View actionShop) {
        if (CRUDHelper.getToken(context) != null) {
            HttpHelper.getCount(context, context.getClass().getSimpleName(), actionShop);
        }
    }

    public static void setSharePreference(Object objectValue, String fileName, String objectName, Context context) {
        SharedPreferences sp = context.getSharedPreferences(fileName, 0);
        SharedPreferences.Editor ed = sp.edit();
        ed.putString(objectName, String.valueOf(objectValue));
        ed.apply();
    }

    public static String getSharePreference(String fileName, String objectName, Context context) {
        SharedPreferences sp = context.getSharedPreferences(fileName, 0);
        return sp.getString(objectName, null);
    }

    public static void sendLocalLock(boolean isLock, Context context, String TAG) {
        if (TAG == null) {
            Intent intent = new Intent(context.getClass().getSimpleName());
            intent.putExtra("isLock", isLock);
            LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
        } else {
            Intent intent = new Intent(TAG);
            intent.putExtra("isLock", isLock);
            LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
        }
    }

    public static boolean isLogin(Context context) {
        boolean isValid = true;
        if (CRUDHelper.getToken(context) == null) {
            Utilities.showError(context, "", context.getString(R.string.need_login));
            isValid = false;
        } else if (!Utilities.isConnectingToInternet(context)) {
            Utilities.showError(context, "", context.getString(R.string.connection));
            isValid = false;
        }
        return isValid;
    }
}