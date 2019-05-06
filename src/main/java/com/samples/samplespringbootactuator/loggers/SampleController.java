package com.samples.samplespringbootactuator.loggers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    private static final Logger LOGGER = LoggerFactory.getLogger( SampleController.class );

    @GetMapping( "/" )
    public ResponseEntity printLog() {

        LOGGER.trace( "printing a useful TRACE log data!!!" );
        LOGGER.debug( "printing a useful DEBUG log data!!!" );
        LOGGER.info( "printing a useful INFO log data!!!" );
        LOGGER.warn( "printing a useful WARN log data!!!" );
        LOGGER.error( "printing a useful ERROR log data!!!" );

        return ResponseEntity
            .ok()
            .headers(
                new HttpHeaders(){{
                    add( "CustomResp", "Successful response" );
                }}
            )
            .build();
    }

}
