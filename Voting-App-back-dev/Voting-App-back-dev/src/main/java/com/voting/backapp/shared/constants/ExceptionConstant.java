package com.voting.backapp.shared.constants;

import org.springframework.http.HttpStatus;

public class ExceptionConstant {

    public static final String USER_ALREADY_EXIST = "User already exists";
    public static final String USER_NOT_FOUND = "User not found";
    public static final String ERROR = "Error";

    public static final String INTERNAL_SERVER_ERROR = "Internal server error";
    public static final String CODE_INTERNAL_SERVER_ERROR = "500";

    public static final String BAD_REQUEST = "Bad request";
    public static final String CODE_BAD_REQUEST = "400";

    public static final String NOT_FOUND = "Not found";
    public static final String CODE_NOT_FOUND = "404";

    public static final String UNAUTHORIZED = "Unauthorized";
    public static final String CODE_UNAUTHORIZED = "401";

    public static final String FORBIDDEN = "Forbidden";
    public static final String CODE_FORBIDDEN = "403";

    public static final String CONFLICT = "Conflict";
    public static final String CODE_CONFLICT = "409";

    public static final String USERS_BY_TYPE_NOT_FOUND = "Users by type not found";
    public static final String ERROR_DELETING_USER = "Error deleting user";
    public static final String USER_MUST_HAVE_ROLE = "User must have role";
    public static final String ROLE_NOT_FOUND = "Role not found";
    public static final String CANDIDATE_ALREADY_EXISTS_FOR_POST = "User is already a candidate for this post";
    public static final String POST_NOT_FOUND = "Post not found";
    public static final String CANDIDATE_NOT_FOUND_FOR_POST = "Candidate not found for this post";
    public static final String POST_ALREADY_EXISTS = "Post already exists";
}
