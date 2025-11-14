package com.ranchopro.journalApp.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@Data
@Document(collection = "cofig_journal_app")
public class ConfigJournalApp {
    private String key;
    private String value;
}
