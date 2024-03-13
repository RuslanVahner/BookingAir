package com.vahner.airticketsapp.generator;

//@RequiredArgsConstructor
//public class QRCodeGenerator {
//    public static byte[] generateQRCodeImage(String text, int width, int height) throws WriterException, IOException {
//        QRCodeWriter qrCodeWriter = new QRCodeWriter();
//        Map<EncodeHintType, Object> hints = new HashMap<>();
//        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
//        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height, hints);
//
//        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
//        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
//        return pngOutputStream.toByteArray();
//    }
//}