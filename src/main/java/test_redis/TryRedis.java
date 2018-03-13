package test_redis;

import redis.clients.jedis.Jedis;

import java.nio.charset.Charset;

/**
 * Created by Fresher on 09/03/2018.
 */
public class TryRedis {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost", 6379);
        jedis.set("foo", "bar");
        String value = jedis.get("foo");


        byte[] key = ("foo").getBytes(Charset.forName("UTF-8"));

        byte[] val = ("bar").getBytes(Charset.forName("UTF-8"));


        jedis.set(key, val);




        byte [] val2=jedis.get(key);

        System.out.println();


    }
}
