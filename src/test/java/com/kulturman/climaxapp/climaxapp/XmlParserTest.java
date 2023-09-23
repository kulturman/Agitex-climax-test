package com.kulturman.climaxapp.climaxapp;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.io.File;

class XmlParserTest {
    @Test
    void getFileContent() {
        var fileParser = new XmlFileParser();
        var clients = fileParser.getFileContent(new File("clients.xml"));

        assertEquals(
            clients.get(0),
            new Client("UCHIHA", "Itachi", 30, "shinobi", 200000)
        );
    }
}
