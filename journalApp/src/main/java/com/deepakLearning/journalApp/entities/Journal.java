package com.deepakLearning.journalApp.entities;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document("journal_entries")
@Data
@NoArgsConstructor
public class Journal {
    @JsonSerialize(using= ToStringSerializer.class)
    private ObjectId id;
    @NonNull
    private String title;
    private String description;
    private Date date;
}
