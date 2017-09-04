package com.rs.tool.quickbackup.ui;

import com.rs.tool.quickbackup.action.*;
import com.rs.tool.quickbackup.db.Database;
import com.rs.tool.quickbackup.model.Task;
import com.rs.tool.quickbackup.model.Version;
import com.rs.tool.quickbackup.model.VersionFiles;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        new Main();
    }

    private Task               task = null;
    private List<Version>      list = null;
    private Map<Long, Version> map  = null;

    public Main() {
        Scanner input = new Scanner(System.in);
        while (input.hasNextLine()) {
            String line = input.nextLine();

            String[] param = line.split(" ");
            String command = param[0];
            switch (command) {
                case "loadtask":
                case "t":
                    task = new Task();
                    String[] fileNames = Arrays.copyOfRange(param, 2, param.length);
                    task.name = param[1];
                    task.fileNames = Arrays.asList(fileNames);
                    LoadTask.load(task);
                    break;

                case "listversions":
                case "l":
                    list = ListVersions.list();
                    map = new HashMap<>();
                    for (Version version : list) {
                        map.put(version.time, version);
                    }
                    for (Version version : list) {
                        System.out.println(version);
                    }
                    break;

                case "addversion":
                case "a":
                    Version version = new Version();
                    version.time = new Date().getTime();
                    version.name = param[2];
                    version.description = param[3];
                    VersionFiles versionFiles = PackFiles.pack(task, version);
                    AddVersion.add(map == null ? null : map.get(Long.parseLong(param[1])), versionFiles);
                    Database.getInstance().save();
                    break;

                case "deleteversion":
                case "d":
                    DeleteVersion.delete(map == null ? null : map.get(Long.parseLong(param[1])));
                    Database.getInstance().save();
                    break;

                case "clearversions":
                case "c":
                    ClearVersions.clear();
                    Database.getInstance().save();
                    break;

                case "unpackfile":
                case "u":
                    VersionFiles vf = LoadFiles.load(map == null ? null : map.get(Long.parseLong(param[1])));
                    UnpackFiles.unpack(vf);
                    break;
            }
        }
    }

}
