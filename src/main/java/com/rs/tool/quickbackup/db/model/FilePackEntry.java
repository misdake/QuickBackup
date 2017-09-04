package com.rs.tool.quickbackup.db.model;

import org.dizitart.no2.objects.Id;

import java.util.List;

public class FilePackEntry {
    @Id
    public long         time;
    public List<String> paths;
    public List<byte[]> contents;

    public FilePackEntry() {
    }
}
