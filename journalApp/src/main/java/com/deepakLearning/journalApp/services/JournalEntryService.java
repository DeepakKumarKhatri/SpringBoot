package com.deepakLearning.journalApp.services;

import com.deepakLearning.journalApp.entities.Journal;
import com.deepakLearning.journalApp.entities.User;
import com.deepakLearning.journalApp.repository.JournalEntryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepo journalEntryRepo;

    @Autowired
    private UserService userService;

    @Transactional
    public void creatEntry(Journal journal, String userName){
        try {
            User user = userService.findByUserName(userName);
            journal.setDate(new Date());
            Journal savedJournal = journalEntryRepo.save(journal);
            user.getJournalList().add(savedJournal);
            userService.creatUser(user);
        }catch (Exception e){
            throw new RuntimeException("An error occurred: ", e);
        }
    }

    public void creatEntry(Journal journal){
        journalEntryRepo.save(journal);
    }

    public List<Journal> getEntries(){
        return journalEntryRepo.findAll();
    }

    public Optional<Journal> getEntry(ObjectId entryId){
        return journalEntryRepo.findById(entryId);
    }

    public void deleteEntry(ObjectId entryId, String userName){
        User user = userService.findByUserName(userName);
        user.getJournalList().removeIf(journal -> journal.getId().equals(entryId));
        userService.creatUser(user);
        journalEntryRepo.deleteById(entryId);
    }

    public void updateEntry(Journal journal){
    }

}
