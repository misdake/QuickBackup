package com.rs.tool.quickbackup.action;

import com.rs.tool.quickbackup.model.Task;
import com.rs.tool.quickbackup.model.Version;
import com.rs.tool.quickbackup.model.VersionFiles;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;

public class PackFiles {

    public static VersionFiles pack(Task task, Version version) {
        VersionFiles vf = new VersionFiles(version);
        vf.files = new HashMap<>();

        try {
            for (String fileName : task.fileNames) {
                byte[] bytes = Files.readAllBytes(new File(fileName).toPath());
                vf.files.put(fileName, bytes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return vf;
    }

}
