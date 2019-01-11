package site.clzblog.elasticsearch.esdemo.repository;

import org.springframework.data.repository.CrudRepository;
import site.clzblog.elasticsearch.esdemo.entity.User;

public interface UserRepository extends CrudRepository<User, String> {
}
