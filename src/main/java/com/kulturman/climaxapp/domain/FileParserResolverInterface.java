package com.kulturman.climaxapp.domain;

import java.io.File;

public interface FileParserResolverInterface {
    FileParser resolve(File file) throws FileParserException;
}
