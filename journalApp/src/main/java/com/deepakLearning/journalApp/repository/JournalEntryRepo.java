package com.deepakLearning.journalApp.repository;

import com.deepakLearning.journalApp.entities.Journal;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalEntryRepo extends MongoRepository<Journal, ObjectId> {
}
