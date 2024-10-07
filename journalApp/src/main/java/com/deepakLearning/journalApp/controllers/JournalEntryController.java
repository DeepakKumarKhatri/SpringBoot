package com.deepakLearning.journalApp.controllers;

import com.deepakLearning.journalApp.entities.Journal;
import com.deepakLearning.journalApp.entities.User;
import com.deepakLearning.journalApp.services.JournalEntryService;
import com.deepakLearning.journalApp.services.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> getAllJournalEntriesOfUser()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User currentUser = userService.findByUserName(userName);
        if (currentUser!=null) {
            List<Journal> journalList = currentUser.getJournalList();
            if (journalList!=null && !journalList.isEmpty()) {
                return new ResponseEntity<>(journalList,HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(new User[0],HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<String> createEntry(@RequestBody Journal journal) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            System.out.println(authentication);
            String userName = authentication.getName();
            journalEntryService.createNewEntry(journal,userName);
            return new ResponseEntity<> (
                    "New Entry Created Successfully", HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<> (
                    "Failed to create new Entry", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("{journalId}")
    public ResponseEntity<Journal> getJournal(@PathVariable ObjectId journalId) {
        Optional<Journal> journal = journalEntryService.getEntry(journalId);
        if (journal.isPresent()) {
            return new ResponseEntity<> (journal.get(), HttpStatus.OK);
        }
        return new ResponseEntity<> (HttpStatus.OK);
    }

    @DeleteMapping("{journalId}")
    public ResponseEntity<?> deleteJournal(@PathVariable ObjectId journalId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
         journalEntryService.deleteEntry(journalId,userName);
        return new ResponseEntity<> ("Entry with ID: " + journalId + " deleted successfully", HttpStatus.OK);
    }

    @PutMapping("{journalId}")
    public ResponseEntity<?> updateJournal(
            @PathVariable("journalId") String journalId,
            @RequestBody Journal journal
    ) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            User user = userService.findByUserName(userName);
            System.out.println(user);
            if (user == null) {
                return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
            }

            // Check if the journalId belongs to this user
            ObjectId journalIdTyped = new ObjectId(journalId);

            Journal journalToUpdate = user.getJournalList().stream()
                    .filter(j -> j.getId().equals(journalIdTyped))
                    .findFirst()
                    .orElse(null);

            if (journalToUpdate == null) {
                return new ResponseEntity<>("Journal not found for this user", HttpStatus.FORBIDDEN);
            }

            // Fetch the journal entry
            Journal existingJournal = journalEntryService.getEntry(journalIdTyped).orElse(null);
            if (existingJournal == null) {
                return new ResponseEntity<>("Journal not found", HttpStatus.NOT_FOUND);
            }

            // Update the journal fields
            existingJournal.setTitle(!journal.getTitle().isEmpty() ? journal.getTitle() : existingJournal.getTitle());
            existingJournal.setDescription(journal.getDescription() != null && !journal.getDescription().isEmpty() ? journal.getDescription() : existingJournal.getDescription());

            // Save the updated journal
            journalEntryService.createEntry(existingJournal);

            return new ResponseEntity<>("Updated Successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error Occurred while updating", HttpStatus.BAD_REQUEST);
        }
    }
}
