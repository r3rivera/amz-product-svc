package com.boogeyman.app.model;

import java.util.UUID;

public record AccountUserResult(UUID acctId, String firstName, String lastName) {
}
