package com.diemme.repository.mysql;

import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.diemme.domain.mysql.ChatUser;

@Repository("ChatRepository")
public interface ChatUserRepository extends JpaRepository<ChatUser, Long> {

	@Query(value = "SELECT cu FROM ChatUser cu WHERE cu.user.id = :idUser")
	Page<ChatUser> findIdChatMongoDb(Pageable pageable, @Param("idUser") Long idUser);
	
	@Query(value = "SELECT cu FROM ChatUser cu WHERE cu.idChatMongo = :idChatMongo")
	Set<ChatUser> findUserChatMongoDb(@Param("idChatMongo") String idChatMongo);
}
