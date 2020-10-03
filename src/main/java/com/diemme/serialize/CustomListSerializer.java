package com.diemme.serialize;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import com.diemme.domain.mysql.ChatUser;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class CustomListSerializer extends StdSerializer<Set<ChatUser>>{
	 
	   public CustomListSerializer() {
	        this(null);
	    }
	 
	    public CustomListSerializer(Class<Set<ChatUser>> t) {
	        super(t);
	    }
	 
	    @Override
	    public void serialize(
	    		Set<ChatUser> chatUsers, 
	      JsonGenerator generator, 
	      SerializerProvider provider) 
	      throws IOException, JsonProcessingException {
	        
	    	Set<Long> ids = new HashSet<>();
	        for (ChatUser chatUser : chatUsers) {
	            ids.add(chatUser.id);
	        }
	        generator.writeObject(ids);
	    }

		
	}
