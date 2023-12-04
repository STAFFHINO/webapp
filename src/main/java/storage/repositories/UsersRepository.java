package storage.repositories;


import storage.model.User;
import storage.repositories.generic.CrudRepository;
import java.util.Optional;

public interface UsersRepository extends CrudRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
