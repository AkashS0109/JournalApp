package com.ranchopro.journalApp.entity;

import com.ranchopro.journalApp.enums.Sentiment;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

// document is written here bcz this is object that will map to collection like ORM
@Document(collection = "journal_entries")
@NoArgsConstructor
@Data
public class JournalEntry {

    @Id
    private ObjectId id;
    
    private String title;

    private String content;

    private LocalDateTime date;

    private Sentiment sentiment;


}
