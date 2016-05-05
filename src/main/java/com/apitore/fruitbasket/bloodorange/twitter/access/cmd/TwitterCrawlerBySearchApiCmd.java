package com.apitore.fruitbasket.bloodorange.twitter.access.cmd;


import java.io.IOException;

import com.apitore.fruitbasket.bloodorange.twitter.access.TwitterCrawlerBySearchApi;


public class TwitterCrawlerBySearchApiCmd {

  public static void main (final String[] args) throws IOException {
    TwitterCrawlerBySearchApi analyzer = new TwitterCrawlerBySearchApi();
    analyzer.run();
  }

}
