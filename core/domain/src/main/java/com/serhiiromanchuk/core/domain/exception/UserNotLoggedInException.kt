package com.serhiiromanchuk.core.domain.exception

class UserNotLoggedInException : Exception("User is not logged in, unable to check session expiration.")