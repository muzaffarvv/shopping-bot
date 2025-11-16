package uz.pdp.service;

import uz.pdp.base.BaseService;
import uz.pdp.model.User;
import uz.pdp.utils.FileUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserService implements BaseService<User> {

    private static final String FILE_NAME = "users.xml";
    List<User> users;

    public UserService() {
        try {
            users = loadFromFile();
        } catch (IOException e) {
            users = new ArrayList<>();
            System.err.println("Failed to load users from the file: " + e.getMessage());
        }
    }

    @Override
    public void add(User user) throws IOException {
        if (!hasUser(user)) {
            users.add(user);
        }
        save();
    }

    @Override
    public User get(UUID id) {
        return getById(id);
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public void delete(User user) {

    }

    public User getById(UUID id) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }


    //  TODO usersni Map qilish kerak Map bilan ishlashni o'rganish kerak

    private boolean hasUser(User user) {
        User targetUser = getById(user.getId());
        return targetUser != null && targetUser.isActive();
    }

    private void save() throws IOException {
        FileUtils.writeToXml(FILE_NAME, users);
    }

    private List<User> loadFromFile() throws IOException {
        return FileUtils.readFromXml(FILE_NAME, User.class);
    }


}
