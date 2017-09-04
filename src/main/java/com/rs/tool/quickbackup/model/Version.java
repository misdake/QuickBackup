package com.rs.tool.quickbackup.model;

import java.util.List;

public class Version {
    public long          time;
    public String        name;
    public String        description;
    public Version       parent;
    public List<Version> children;

    @Override
    public String toString() {
        return String.format("%d %s %s %d", time, name, description, parent == null ? 0 : parent.time);
    }
}
