package service.impl;

import bot.core.XueqiuCommentJson;
import bot.core.XueqiuCommentParser;
import model.Comment;
import service.CommentService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import tools.StockHelper;

import java.util.ArrayList;
import java.util.List;

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
    public List<Comment> getCurrentComments(String ID) {
        ID = StockHelper.xueqiuCodeReflect(ID);
        String htmlData = crawler.getJSONData(ID);
        JSONArray jsonList = parser.getCommentList(htmlData);
        List<Comment> commentList = new ArrayList<>();

        for (int i=0;i<jsonList.size();i++) {
            JSONObject item = jsonList.getJSONObject(i);
            JSONObject user = item.getJSONObject("user");
            String title = item.getString("title");
            String target = DEFAULT_URL + item.getString("target");
            String description = item.getString("description");
            String text = item.getString("text");
            String username = user.getString("screen_name");
            String userUrl = DEFAULT_URL + user.getString("profile");

            Comment comment = new Comment();
            comment.setTitle(title);
            comment.setTarget(target);
            comment.setDescription(description);
            comment.setText(text);
            comment.setUsername(username);
            comment.setUserUrl(userUrl);

            commentList.add(comment);
        }

        return commentList;
    }
}
