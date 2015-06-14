package com.utils;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenCapture {

    private static final String TAG = "ssss";

    private static final String FOLDER = "screens";

    private static final long GB = 1024 * 1024 * 1024l;

    private static boolean IS_SPACE_LEFT = true;

    /**
     * Saves the screenshot to the sdcard.
     *
     * @return true if the file was saved to sdcard, false otherwise
     */
    public boolean saveScreenshotToSdCard(Bitmap bm, String cassName) {
        if (!IS_SPACE_LEFT) {
            Log.e(TAG, "There is not enough space left for saving screen shot");
            return false;
        }
        if (bm == null) {
            return false;
        }
        boolean isSaved = false;
        File imageDir = Environment.getExternalStoragePublicDirectory(FOLDER);
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File sdcardDir = Environment.getExternalStorageDirectory();
            StatFs sf = new StatFs(sdcardDir.getPath());
            int blockSize = sf.getBlockSize();
            long availCount = sf.getAvailableBlocks();
            if (blockSize * availCount < GB) {
                IS_SPACE_LEFT = false;
                if (!IS_SPACE_LEFT) {
                    Log.e(TAG, "There is not enough space left for saving screen shot");
                    return false;
                }
            }
            // External Storage is mounted. Go ahead and save the file.
            File file = null;
            boolean targetFileCreated = false;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String filename = cassName + sdf.format(new Date());

            file = new File(imageDir, filename+".png");

            try {
                File parentFile = file.getParentFile();
                if (parentFile != null && (parentFile.exists() || parentFile.mkdirs())) {
                    targetFileCreated = file.createNewFile();
                }
            } catch (IOException e) {
                Log.e(TAG, "IOException when creating the file", e);
            }

            if (targetFileCreated) {
                Log.i(TAG, "Found a free filename! " + file.toString());

                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream(file);
                    isSaved = bm.compress(CompressFormat.PNG, 100, fos);

                } catch (FileNotFoundException e) {
                    Log.e(TAG, "FileNotFoundException", e);
                } finally {
                    if (fos != null) {
                        try {
                            fos.close();
                        } catch (IOException e) {
                        }
                    }
                }
            } else {
                Log.i(TAG, "Failed to create target file.");
            }
        }

        return isSaved;
    }
}
