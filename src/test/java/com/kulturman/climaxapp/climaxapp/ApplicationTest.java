package com.kulturman.climaxapp.climaxapp;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ApplicationTest implements FileParser {
    @Mock
    private FileParserResolverInterface fileParserResolver;

    private static List<Client> clientsList;

    private final String  filePath = "file.csv";

    @BeforeAll
    public static void beforeAll() {
        clientsList = Arrays.asList(
            new Client("UCHIHA", "Itachi", 30, "shinobi", 200000),
            new Client("ZOUS", "Adrien", 30, "informaticien", 35000),
            new Client("BAKYONO", "Arnaud", 30, "informaticien", 45000)
        );
    }
    @Test
    void getClientsList() {
        when(fileParserResolver.resolve(filePath)).thenReturn(this);
        var applicationService = new ApplicationService(fileParserResolver);

        List<Client> clients = applicationService.getClientsList(filePath);

        assertEquals(3, clients.size());
        assertClientsListMatches(clientsList, clients);
    }

    @Test
    void calculateMeanByProfession() {
        when(fileParserResolver.resolve(filePath)).thenReturn(this);
        var applicationService = new ApplicationService(fileParserResolver);

        var result = applicationService.getMeanByProfession(applicationService.getClientsList(filePath));
        assertEquals(result.get(0), new GroupResult("shinobi", 200000));
        assertEquals(result.get(1), new GroupResult("informaticien", 40000));
    }

    private void assertClientsListMatches(List<Client> expectedClients, List<Client> clients) {
        for(int i = 0; i < expectedClients.size(); i++) {
            assertEquals(expectedClients.get(i), clients.get(i));
        }
    }


    //Self shunt test, so I don't need to mock or create a concrete implem of FileParser
    @Override
    public List<Client> getFileContent(String filePath) {
        return Arrays.asList(
            new Client("UCHIHA", "Itachi", 30, "shinobi", 200000),
            new Client("ZOUS", "Adrien", 30, "informaticien", 35000),
            new Client("BAKYONO", "Arnaud", 30, "informaticien", 45000)
        );
    }
}
