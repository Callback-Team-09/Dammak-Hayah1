package com.dammak.project401;


import com.dammak.project401.models.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepo extends CrudRepository<Comment,Long> {

}
