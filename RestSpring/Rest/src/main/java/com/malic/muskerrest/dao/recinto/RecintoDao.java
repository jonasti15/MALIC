package com.malic.muskerrest.dao.recinto;

import com.malic.muskerrest.entities.Recinto;

import java.util.List;

public interface RecintoDao {

    List<Recinto> getAllRecinto();
    Recinto getRecinto(int id);
    void editRecinto(Recinto recinto);
    void deleteRecinto(Recinto recinto);
    void deleteRecinto(int id);
    void addRecinto(Recinto recinto);
}
