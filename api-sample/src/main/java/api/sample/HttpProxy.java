package api.sample;



public interface HttpProxy {
    
    
    public <T> T executePost(String url, String body,
            Class<T> resultClaz, String token);
    
}
