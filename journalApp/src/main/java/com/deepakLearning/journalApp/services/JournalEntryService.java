package com.deepakLearning.journalApp.services;

import com.deepakLearning.journalApp.entities.Journal;
import com.deepakLearning.journalApp.repository.JournalEntryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepo journalEntryRepo;

    public void creatEntry(Journal journal){
        journalEntryRepo.save(journal);
    }

    public List<Journal> getEntries(){
        return journalEntryRepo.findAll();
    }

    public Optional<Journal> getEntry(ObjectId entryId){
        return journalEntryRepo.findById(entryId);
    }

    public void deleteEntry(ObjectId entryId){
         journalEntryRepo.deleteById(entryId);
    }

    public void updateEntry(Journal journal){
    }

}
