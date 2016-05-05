package com.apitore.fruitbasket.bloodorange.twitter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
//import java.util.Date;
import java.util.Properties;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

public class GetTwitterObject {

  private static String CONSUMERKEY;
  private static String CONSUMERSECRET;
  private static String ACCESSTOKEN;
  private static String ACCESSSECRET;

  //private static Long SerialVersionUid = 1346379335928L;
  //private static Long CheckSerialVersion = new Date().getTime();

  /**
   * Twitterオブジェクトの取得
   * @return
   */
  public static Twitter getTwitter(){
    return GetTwitterObject.getTwitter("./src/main/resources/twitter.properties");
  }

  /**
   * 設定ファイルを読み込み、Twitterオブジェクトを返す
   * @param prop_filename
   * @return
   */
  public static Twitter getTwitter(String prop_filename){
    Twitter twitter = null;
    try {
      //Propertiesオブジェクトを生成
      Properties prop = new Properties();
      prop.load(new FileInputStream(prop_filename));
      twitter = getTwitter(prop);
    } catch (FileNotFoundException e) {
      System.err.println(e);
    } catch (IOException e) {
      System.err.println(e);
    }
    return twitter;
  }

  /**
   * Propertiesを読み込み、Twitterオブジェクトを返す
   * @param prop
   * @return
   */
  public static Twitter getTwitter(Properties prop) {
    Twitter twitter = null;
    //if (GetTwitterObject.CheckSerialVersion > GetTwitterObject.SerialVersionUid)
    //	return twitter;

    GetTwitterObject.CONSUMERKEY = prop.getProperty("consumer.key");
    GetTwitterObject.CONSUMERSECRET = prop.getProperty("consumer.secret");
    GetTwitterObject.ACCESSTOKEN = prop.getProperty("access.token");
    GetTwitterObject.ACCESSSECRET = prop.getProperty("access.token.secret");

    ConfigurationBuilder confbuilder = new ConfigurationBuilder();
    confbuilder.setOAuthConsumerKey(CONSUMERKEY);
    confbuilder.setOAuthConsumerSecret(CONSUMERSECRET);
    confbuilder.setOAuthAccessToken(ACCESSTOKEN);
    confbuilder.setOAuthAccessTokenSecret(ACCESSSECRET);

    TwitterFactory twitterfactory = new TwitterFactory(confbuilder.build());
    twitter = twitterfactory.getInstance();

    return twitter;
  }


  /**
   * TwitterStreamオブジェクトの取得
   * @return
   */
  public static TwitterStream getTwitterStream(){
    return GetTwitterObject.getTwitterStream("./src/main/resources/twitter.properties");
  }

  /**
   * 設定ファイルを読み込み、TwitterStreamオブジェクトを返す
   * @param prop_filename
   * @return
   */
  public static TwitterStream getTwitterStream(String prop_filename){
    TwitterStream twitter = null;
    try {
      //Propertiesオブジェクトを生成
      Properties prop = new Properties();
      prop.load(new FileInputStream(prop_filename));
      twitter = getTwitterStream(prop);
    } catch (FileNotFoundException e) {
      System.err.println(e);
    } catch (IOException e) {
      System.err.println(e);
    }
    return twitter;
  }

  /**
   * Propertiesを読み込み、TwitterStreamオブジェクトを返す
   * @param prop
   * @return
   */
  public static TwitterStream getTwitterStream(Properties prop) {
    TwitterStream twitter = null;
    //if (GetTwitterObject.CheckSerialVersion > GetTwitterObject.SerialVersionUid)
    //	return twitter;

    GetTwitterObject.CONSUMERKEY = prop.getProperty("consumer.key");
    GetTwitterObject.CONSUMERSECRET = prop.getProperty("consumer.secret");
    GetTwitterObject.ACCESSTOKEN = prop.getProperty("access.token");
    GetTwitterObject.ACCESSSECRET = prop.getProperty("access.token.secret");

    ConfigurationBuilder confbuilder = new ConfigurationBuilder();
    confbuilder.setOAuthConsumerKey(CONSUMERKEY);
    confbuilder.setOAuthConsumerSecret(CONSUMERSECRET);
    confbuilder.setOAuthAccessToken(ACCESSTOKEN);
    confbuilder.setOAuthAccessTokenSecret(ACCESSSECRET);

    TwitterStreamFactory twitterfactory = new TwitterStreamFactory(confbuilder.build());
    twitter = twitterfactory.getInstance();

    return twitter;
  }

}
