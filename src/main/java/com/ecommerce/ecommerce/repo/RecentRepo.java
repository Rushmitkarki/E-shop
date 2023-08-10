package com.ecommerce.ecommerce.repo;

import com.ecommerce.ecommerce.entity.Item;
import com.ecommerce.ecommerce.entity.Recent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RecentRepo extends JpaRepository<Recent,Integer> {


    @Query(value = "select * from recent where item_id=?1 and email=?2",nativeQuery = true)
    Optional<Recent> getByItemIdAndUserId(Integer itemId, Integer userId);
    @Query(value = "select * from recent where email=?1 order by recent_date desc limit 3",nativeQuery = true)
    List<Recent> getRecent(int id);


}
