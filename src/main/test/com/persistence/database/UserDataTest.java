package com.persistence.database;

import com.fishing.guide.Entity.UserData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class UserDataTest {

    GenericDao<UserData> dao;

    @BeforeEach
    void setup() {
        dao = new GenericDao<>(UserData.class);
    }


    @Test
    void getAll() {
        List<UserData> users = dao.getAll();
        Assertions.assertEquals(0, users.size());
    }

    @Test
    void getBy() {}
}
