package com.kulturman.climaxapp.infra;

import com.kulturman.climaxapp.domain.FileParser;
import com.kulturman.climaxapp.domain.FileParserException;
import com.kulturman.climaxapp.domain.FileParserResolverInterface;
import com.kulturman.climaxapp.domain.PARSER_TYPE;

import java.io.File;
import java.lang.reflect.InvocationTargetException;

public class ExtensionFileResolver implements FileParserResolverInterface {
    @Override
    public FileParser resolve(File file) throws FileParserException {
        var extension = file.getName().substring(file.getName().lastIndexOf(".") + 1);

        try {
            return PARSER_TYPE.getFileParser(extension).getDeclaredConstructor().newInstance();
        } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            throw new FileParserException(e.getMessage());
        }
    }
}
