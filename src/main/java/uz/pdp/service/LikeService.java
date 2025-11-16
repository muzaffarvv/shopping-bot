package uz.pdp.service;

import uz.pdp.base.BaseService;
import uz.pdp.model.Like;
import uz.pdp.utils.FileUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LikeService implements BaseService<Like> {

    private static final String FILE_NAME_LIKE = "likes.json";
    List<Like> likes;

    public LikeService() {
        try {
            likes = loadFromFile();
        } catch (IOException e) {
            likes = new ArrayList<>();
            System.out.println("Failed to load likes from file: " + e.getMessage());
        }
    }

    @Override
    public void add(Like like) {

    }

    @Override
    public Like get(UUID id) {
        return null;
    }

    @Override
    public boolean update(Like like) {
        return false;
    }

    @Override
    public void delete(Like like) {

    }

    private void save() throws IOException {
        FileUtils.writeToJson(FILE_NAME_LIKE, likes);
    }

    private List<Like> loadFromFile() throws IOException {
        return FileUtils.readFromJson(FILE_NAME_LIKE, Like.class);
    }
}
