package com.strore.book.repository;

import com.strore.book.dao.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<Users,Long> {

    List<Users> findAllByName(String name);//просто правильное название метода даст возможность
    //избежать запросов на SQL

    @Query(value = "select * from p01_users where email like '%@gmail.com%'",nativeQuery = true) //если этого мало можно написать
                                                                                            //собственный запрос на языке похожем на SQL
    List<Users> findWhereEmailIsGmail();

    @Query(value = "select * from p01_users where name like '%smith%'", nativeQuery = true)
        //если и этого мало - можно написать запрос на чистом SQL и все это будет работать
    List<Users> findWhereNameStartsFromSmith();
}
