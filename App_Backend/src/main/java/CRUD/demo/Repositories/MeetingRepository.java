package CRUD.demo.Repositories;

import CRUD.demo.Models.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting,Long> {
    @Query(value = "select * from meeting where username = :userName", nativeQuery = true)
    public List<Meeting> getMeetingsByUserName(@Param(value = "userName") String userName);

    @Modifying
    @Transactional
    @Query(value = "delete from meeting where username = :userName", nativeQuery = true)
    public void deleteMeetingsByUserName(@Param(value="userName") String userName);
}
