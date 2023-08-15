package dev.ionutb.movies;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Document(collection = "movies") //
@Data // all getters setters and string methods
@AllArgsConstructor // Creating constructor that takes all private field as argument
@NoArgsConstructor // Another Constructor that takes no parameters
public class Movie {
    @Id // unique identifier
    private ObjectId id;
    private String imdbId;
    private String title;
    private String releaseDate;
    private  String trailerLink;
    private  String poster;
    private  List<String> genres;
    private List<String> backdrops;
    @DocumentReference  // DB stores only the ids of the review, in separate collection
    private List<Review> reviewIds;


}
