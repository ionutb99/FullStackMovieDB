package dev.ionutb.movies;

import java.util.ArrayList;
import java.util.List;

public class UserResponseDTO {
    private String id; // Use String instead of ObjectId
    private String username;
    private String password;
    private List<String> favList = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getFavList() {
        return favList;
    }

    public void setFavList(List<String> favList) {
        this.favList = favList;
    }
}
