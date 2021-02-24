package cn.onekit.cloud.nut5gsdk;

import cn.onekit.cloud.nut5g.Nut5GAPI;
import cn.onekit.cloud.nut5g.Nut5GError;
import cn.onekit.cloud.nut5g.request.*;
import cn.onekit.cloud.nut5g.response.*;
import cn.onekit.thekit.AJAX;
import cn.onekit.thekit.JSON;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;


public class Nut5GSDK implements Nut5GAPI {
    private final String host;

    public Nut5GSDK(String host){
        this.host=host;
    }

    void _init(String accessToken,  Map<String,String> headers) {
        if (headers == null) {
            headers = new HashMap<>();
        }

        if (accessToken != null) {
            headers.put("Authorization", String.format("accessToken %s", accessToken));
        }
        AJAX.headers = headers;
    }
    void _init(String accessToken) {
        _init(accessToken,new HashMap<String,String>(){{
            put("Content-Type","application/json");
        }});
    }
    @Override
    public AccessTokenResponse accessToken(AccessTokenRequest accessTokenRequest) throws Nut5GError {
        try {
            String url = String.format("%s/accessToken", host);
            _init(null);
            JsonObject post_body = (JsonObject) JSON.object2json(accessTokenRequest);
            /////////////////////////////////////////
            JsonObject result = (JsonObject) JSON.parse(AJAX.request(url, "post", post_body.toString()));
            if (result.get("errorCode").getAsInt() != 0) {
                throw JSON.json2object(result, Nut5GError.class);
            }
            //////////////////////////////////////
            return JSON.json2object(result, AccessTokenResponse.class);
        } catch (Nut5GError e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Nut5GError();
        }
    }

    @Override
    public void chatBotInfo(String accessToken,ChatBotInfoRequest chatBotInfoRequest) throws Nut5GError {
        try {
        String url = String.format("%s/update/chatBotInfo/optionals",host);
            _init(accessToken);
        JsonObject post_body = (JsonObject) JSON.object2json(chatBotInfoRequest);
        AJAX.request(url,"post", post_body.toString());
        } catch (Exception e) {
            e.printStackTrace();
            throw new Nut5GError();
        }

    }

    @Override
    public void chatBotInfomenu(String accessToken,ChatBotInfomenuRequest chatBotInfomenuRequest) throws Nut5GError {
        try {
            String url = String.format("%s/update/chatBotInfo/menu",host);
            _init(accessToken);
            JsonObject post_body = (JsonObject) JSON.object2json(chatBotInfomenuRequest);
            AJAX.request(url,"post", post_body.toString());
        } catch (Exception e) {
            e.printStackTrace();
            throw new Nut5GError();
        }

    }

    @Override
    public FindchatBotInfoResponse findchatBotInfo(String accessToken) throws Nut5GError {
        try {
            String url = String.format("%s/find/chatBotInfo",host);
            _init(accessToken);
            JsonObject result = (JsonObject) JSON.parse(AJAX.request(url));
            return JSON.json2object(result,FindchatBotInfoResponse.class);
        } catch (Nut5GError e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Nut5GError();
        }
    }

    @Override
    public MediasuploadResponse mediasupload(String accessToken,String uploadMode, Map<String,byte[]> files) throws Nut5GError {
        try {
            String url = String.format("%s/medias/upload",host);
            _init(accessToken,new HashMap<String, String>(){{
                put("UploadMode",uploadMode);
            }});
            JsonObject result = (JsonObject) JSON.parse(AJAX.upload(url,files));
            if (result.get("errorCode").getAsInt() != 0) {
                throw JSON.json2object(result, Nut5GError.class);
            }
            ///////////////////////////////
            return JSON.json2object(result,MediasuploadResponse.class);
        } catch (Nut5GError e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Nut5GError();
        }

    }

    @Override
    public byte[] mediasdownload(String accessToken,String downloadurl) throws Nut5GError {
        try {
            String url = String.format("%s/medias/download",host);
            _init(accessToken,new HashMap<String, String>(){{
                put("url",downloadurl);
            }});
            return AJAX.download(url, "get","");
        } catch (Nut5GError e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Nut5GError();
        }
    }

    @Override
    public MediasdeleteResponse mediasdelete(String accessToken,String deleteurl) throws Nut5GError {
        try {
            JsonObject result = null;
            String url = String.format("%s/medias/delete",host);
            _init(accessToken,new HashMap<String, String>(){{
                put("url",deleteurl);
            }});
            result = (JsonObject) JSON.parse(AJAX.request(url,"delete",""));
            if (result.get("errorCode").getAsInt() != 0) {
                throw JSON.json2object(result, Nut5GError.class);
            }
            return JSON.json2object(result,MediasdeleteResponse.class);
        } catch (Nut5GError e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Nut5GError();
        }

    }

    @Override
    public MessagesResponse messages(String accessToken,MessagesRequest messagesRequest) throws Nut5GError {
        try {
            JsonObject result = null;
            String url = String.format("%s/messages",host);
            _init(accessToken,new HashMap<String, String>(){{
                put("Content-Type","application/json");
            }});
            JsonObject post_body = (JsonObject) JSON.object2json(messagesRequest);
            result = (JsonObject) JSON.parse(AJAX.request(url,"post", post_body.toString()));
            if (result.get("errorCode").getAsInt() != 0) {
                throw JSON.json2object(result, Nut5GError.class);
            }
            return JSON.json2object(result,MessagesResponse.class);
        } catch (Nut5GError e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Nut5GError();
        }

    }

    @Override
    public MessagesrevokeResponse messagesrevoke(String accessToken,MessagesrevokeRequest messagesrevokeRequest) throws Nut5GError {

        try {
            JsonObject result = null;
            String url = String.format("%s/revoke",host);
            _init(accessToken,new HashMap<String, String>(){{
                put("Content-Type","application/json");
            }});
            JsonObject post_body = (JsonObject) JSON.object2json(messagesrevokeRequest);
            result = (JsonObject) JSON.parse(AJAX.request(url,"post", post_body.toString()));
            return JSON.json2object(result,MessagesrevokeResponse.class);
        } catch (Nut5GError e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Nut5GError();
        }

    }



}
