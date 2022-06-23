package com.example.cinema.dataloader;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequiredArgsConstructor
public class DataLoader {

    private final DataGenerator dataLoader;

    @PostConstruct
    public void initData() {
        dataLoader.generateTestData();
    }
}
