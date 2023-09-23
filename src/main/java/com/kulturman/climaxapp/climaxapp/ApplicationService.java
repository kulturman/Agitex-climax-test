package com.kulturman.climaxapp.climaxapp;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class ApplicationService {
    private FileParserResolverInterface fileParserResolver;

    public ApplicationService(FileParserResolverInterface fileParserResolver) {
        this.fileParserResolver = fileParserResolver;
    }

    public List<Client> getClientsList(String filePath) {
        var parser = fileParserResolver.resolve(filePath);
        return parser.getFileContent(filePath);
    }

    public List<GroupResult> getMeanByProfession(List<Client> clientsList) {
        return clientsList.stream().collect(
                groupingBy(
                    Client::profession,
                    mapping(Client::salary, averagingDouble(salary -> salary))
                )
            )
            .entrySet()
            .stream()
            .map(entry -> new GroupResult(entry.getKey(), entry.getValue())).collect(toList());
    }
}
