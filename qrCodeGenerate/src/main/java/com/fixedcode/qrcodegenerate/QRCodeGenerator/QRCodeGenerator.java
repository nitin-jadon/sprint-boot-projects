package com.fixedcode.qrcodegenerate.QRCodeGenerator;

import com.fixedcode.qrcodegenerate.Student.Student;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class QRCodeGenerator {
    public static void generateQRCode(Student student) throws IOException, WriterException {
        String qrCodePath = "D:\\QRCodes\\";
        String qrCodeName = qrCodePath + "_ID_" + student.getId()
                            + "_Name_" + student.getFirstName() + "_QRCode.png";
        var qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(
                "ID: "+student.getId()+ "\n"+
                        "Firstname: "+student.getFirstName()+ "\n"+
                        "Lastname: "+student.getLastName()+ "\n"+
                        "Email: "+student.getEmail()+ "\n" +
                        "Mobile: "+student.getTelephone(), BarcodeFormat.QR_CODE, 400, 400);
        Path path = FileSystems.getDefault().getPath(qrCodeName);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);

    }
}
