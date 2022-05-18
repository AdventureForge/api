package com.adventureforge.adventureservice.utils;

import org.springframework.util.Assert;

public class UserContextHolder {
    private static final ThreadLocal<UserContext> userContext = new ThreadLocal<UserContext>();

    public static UserContext getContext() {
        UserContext context = userContext.get();

        if (context == null) {
            context = createEmptyContext();
            userContext.set(context);
            return userContext.get();
        }
        throw new RuntimeException("No context found");
    }

    public static void setContext(UserContext context) {
        Assert.notNull(context, "Only non-null UserContext instances are permitted");
        userContext.set(context);
    }

    public void unload() {
        userContext.remove();
    }

    public static UserContext createEmptyContext() {
        return new UserContext();
    }
}
