package com.ranchopro.journalApp.cache;

import com.ranchopro.journalApp.entity.ConfigJournalApp;
import com.ranchopro.journalApp.repository.ConfigJournalAppRepo;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class AppCache {
    public Map<String, String> APP_CACHE = new HashMap<>();

    @Autowired
    private ConfigJournalAppRepo configJournalAppRepo;

    @PostConstruct
    public void init() {
        List<ConfigJournalApp> all = configJournalAppRepo.findAll();

        for (ConfigJournalApp configJournalApp : all) {
            APP_CACHE.put(configJournalApp.getKey(), configJournalApp.getValue());
        }

    }

}
