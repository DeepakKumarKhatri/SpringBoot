package com.deepakLearning.journalApp.controllers;

import com.deepakLearning.journalApp.entities.Journal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {
    private Map<Long, Journal> journalEntries = new HashMap<>();

    @GetMapping
    public List<Journal> getJournals() {
        return new ArrayList<>(journalEntries.values());
    }

    @PostMapping
    public String createEntry(@RequestBody Journal journal) {
        journalEntries.put(journal.getId(), journal);
        return "Status: 200," +
                " New Entry Created Successfully";
    }

    @GetMapping("{journalId}")
    public Journal getJournal(@PathVariable Long journalId) {
        return journalEntries.get(journalId);
    }

    @DeleteMapping("{journalId}")
    public Journal deleteJournal(@PathVariable Long journalId) {
        return journalEntries.remove(journalId);
    }

    @PatchMapping("{journalId}")
    public Journal updateJournal(@PathVariable Long journalId, @RequestBody  Journal journal) {
        return journalEntries.put(journalId,journal);
    }
}
