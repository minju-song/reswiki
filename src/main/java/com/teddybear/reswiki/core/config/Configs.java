package com.teddybear.reswiki.core.config;

import java.util.Collections;
import java.util.List;

public class Configs {
    public final static List<String > CORS = Collections.unmodifiableList(
            List.of("http://localhost:3000",
                    "http://localhost:9892",
                    "http://172.30.1.44:3000")
    );
}
