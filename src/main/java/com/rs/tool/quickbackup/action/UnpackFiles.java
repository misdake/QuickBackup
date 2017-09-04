package com.rs.tool.quickbackup.action;

import com.rs.tool.quickbackup.model.VersionFiles;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;

public class UnpackFiles {

    public static void unpack(VersionFiles versionFiles) {
        try {
            for (Map.Entry<String, byte[]> e : versionFiles.files.entrySet()) {
                Files.write(new File(e.getKey()).toPath(), e.getValue());
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

}
