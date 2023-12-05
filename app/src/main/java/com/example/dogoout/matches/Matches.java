package com.example.dogoout.matches;

import com.example.dogoout.domain.user.User;

import java.util.ArrayList;

public class Matches {

    private static ArrayList<User> matches = new ArrayList<>();

    public static void addMatch(User user) {
        if (!matches.contains(user)) {
            matches.add(user);
        }
    }
    public static ArrayList<User> getMatches() {
        return matches;
    }
}
