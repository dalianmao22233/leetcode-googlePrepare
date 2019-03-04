public class Codec {

    private static final String ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String PREFIX = "https://tinyurl.com/";
    
    private static final int ALPHABET_LENGTH = ALPHABET.length();
    private static final int KEY_LENGTH = 6;
    
    private final Map<String, String> longUrlToKey;
    private final Map<String, String> keyToLongUrl;
    private final Random random;
    
    public Codec() {
        this.longUrlToKey = new HashMap<>();
        this.keyToLongUrl = new HashMap<>();
        this.random = new Random();
    }
    
    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        if (longUrl == null || longUrl.length() == 0) {
            return null;
        }
        
        if (longUrlToKey.containsKey(longUrl)) {
            return PREFIX + longUrlToKey.get(longUrl);
        }
        
        String key = generateRandom();
        
        while (keyToLongUrl.containsKey(key)) { // 避免碰撞
            key = generateRandom();
        }
        
        longUrlToKey.put(longUrl, key);
        keyToLongUrl.put(key, longUrl);
        
        return PREFIX + key;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        if (shortUrl == null || shortUrl.length() == 0) {
            return null;
        }
        
        String key = shortUrl.substring(shortUrl.lastIndexOf("/") + 1);
        
        if (!keyToLongUrl.containsKey(key)) {
            return null;
        }
        
        return keyToLongUrl.get(key);
    }
    
    private String generateRandom() {
        StringBuilder builder = new StringBuilder();
        
        for (int i = 0; i < KEY_LENGTH; i++) {
            builder.append(ALPHABET.charAt(random.nextInt(ALPHABET_LENGTH)));
        }
                                   
        return builder.toString();
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(url));
