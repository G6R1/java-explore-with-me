package ru.practicum.explorewithme.storages;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.explorewithme.models.User;

import java.util.List;
import java.util.Set;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "select * " +
            "from users as u " +
            "LIMIT ?2 OFFSET ?1", nativeQuery = true)
    List<User> FindAllFromSize(Integer from, Integer size);

    @Query(value = "select * " +
            "from users as u " +
            "where u.user_id in ?1 " +
            "LIMIT ?3 OFFSET ?2", nativeQuery = true)
    List<User> FindAllByIdsFromSize(Set<Long> ids, Integer from, Integer size);

    @Modifying
    @Query(value = "delete from subscriptions as s " +
            "where s.user_id=?1 and s.subscription_id=?2", nativeQuery = true)
    void deleteSubscription(Long userId, Long subsId);

    @Modifying
    @Query(value = "INSERT INTO subscriptions (user_id, subscription_id)" +
            "VALUES (?1, ?2)", nativeQuery = true)
    void createSubscription(Long userId, Long subsId);

    /*
    Вернет 1, если подписка существует, 0, если нет.
     */
    @Query(value = "select count(*) " +
            "from subscriptions as s " +
            "where s.user_id=?1 and s.subscription_id=?2", nativeQuery = true)
    Integer checkSubscription(Long userId, Long subsId);

    @Query(value = "select s.subscription_id " +
            "from subscriptions as s " +
            "where s.user_id=?1 ", nativeQuery = true)
    List<Long> findSubscriptionsIds(Long userId);

}