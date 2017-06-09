package service;

import com.alibaba.fastjson.JSONArray;
import model.Comment;

import java.util.List;

/**
 * Created by Hermit on 2017/6/7.
 */
public interface CommentService {

    List<Comment> getCurrentComments(String ID);
}
