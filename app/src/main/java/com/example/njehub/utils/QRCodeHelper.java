package com.example.njehub.utils;

import android.graphics.Bitmap;
import android.graphics.Color;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.QRCodeWriter;

public class QRCodeHelper {

    public static Bitmap generateQRCode(String text, int width, int height) {

        QRCodeWriter writer = new QRCodeWriter();

        try {

            var bitMatrix = writer.encode(text, BarcodeFormat.QR_CODE, width, height);

            Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);

            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {

                    bitmap.setPixel(
                            x,
                            y,
                            bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE
                    );
                }
            }

            return bitmap;

        } catch (WriterException e) {
            e.printStackTrace();
            return null;
        }
    }
}