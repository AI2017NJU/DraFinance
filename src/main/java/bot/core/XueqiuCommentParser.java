package bot.core;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * Created by Hermit on 2017/6/1.
 */
public class XueqiuCommentParser {

    public static void main(String[] args) {
        XueqiuCommentJson json = new XueqiuCommentJson();
        String html = json.getJSONData("SH600000");
        XueqiuCommentParser parser = new XueqiuCommentParser();
        JSONArray list = parser.getCommentList(html);
        for (int i=0;i<list.size();i++) {
            JSONObject item = list.getJSONObject(i);
            System.out.println(item.getString("description"));
        }
    }

    public JSONArray getCommentList(String json) {
        JSONObject content = JSON.parseObject(json);
        JSONArray list = content.getJSONArray("list");
        return list;
    }

}
