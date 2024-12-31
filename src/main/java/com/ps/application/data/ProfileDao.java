package com.ps.application.data;

import com.ps.application.models.Profile;

public interface ProfileDao {
    Profile createProfile (Profile profile);
    Profile getUserById(int profileId);
    Profile updateProfile (int profileId, Profile profile);
}
