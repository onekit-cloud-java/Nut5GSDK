package cn.onekit.cloud.nut5gsdk;

import cn.onekit.cloud.nut5g.Nut5GAPI;
import cn.onekit.cloud.nut5g.Nut5GError;
import cn.onekit.cloud.nut5g.request.*;
import cn.onekit.cloud.nut5g.response.*;
import cn.onekit.thekit.AJAX;
import cn.onekit.thekit.JSON;
import com.google.gson.JsonObject;

import java.util.HashMap;



public class Nut5GSDK implements Nut5GAPI {
    private final String host;

    public Nut5GSDK(String host){
        this.host=host;
    }


    @Override
    public AccessTokenResponse accessToken(AccessTokenRequest accessTokenRequest) throws Nut5GError {
        JsonObject result = null;
        try {
            String url = String.format("%s/accessToken",host);
            AJAX.headers = new HashMap<String,String>(){{
                put("Content-Type","application/json");
            }};
            JsonObject post_body = (JsonObject) JSON.object2json(accessTokenRequest);
            result = (JsonObject) JSON.parse(AJAX.request(url,"post", post_body.toString()));

            return JSON.json2object(result,AccessTokenResponse.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }


    @Override
    public void chatBotInfo(String accessToken,ChatBotInfoRequest chatBotInfoRequest) throws Nut5GError {
        try {
        String url = String.format("%s/update/chatBotInfo/optionals",host);
        AJAX.headers = new HashMap<String,String>(){{
            put("Content-Type","application/json");
            put("Authorization",accessToken);
        }};
        JsonObject post_body = (JsonObject) JSON.object2json(chatBotInfoRequest);
        AJAX.request(url,"post", post_body.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void chatBotInfomenu(ChatBotInfomenuRequest chatBotInfomenuRequest) throws Nut5GError {
        try {
            String url = String.format("%s/update/chatBotInfo/menu",host);
            AJAX.headers = new HashMap<String,String>(){{
                put("Content-Type","application/json");
            }};
            JsonObject post_body = (JsonObject) JSON.object2json(chatBotInfomenuRequest);
            AJAX.request(url,"post", post_body.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public FindchatBotInfoResponse findchatBotInfo() throws Nut5GError {
        JsonObject result = null;
        try {
            String url = String.format("%s/find/chatBotInfo",host);
//            AJAX.headers = new HashMap<String,String>(){{
//                put("Content-Type","application/json");
//            }};
            result = (JsonObject) JSON.parse(AJAX.request(url,"post", ""));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSON.json2object(result,FindchatBotInfoResponse.class);
    }

    @Override
    public MediasuploadResponse mediasupload(byte[] bytes) throws Nut5GError {
        JsonObject result = null;
        try {
            String url = String.format("%s/medias/upload",host);
            AJAX.headers = new HashMap<String,String>(){{
                put("Content-Type","multipart/form-data");
            }};
            result = (JsonObject) JSON.parse(AJAX.upload(url,bytes));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSON.json2object(result,MediasuploadResponse.class);
    }

    @Override
    public byte[] mediasdownload(String s) throws Nut5GError {
        byte[] result = null;
        try {
            String url = String.format("%s/medias/download",host);
            AJAX.headers = new HashMap<String,String>(){{
                put("Content-Type","multipart/form-data");
            }};
            result = AJAX.download(url, "post", s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public MediasdeleteResponse mediasdelete(String s) throws Nut5GError {
        JsonObject result = null;
        try {
            String url = String.format("%s/medias/delete",host);
//            AJAX.headers = new HashMap<String,String>(){{
//                put("Content-Type","application/json");
//            }};
            result = (JsonObject) JSON.parse(AJAX.request(url,"post", s));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSON.json2object(result,MediasdeleteResponse.class);
    }

    @Override
    public MessagesResponse messages(MessagesRequest messagesRequest) throws Nut5GError {
        JsonObject result = null;
        try {
            String url = String.format("%s/messages",host);
            AJAX.headers = new HashMap<String,String>(){{
                put("Content-Type","application/json");
            }};
            JsonObject post_body = (JsonObject) JSON.object2json(messagesRequest);
            result = (JsonObject) JSON.parse(AJAX.request(url,"post", post_body.toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSON.json2object(result,MessagesResponse.class);
    }

    @Override
    public MessagesrevokeResponse messagesrevoke(MessagesrevokeRequest messagesrevokeRequest) throws Nut5GError {
        JsonObject result = null;
        try {
            String url = String.format("%s/revoke",host);
            AJAX.headers = new HashMap<String,String>(){{
                put("Content-Type","application/json");
            }};
            JsonObject post_body = (JsonObject) JSON.object2json(messagesrevokeRequest);
            result = (JsonObject) JSON.parse(AJAX.request(url,"post", post_body.toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSON.json2object(result,MessagesrevokeResponse.class);
    }

    @Override
    public void receivemessage(ReceivemessageRequest receivemessageRequest) throws Nut5GError {
        try {
            String url = String.format("%s/messages",host);
            AJAX.headers = new HashMap<String,String>(){{
                put("Content-Type","application/json");
            }};
            JsonObject post_body = (JsonObject) JSON.object2json(receivemessageRequest);
            AJAX.request(url,"post", post_body.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void status(StatusRequest statusRequest) throws Nut5GError {
        try {
            String url = String.format("%s/status",host);
            AJAX.headers = new HashMap<String,String>(){{
                put("Content-Type","application/json");
            }};
            JsonObject post_body = (JsonObject) JSON.object2json(statusRequest);
            AJAX.request(url,"post", post_body.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void informationChange() throws Nut5GError {
        try {
            String url = String.format("%s/notice/informationChange",host);
            AJAX.request(url,"post","");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void rcsspam(RcsspamRequest rcsspamRequest) throws Nut5GError {
        try {
            String url = String.format("%s/notice/rcsspam",host);
            JsonObject post_body = (JsonObject) JSON.object2json(rcsspamRequest);
            AJAX.request(url,"post", post_body.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void check(CheckRequest checkRequest) throws Nut5GError {
        try {
            String url = String.format("%s/check",host);
            JsonObject post_body = (JsonObject) JSON.object2json(checkRequest);
            AJAX.request(url,"post", post_body.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
