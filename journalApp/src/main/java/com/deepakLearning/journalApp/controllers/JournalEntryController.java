package com.deepakLearning.journalApp.controllers;

import com.deepakLearning.journalApp.entities.Journal;
import com.deepakLearning.journalApp.services.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping
    public List<Journal> getJournals() {
        return journalEntryService.getEntries();
    }

    @PostMapping
    public String createEntry(@RequestBody Journal journal) {
        journal.setDate(new Date());
        journalEntryService.creatEntry(journal);
        return "Status: 200," +
                " New Entry Created Successfully";
    }

    @GetMapping("{journalId}")
    public Journal getJournal(@PathVariable ObjectId journalId) {
        return journalEntryService.getEntry(journalId).orElse(
                null
        );
    }

    @DeleteMapping("{journalId}")
    public String deleteJournal(@PathVariable ObjectId journalId) {
         journalEntryService.deleteEntry(journalId);
         return "Entry with ID: " + journalId + " deleted successfully";
    }

    @PatchMapping("{journalId}")
    public String updateJournal(@PathVariable ObjectId journalId, @RequestBody  Journal journal) {
        Journal response = journalEntryService.getEntry(journalId).orElse(null);
        if (response!=null){
            response.setTitle(journal.getTitle() != null && !journal.getTitle().isEmpty() ? journal.getTitle() : response.getTitle());
            response.setDescription(journal.getDescription() != null && !journal.getDescription().isEmpty() ? journal.getDescription() : response.getDescription());
        }
        journalEntryService.creatEntry(response);
        return "Status: 200," +
                "Updated Successfully";
    }
}
