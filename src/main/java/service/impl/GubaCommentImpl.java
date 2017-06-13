package service.impl;

import bot.core.GubaCommentJson;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import model.Comment;
import org.springframework.stereotype.Service;
import service.CommentService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hermit on 2017/6/13.
 */
public class GubaCommentImpl implements CommentService {

    GubaCommentJson json = new GubaCommentJson();

    @Override
    public List<Comment> getCurrentComments(String ID) {
        List<Comment> commentList = new ArrayList<>();

        JSONArray array = json.getJsonData(ID);

        for (int i=0;i<array.size();i++) {
            JSONObject object = array.getJSONObject(i);

            Comment comment = new Comment();
            comment.setUsername(object.getString("author"));
            comment.setText(object.getString("text"));
            comment.setDescription(object.getString("description"));
            comment.setTitle(object.getString("title"));

            commentList.add(comment);
        }

        return commentList;
    }
}
