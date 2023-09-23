package com.kulturman.climaxapp.climaxapp;

import java.io.File;
import java.util.List;

public interface FileParser {
    List<Client> getFileContent(File file);
}
