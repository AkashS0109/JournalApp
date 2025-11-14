package com.ranchopro.journalApp.repository;

import com.ranchopro.journalApp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

// this will run query from DB

//MONGO
//mongoRepository is  interface give by default by SpringDataMongoDB
//MongoRepository<JournalEntry,String>  JournalEntity is entity type and string is its id
public interface JournalEntryRepository extends MongoRepository<JournalEntry, ObjectId> {

}
