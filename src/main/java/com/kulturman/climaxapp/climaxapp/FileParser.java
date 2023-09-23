package com.kulturman.climaxapp.climaxapp;

import java.util.List;

public interface FileParser {
    List<Client> getFileContent(String filePath);
}
