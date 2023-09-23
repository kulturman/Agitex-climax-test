package com.kulturman.climaxapp.climaxapp;

import java.util.List;

public class ApplicationService {
    private FileParserResolverInterface fileParserResolver;

    public ApplicationService(FileParserResolverInterface fileParserResolver) {
        this.fileParserResolver = fileParserResolver;
    }

    public List<Client> getClientsList(String filePath) {
        var parser = fileParserResolver.resolve(filePath);
        return parser.getFileContent(filePath);
    }
}
