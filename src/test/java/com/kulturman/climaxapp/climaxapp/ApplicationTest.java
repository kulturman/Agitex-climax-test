package com.kulturman.climaxapp.climaxapp;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class ApplicationTest implements FileParser {
    @Mock
    private FileParserResolverInterface fileParserResolver;

    private static List<Client> clientsList;

    private static File file;

    private ApplicationService applicationService;

    @BeforeAll
    public static void beforeAll() {
        file = new File("fakeFile.xml");

        clientsList = Arrays.asList(
            new Client("UCHIHA", "Itachi", 30, "shinobi", 200000),
            new Client("ZOUS", "Adrien", 30, "informaticien", 35000),
            new Client("BAKYONO", "Arnaud", 30, "informaticien", 45000)
        );
    }

    @BeforeEach
    void beforeEach() {
        when(fileParserResolver.resolve(file)).thenReturn(this);
        applicationService = new ApplicationService(fileParserResolver);
    }

    @Test
    void getClientsList() throws FileParserException {
        List<Client> clients = applicationService.getClientsList(file);

        assertEquals(3, clients.size());
        assertClientsListMatches(clientsList, clients);
    }

    @Test
    void calculateMeanByProfession() throws FileParserException {
        var result = applicationService.getMeanByProfession(applicationService.getClientsList(file));
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
    public List<Client> getFileContent(File file) {
        return Arrays.asList(
            new Client("UCHIHA", "Itachi", 30, "shinobi", 200000),
            new Client("ZOUS", "Adrien", 30, "informaticien", 35000),
            new Client("BAKYONO", "Arnaud", 30, "informaticien", 45000)
        );
    }
}
