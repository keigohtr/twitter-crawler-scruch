package com.apitore.fruitbasket.bloodorange.twitter.access;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import com.apitore.fruitbasket.bloodorange.twitter.GetTwitterObject;

import twitter4j.FilterQuery;
import twitter4j.Status;
import twitter4j.StatusAdapter;
import twitter4j.TwitterStream;

public class TwitterCrawlerByStreamingApi {

  private TwitterStream twitter;
  private final String OUTFILE = "./streaming.txt";

  private String[] query;
  private String lang;


  /* Constructor */
  public TwitterCrawlerByStreamingApi () throws IOException
  {
    this.twitter = GetTwitterObject.getTwitterStream();
    this.twitter.addListener(new Listener(OUTFILE));

    this.query = new String[]{"。"};
    this.lang = "ja";
  }


  /* main */
  public void run() {
    // Twitter Objectが取得できなければ終了
    if (this.twitter == null) {
      System.err.println("Twitter.com is busy or Password is incorrect.");
      return;
    }

    FilterQuery filterQuery = new FilterQuery();
    filterQuery.track(query);
    filterQuery.language(lang);

    twitter.filter(filterQuery);

    try {
      while(true) {
        Thread.sleep(300 * 1000L);
      }
    } catch (Exception e) {
      System.err.println(e.getMessage());
    } finally {
      twitter.shutdown();
    }
  }

  /* private */
  /**
   * Streamingを受け取るListener
   */
  class Listener extends StatusAdapter {

    private PrintWriter pw;
    private int count;

    public Listener(String filename) throws IOException {
      File file = new File(filename);
      this.pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
      this.count=0;
    }

    @Override
    public void onStatus(Status status) {
      if(status.isRetweeted() || status.getText().startsWith("RT "))
        return;
      if(status.getInReplyToStatusId() != -1 || status.getText().startsWith("@"))
        return;
      if(status.getText().contains("http"))
        return;
      count++;
      String tweet = status.getText();
      tweet = tweet.replaceAll("[\r\n\t\\s]+", "");
      this.pw.println(tweet);
      if (count>10) {
        this.pw.flush();
        count=0;
      }
      System.out.println(tweet);
    }

    @Override
    public void onException(Exception e) {
      System.err.println(e.getMessage());
    }
  }

}
