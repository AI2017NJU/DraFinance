package service;

import com.alibaba.fastjson.JSONArray;

/**
 * Created by Hermit on 2017/6/7.
 */
public interface CommentService {

    JSONArray getCurrentComments(String ID);
}
