package com.example.translator1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Translator1Application implements CommandLineRunner {

    private static Logger LOG = LoggerFactory.getLogger(Translator1Application.class);
    public static void main(String[] args) {
        LOG.info("STARTING THE APPLICATION");
        SpringApplication.run(Translator1Application.class, args);
        LOG.info("APPLICATION FINISHED");
    }
    @Override
    public void run(String... args) throws Exception{
        LOG.info("EXECUTING : command line runner");
        for (int i = 0; i < args.length; ++i) {
            LOG.info("args[{}]: {}", i, args[i]);
        }
    }

}
