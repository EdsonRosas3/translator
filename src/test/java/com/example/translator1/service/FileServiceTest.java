package com.example.translator1.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FileServiceTest {

    @Autowired
    private FileService fileService;

    @Test
    void testOfIntegration() {
        boolean exists = false;
        try {
            exists = fileService.invertFile();

        } catch (IOException e) {
            e.printStackTrace();
        }
        boolean existeImprimirFile=false;
        try {
            existeImprimirFile = fileService.imprimirData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        boolean existsRemplace = false;
        try {
            existsRemplace = fileService.remplaceWord();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertTrue(exists);
        assertTrue(existeImprimirFile);
        assertTrue(existsRemplace);
    }
}