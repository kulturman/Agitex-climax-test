package com.kulturman.climaxapp.infra;

import com.kulturman.climaxapp.domain.FileParser;
import com.kulturman.climaxapp.domain.FileParserResolverInterface;

import java.io.File;

public class ExtensionFileResolver implements FileParserResolverInterface {
    @Override
    public FileParser resolve(File file) {
        var extension = file.getName().substring(file.getName().lastIndexOf(".") + 1);

        return switch (extension) {
            case "xml" -> new XmlFileParser();
            case "yaml", "yml" -> new YamlFileParser();
            default -> throw new RuntimeException("No file parser for " + file.getName());
        };
    }
}
