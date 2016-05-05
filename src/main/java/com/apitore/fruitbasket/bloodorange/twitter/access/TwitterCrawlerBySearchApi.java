package com.apitore.fruitbasket.bloodorange.twitter.access;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.apitore.fruitbasket.bloodorange.twitter.GetTwitterObject;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;


public class TwitterCrawlerBySearchApi {

  private Twitter twitter;
  private PrintWriter pw;

  private String query;
  private Integer count;
  private Long sinceId;
  private Long maxId;
  private String lang;
  private String locale;
  private String since_date;
  private String until_date;


  public TwitterCrawlerBySearchApi () throws IOException {
    this.twitter = GetTwitterObject.getTwitter();

    File file = new File("./search.txt");
    this.pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));

    this.query = "ロックマン AND -RT";
    this.count = 200;
    this.lang = "ja";
    this.locale = "ja";
    this.sinceId = -1L;
    this.maxId = -1L;
    this.since_date = null;
    this.until_date = null;
  }


  /* main関数 */
  /**
   * SearchAPI利用し、Solrに登録
   */
  public void run() {
    // Twitter Objectが取得できなければ終了
    if (this.twitter == null) {
      System.err.println("Twitter.com is busy or Password is incorrect.");
      return;
    }

    Query query = new Query(this.query);
    query.setLang(this.lang);
    query.setLocale(this.locale);
    query.setCount(this.count);
    if (this.sinceId != -1L)
      query.setSinceId(this.sinceId);
    if (this.maxId != -1L)
      query.setMaxId(this.maxId);
    if (this.since_date != null)
      query.setSince(this.since_date);
    if (this.until_date != null)
      query.setUntil(this.until_date);

    getSearch(query);
  }

  /* private */
  /**
   * Tweet取得
   *
   * @param query
   */
  private void getSearch(Query query) {
    try {
      QueryResult statuses = twitter.search(query);
      if(statuses != null){
        List<Status> tweets = statuses.getTweets();
        for (Status status: tweets) {
          if(status.isRetweeted() || status.getText().startsWith("RT "))
            continue;
          if(status.getInReplyToStatusId() != -1 || status.getText().startsWith("@"))
            continue;
          if(status.getText().contains("http"))
            continue;
          String tweet = status.getText();
          tweet = tweet.replaceAll("[\r\n\t\\s]+", "");
          this.pw.println(tweet);
          System.out.println(tweet);
        }
      }
    } catch (TwitterException e) {
      System.err.println(e.getMessage());
    } finally {
      this.pw.flush();
    }
  }

}
