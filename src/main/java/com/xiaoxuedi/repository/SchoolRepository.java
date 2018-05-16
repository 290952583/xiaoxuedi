package com.xiaoxuedi.repository;

import com.xiaoxuedi.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolRepository extends JpaRepository<School, String>
{
}
