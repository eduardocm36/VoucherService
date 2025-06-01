package edu.undac.payservice.Web.Requests;

import java.io.File;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileRequest {

    private String path;

    public File[] fileOrDirectory(){
        File[] files = new File(path).listFiles();
        if( files == null) {
            return null;
        }
        return files;
    }
}
