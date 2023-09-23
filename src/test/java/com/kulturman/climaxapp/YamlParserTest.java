package com.kulturman.climaxapp;

import com.kulturman.climaxapp.domain.Client;
import com.kulturman.climaxapp.domain.FileParserException;
import com.kulturman.climaxapp.infra.YamlFileParser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class YamlParserTest {
    @Autowired
    private ResourceLoader resourceLoader;

    @Test
    void getFileContent() throws IOException, FileParserException {
        var fileParser = new YamlFileParser();
        var resource = resourceLoader.getResource("classpath:static/clients.yaml");
        var clients = fileParser.getFileContent(resource.getFile());

        assertEquals(
            new Client("LUPIN", "Arsène", 30, "cambrioleur", 500000),
            clients.get(0)
        );

        assertEquals(
            new Client("HOLMES", "Sherlock", 45, "detective", 33000),
            clients.get(1)
        );
    }
}
