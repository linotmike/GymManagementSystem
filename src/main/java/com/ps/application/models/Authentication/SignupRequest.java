package com.ps.application.models.Authentication;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ps.application.models.AppUser;
import com.ps.application.models.Profile;

public class SignupRequest {
    @JsonProperty("user")
    private AppUser user;
    @JsonProperty("profile")
    private Profile profile;

    public SignupRequest() {
    }

    public SignupRequest(AppUser user, Profile profile) {
        this.user = user;
        this.profile = profile;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    @Override
    public String toString() {
        return "SignupRequest{" +
                "user=" + user +
                ", profile=" + profile +
                '}';
    }
}
