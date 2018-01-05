package controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;

import controller.account.AccountDeleteAction;
import controller.account.AccountEditAction;
import controller.account.AccountListAction;
import controller.account.AccountSaveAction;
import controller.account.AccountViewAction;
import controller.user.UserDeleteAction;
import controller.user.UserEditAction;
import controller.user.UserListAction;
import controller.user.UserSaveAction;

public class ActionFactory {
    private static Map<String, Class<? extends Action>> actions = new HashMap<>();
    static {
        actions.put("/", MainAction.class);
        actions.put("/index", MainAction.class);
        actions.put("/user/list", UserListAction.class);
        actions.put("/user/edit", UserEditAction.class);
        actions.put("/user/save", UserSaveAction.class);
        actions.put("/user/delete", UserDeleteAction.class);
        actions.put("/account/list", AccountListAction.class);
        actions.put("/account/view", AccountViewAction.class);
        actions.put("/account/edit", AccountEditAction.class);
        actions.put("/account/save", AccountSaveAction.class);
        actions.put("/account/delete", AccountDeleteAction.class);
    }

    public static Action getAction(String url) throws ServletException {
        Class<?> action = actions.get(url);
        try {
            return (Action)action.newInstance();
        } catch(InstantiationException | IllegalAccessException | NullPointerException e) {
            throw new ServletException(e);
        }
    }
}
