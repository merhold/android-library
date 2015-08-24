/*
Copyright 2009-2015 Urban Airship Inc. All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:

1. Redistributions of source code must retain the above copyright notice, this
list of conditions and the following disclaimer.

2. Redistributions in binary form must reproduce the above copyright notice,
this list of conditions and the following disclaimer in the documentation
and/or other materials provided with the distribution.

THIS SOFTWARE IS PROVIDED BY THE URBAN AIRSHIP INC ``AS IS'' AND ANY EXPRESS OR
IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO
EVENT SHALL URBAN AIRSHIP INC OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.urbanairship.richpush;

import android.support.annotation.NonNull;

import com.urbanairship.Logger;
import com.urbanairship.PreferenceDataStore;
import com.urbanairship.UAirship;
import com.urbanairship.util.UAStringUtil;

/**
 * A local abstraction of an Urban Airship user. It provides getters and
 * setters for user metadata such as tags and an alias. Changes made to this object will
 * be sent to Urban Airship's servers.
 */
public class RichPushUser {

    final RichPushUserPreferences preferences;

    RichPushUser(PreferenceDataStore preferenceDataStore) {
        this.preferences = new RichPushUserPreferences(preferenceDataStore);
    }

    /**
     * Returns whether the user has been created.
     *
     * @return <code>true</code> if the user has an id, <code>false</code> otherwise.
     */
    public static boolean isCreated() {
        UAirship airship = UAirship.shared();
        String userId = airship.getRichPushManager().getRichPushUser().getId();
        String userToken = airship.getRichPushManager().getRichPushUser().getPassword();
        return (!UAStringUtil.isEmpty(userId) && !UAStringUtil.isEmpty(userToken));
    }

    /**
     * Updates the user
     *
     * @param userId The user ID from the response
     * @param userToken The user token from the response
     */
    void setUser(@NonNull String userId, @NonNull String userToken) {
        Logger.debug("RichPushUser - Setting Rich Push user: " + userId);
        preferences.setUserCredentials(userId, userToken);
    }

    /**
     * Get the user's ID.
     *
     * @return A user ID String or null if it doesn't exist.
     */
    public String getId() {
        return preferences.getUserId();
    }

    /**
     * Get the user's token used for basic auth.
     *
     * @return A user token String.
     */
    public String getPassword() {
        return preferences.getUserToken();
    }
}