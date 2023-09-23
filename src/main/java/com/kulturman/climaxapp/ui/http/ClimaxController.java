package com.kulturman.climaxapp.ui.http;

import com.kulturman.climaxapp.domain.ApplicationService;
import com.kulturman.climaxapp.domain.FileParserException;
import com.kulturman.climaxapp.domain.FileParserResolverInterface;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;

@Controller
public class ClimaxController {
    private final FileParserResolverInterface fileParserResolver;
    private final ResourceLoader resourceLoader;


    public ClimaxController(
        FileParserResolverInterface fileParserResolver,
        ResourceLoader resourceLoader
    ) {
        this.fileParserResolver = fileParserResolver;
        this.resourceLoader = resourceLoader;
    }

    @GetMapping("/{fileType}")
    public ResponseEntity<?> getFileContent(@PathVariable("fileType") String extension) throws IOException, FileParserException {
        ApplicationService applicationService = new ApplicationService(fileParserResolver);

        return ResponseEntity.ok(applicationService.getClientsList(resourceLoader.getResource(
            String.format("classpath:static/clients.%s", extension)
        ).getFile()));
    }
}
