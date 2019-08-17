package ioiBaku.monitor.IOIMonitor.Repositories;

import ioiBaku.monitor.IOIMonitor.Domains.Status;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {
    @Transactional
    @Modifying
    @Query(value = "UPDATE STATUS u set u.STATUS =?2 where u.COUNTRY = ?1",nativeQuery = true)
    void ready(String country,String status);


    Status findById(Integer id);
    Status findByCountryCode(String countryCode);

    @Transactional
    @Modifying
    @Query(value = "UPDATE STATUS u set u.STATUS =?2 where u.COUNTRY = ?1",nativeQuery = true)
    void changeStatus(String country,String status);

    @Transactional
    @Modifying
    @Query(value = "UPDATE STATUS u set u.STATUS =?2 where u.COUNTRY = ?1",nativeQuery = true)
    void statusDropdown(String country,String status);

    @Transactional
    @Modifying
    @Query(value = "UPDATE STATUS u set u.MESSAGE =?2 where u.ID = ?1",nativeQuery = true)
    void message(Integer id,String message);


    @Transactional
    @Modifying
    @Query(value = "UPDATE STATUS u set u.EXTRALAN1 =?2, u.EXTRALAN2 =?3 where u.COUNTRYCODE = ?1",nativeQuery = true)
    void extra(String country,String extra1,String extra2);


    @Transactional
    @Modifying
    @Query(value = "UPDATE STATUS u set u.EXTRALAN1 =?2 where u.COUNTRYCODE = ?1",nativeQuery = true)
    void extraDone(String country,String extra);

    @Transactional
    @Modifying
    @Query(value = "UPDATE STATUS u set u.EXTRALAN2 =?2 where u.COUNTRYCODE = ?1",nativeQuery = true)
    void extraDone1(String country,String extra);

    List<Status> findByOrderByDateAsc();


}
