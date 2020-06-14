package association.service;

import java.util.List;

import org.springframework.data.domain.Page;

import association.model.Message;
import association.model.Vote;

public interface MessageService {

	Page<Message> findAll(int pageNum);

	Message findOne(Long id);

	Message save(Message message);

	Message delete(Long id);

	List<Message> findByFlatId(Long flatId);

	Page<Message> search(String title, String type, Long flatId, int pageNum);

	void performCheck(Vote savedVote);

}
