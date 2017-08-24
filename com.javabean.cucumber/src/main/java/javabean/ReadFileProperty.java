package javabean;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;
import java.io.FilenameFilter;
import java.io.Serializable;


public class ReadFileProperty implements Serializable {

    public String fileName;
    public int fileSize;
    public String fileExtension;
    public String fileMimeType;
    public String filePath;

    private static String getExtensionOfFile(File file) {
        String fileExtension = "";
        // Get file Name first
        String fileName = file.getName();

        // If fileName do not contain "." or starts with "." then it is not a valid file
        if (fileName.contains(".") && fileName.lastIndexOf(".") != 0) {
            fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);
        }
        return fileExtension;

    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getFileSize() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public String getFileMimeType() {
        return fileMimeType;
    }

    public void setFileMimeType(String fileMimeType) {
        this.fileMimeType = fileMimeType;
    }

    public String getFilePath() {
        String currentDirectory = System.getProperty("user.dir");
        return currentDirectory + "/" + filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void displayFileData(File[] fList) {
        for (File file : fList) {
            if (file.isFile()) {
                System.out.println("File name:" + file.getName());
                System.out.println("File extension:" + getExtensionOfFile(file));
                System.out.println("File size:" + filesize(file));
                System.out.println("File mime type:" + getFileMimetype(file));

            }
        }
    }

    private double filesize(File file) {
        double bytes = file.length();
        return bytes;
    }

    private String getFileMimetype(File file) {

        MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();
        String mimeType = mimeTypesMap.getContentType(file);
        File fileMimeType = new File(this.getFilePath());
        mimeType = mimeTypesMap.getContentType(file);
        return mimeType;
    }

    public void filterFileTypes() {
        File dir = new File(this.getFilePath());
        String fileType = fileExtension;
        File[] files = getMatchingFiles(dir, fileType);
        System.out.println("Matching file found:" + files.length);
        displayFileData(files);
    }

    public File[] getMatchingFiles(File dir, final String fileType) {
        return dir.listFiles(new FilenameFilter() {
            public boolean accept(File dir1, String name) {
                return name.toLowerCase().endsWith(fileType);
            }
        });
    }

    public void listFiles() {
        File directory = new File(this.getFilePath());
        //get all the files from a directory
        File[] fList = directory.listFiles();

        displayFileData(fList);

    }


}
