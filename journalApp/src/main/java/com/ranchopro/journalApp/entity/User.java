package com.ranchopro.journalApp.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    private ObjectId id;
    // iy will not do auto indexing by itself so we have to make changes in app.properties file
    @Indexed(unique = true)    //applying unique username property
    @NotNull             // this will apply the not null to username
    private String userName;
    @NotNull          //this will also if anyone will pass th null value then it will throw null pointer exception
    private String password;

    private String email;
    private Boolean sentimentAnalysis;


    @DBRef
    //referencing the entries of journal_entries collection -----> means the  journalEntriesList will take references  of the entries of journal_entries data
    // mean it will only store the  object if of the entries that is stored in journal_entries works as  foreign keys
    private List<JournalEntry> journalEntriesList = new ArrayList<>();
    private List<String> roles;


}
