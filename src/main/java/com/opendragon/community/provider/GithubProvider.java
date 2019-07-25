package com.opendragon.community.provider;

import com.alibaba.fastjson.JSON;
import com.opendragon.community.dto.AccessTokenDTO;
import com.opendragon.community.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author opend
 * @version 1.0
 * @date 2019/7/25
 */
@Component
public class GithubProvider {
    /**
     * 通过 AccessTokenDTO 获取用户 github 的 access token
     * @param accessTokenDTO
     * @return 用户 github 的 access token
     */
    public String getAccessToken(AccessTokenDTO accessTokenDTO){
        final MediaType mediaType
                = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON.toJSONString(accessTokenDTO), mediaType);
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String str = response.body().string();
            String accessToken = str.split("&")[0].split("=")[1];
            return accessToken;
        } catch (IOException e) {
        }

        return null;
    }

    /**
     * 通过用户的 access token 获取用户 github 上的用户信息。
     * @param accessToken
     * @return 保持用户 github 信息的对象。
     */
    public GithubUser getUser(String accessToken){
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+accessToken)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return JSON.parseObject(response.body().string(), GithubUser.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}

