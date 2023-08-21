package dev.ionutb.movies;

public class UserMapper {

    public static UserResponseDTO mapToResponseDTO(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId().toString()); // Convert ObjectId to String
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        dto.setFavList(user.getFavList());
        return dto;
    }
}