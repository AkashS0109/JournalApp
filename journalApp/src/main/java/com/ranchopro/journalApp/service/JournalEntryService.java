package com.ranchopro.journalApp.service;

import com.ranchopro.journalApp.entity.JournalEntry;
import com.ranchopro.journalApp.entity.User;
import com.ranchopro.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {
    //dependency injection
    @Autowired
    private JournalEntryRepository journalEntryRepository;


    @Autowired
    private UserService userService;


    //create
    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName) {
        try {
            User user = userService.findByUserName(userName);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            user.getJournalEntriesList().add(saved);
//       user.setUserName(null);
            userService.saveUser(user);
        } catch (Exception e) {

            throw new RuntimeException("An Error occurred while saving Entry");
        }
    }

    public void saveEntry(JournalEntry journalEntry) {
        journalEntryRepository.save(journalEntry);
    }

    //get all entries
    public List<JournalEntry> getAll() {
        return journalEntryRepository.findAll();
    }


    //find byid
    public Optional<JournalEntry> findById(ObjectId Id) {
        return journalEntryRepository.findById(Id);
    }


    // delete byId
    @Transactional
    public boolean deleteByID(ObjectId id, String userName) {
        boolean removed = false;
        try {
            User user = userService.findByUserName(userName);
            Boolean remove = user.getJournalEntriesList().removeIf(x -> x.getId().equals(id));
            if (remove) {
                userService.saveUser(user);
                journalEntryRepository.deleteById(id);
            }
        } catch (Exception e) {
            throw new RuntimeException("An Error Occured while Deleting", e);
        }
        return removed;
    }

    //update by id
    public Optional<JournalEntry> updateByID(ObjectId id, JournalEntry myData) {
        return journalEntryRepository.findById(id);
    }


}
