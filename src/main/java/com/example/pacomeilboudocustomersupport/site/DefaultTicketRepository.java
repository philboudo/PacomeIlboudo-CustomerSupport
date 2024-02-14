package com.example.pacomeilboudocustomersupport.site;

import com.example.pacomeilboudocustomersupport.entities.TicketEntity;
import org.springframework.stereotype.Repository;
@Repository
public class DefaultTicketRepository extends GenericJpaRepository<Long, TicketEntity> implements TicketRepository {

}




