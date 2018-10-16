package cht;

import java.io.File;

public class Path {

    private String path;

    public String getPath() {
        return path;
    }


    public void setPath(String path) {
        this.path = path;
    }

    public void updatePath(String str) {
        this.path = this.path + File.separator + str;
    }

    public String[] fileList() {
        return (new File(getPath())).list();
    }

    public void returnBack() {
        this.path = new File(getPath()).getParent();
    }

}
