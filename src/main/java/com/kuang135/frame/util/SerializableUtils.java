package com.kuang135.frame.util;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class SerializableUtils {

    public static String serialize(Object object) {
    	ByteArrayOutputStream bos = null;
    	ObjectOutputStream oos = null;
        try {
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(object);
            return new String(bos.toByteArray());
        } catch (Exception e) {
            throw new RuntimeException("serialize error", e);
        } finally {
			try {
				if (bos != null)
					bos.close();
				if (oos != null)
					oos.close();
			} catch (IOException e) {
				throw new RuntimeException("serialize error", e);
			}
        }
    }
    
	public static Object deserialize(String serializedStr) {
		ByteArrayInputStream bis = null;
		ObjectInputStream ois = null;
        try {
            bis = new ByteArrayInputStream(serializedStr.getBytes());
            ois = new ObjectInputStream(bis);
            return ois.readObject();
        } catch (Exception e) {
            throw new RuntimeException("deserialize error", e);
        } finally {
        	try {
				if (bis != null)
					bis.close();
				if (ois != null)
					ois.close();
			} catch (IOException e) {
				throw new RuntimeException("serialize error", e);
			}
        }
    }
}
