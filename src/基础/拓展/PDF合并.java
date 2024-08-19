package 基础.拓展;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PDF合并 {
    public static void main(String[] args) {
        List<String> inputFiles = new ArrayList<>();
        inputFiles.add("D:\\AAA_notApp\\桌面\\南昌大学\\课程\\学术英语\\文献1.pdf");
        inputFiles.add("D:\\AAA_notApp\\桌面\\南昌大学\\课程\\学术英语\\文献2.pdf");
        inputFiles.add("D:\\AAA_notApp\\桌面\\南昌大学\\课程\\学术英语\\文献3.pdf");
        String outputFile = "D:\\AAA_notApp\\桌面\\南昌大学\\课程\\学术英语\\合并文献.pdf";
        try {
            mergePDFs(inputFiles, outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void mergePDFs(List<String> inputFiles, String outputFile) throws IOException {
        PDDocument mergedDocument = new PDDocument();
        for (String inputFile : inputFiles) {
            PDDocument document = PDDocument.load(new File(inputFile));
            for (PDPage page : document.getPages()) {
                mergedDocument.addPage(page);
            }
        }

        mergedDocument.save(outputFile);
    }


}
