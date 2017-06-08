package service.impl;

import bot.core.XueqiuCommentJson;
import bot.core.XueqiuCommentParser;
import service.CommentService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * Created by Hermit on 2017/6/7.
 */
public class XueqiuCommentImpl implements CommentService {

    public static final String DEFAULT_URL = "https://xueqiu.com";
    private XueqiuCommentJson crawler;
    private XueqiuCommentParser parser;

    public XueqiuCommentImpl() {
        crawler = new XueqiuCommentJson();
        parser = new XueqiuCommentParser();
    }

    @Override
    public JSONArray getCurrentComments(String ID) {
        String htmlData = crawler.getJSONData(ID);
        JSONArray jsonList = parser.getCommentList(htmlData);
        JSONArray result = new JSONArray();

        for (int i=0;i<jsonList.size();i++) {
            JSONObject item = jsonList.getJSONObject(i);
            JSONObject user = item.getJSONObject("user");
            String title = item.getString("title");
            String target = DEFAULT_URL + item.getString("target");
            String description = item.getString("description");
            String text = item.getString("text");
            String username = user.getString("screen_name");
            String userUrl = DEFAULT_URL + user.getString("profile");

            JSONObject newItem = new JSONObject();
            newItem.put("title", title);
            newItem.put("target", target);
            newItem.put("description", description);
            newItem.put("text", text);
            newItem.put("username", username);
            newItem.put("userUrl", userUrl);

            result.add(newItem);
        }

        return result;
    }
}
