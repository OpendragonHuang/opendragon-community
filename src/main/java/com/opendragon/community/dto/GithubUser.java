package com.opendragon.community.dto;

/**
 * @author opend
 * @version 1.0
 * @date 2019/7/25
 */
public class GithubUser {
    private String login;
    private long id;
    private String name;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("[login:%s, id:%d, name=%s]", login, id, name);
    }
}

