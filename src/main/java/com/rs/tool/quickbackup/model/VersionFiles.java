package com.rs.tool.quickbackup.model;

import java.io.File;
import java.util.Map;

public class VersionFiles extends Version {
    public Map<String, byte[]> files;

    public VersionFiles(Version version) {
        this.time = version.time;
        this.name = version.name;
        this.description = version.description;
        this.children = version.children;
    }
}
