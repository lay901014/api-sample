package api.sample;

import java.io.Serializable;

public class Pic implements Serializable{
    
    private byte[] PIC;

    
    public byte[] getPIC() {
        return PIC;
    }

    
    public void setPIC(byte[] pIC) {
        PIC = pIC;
    }
    
    
}
