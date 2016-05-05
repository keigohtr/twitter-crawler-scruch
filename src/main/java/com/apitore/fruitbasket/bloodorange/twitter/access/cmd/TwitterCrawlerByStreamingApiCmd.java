package com.apitore.fruitbasket.bloodorange.twitter.access.cmd;


import java.io.IOException;

import com.apitore.fruitbasket.bloodorange.twitter.access.TwitterCrawlerByStreamingApi;


public class TwitterCrawlerByStreamingApiCmd {

  public static void main (final String[] args) throws IOException {
    TwitterCrawlerByStreamingApi analyzer = new TwitterCrawlerByStreamingApi();
    analyzer.run();
  }

}
