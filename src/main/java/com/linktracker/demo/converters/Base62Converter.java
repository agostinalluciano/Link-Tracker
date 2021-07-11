package com.linktracker.demo.converters;

import java.util.HashMap;
import java.util.Map;

public class Base62Converter {
    private static Long counter = 1L;
    private static Map<Long, String> indexToUrl = new HashMap<Long, String>();
    private static Map<String, Long> urlToIndex = new HashMap<String, Long>();
    private static String base62 = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    // Encodes a URL to a shortened URL.
    public static String encode(String longUrl) {
        if (urlToIndex.containsKey(longUrl)) {
            return base62Encode(urlToIndex.get(longUrl));
        } else {
            indexToUrl.put(counter, longUrl);
            urlToIndex.put(longUrl, counter);
            counter++;
            return   base62Encode(urlToIndex.get(longUrl));
        }
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        String base62Encoded = shortUrl.substring(shortUrl.lastIndexOf("/") + 1);
        long decode = 0;
        for (int i = 0; i < base62Encoded.length(); i++) {
            decode = decode * 62 + base62.indexOf("" + base62Encoded.charAt(i));
        }
        return indexToUrl.get(decode);
    }

    private static String base62Encode(long value) {
        StringBuilder sb = new StringBuilder();
        while (value != 0) {
            sb.append(base62.charAt((int) (value % 62)));
            value /= 62;
        }
        while (sb.length() < 6) {
            sb.append(0);
        }
        return sb.reverse().toString();
    }
}