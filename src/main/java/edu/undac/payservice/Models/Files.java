package edu.undac.payservice.Models;

import java.io.File;

public class Files {

    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public File[] fileOrDirectory(){
        File[] files = new File(path).listFiles();
        if( files == null) {
            return null;
        }
        return files;
    }
}
