package com.diemme.customType;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data @NoArgsConstructor @AllArgsConstructor
public class UserChatID implements Serializable {
	
	private static final long serialVersionUID = 7368631988611481727L;

	@Column(name = "chat_id")
    private Long projectId;

    @Column(name = "user_id")
    private Long userId;

}
