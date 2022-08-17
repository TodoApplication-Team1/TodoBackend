package com.nest.todo;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nest.todo.entities.Users;
@Repository
public interface TestH2Repository  extends JpaRepository<Users, Integer>{

}
