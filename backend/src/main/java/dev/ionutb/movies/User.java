package dev.ionutb.movies;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;


@Document(collection = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private ObjectId id;
    private String username;
    private String password;
    private List<String > favList = new ArrayList<>();

    public String getUsername() {
        return username;
    }

    public ObjectId getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }
    public List<String> getFavList() {
        return favList;
    }

    public void setFavList(List<String> favList) {
        this.favList = favList;
    }

}
