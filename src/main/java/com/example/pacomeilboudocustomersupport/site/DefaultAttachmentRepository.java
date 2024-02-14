package com.example.pacomeilboudocustomersupport.site;

import com.example.pacomeilboudocustomersupport.entities.Attachment;
import org.springframework.stereotype.Repository;

@Repository
public class DefaultAttachmentRepository extends GenericJpaRepository<Long, Attachment> implements AttachmentRepository{

    @Override
    public Attachment getByTicket_id(Long ticket_id) {
        try {
            return this.entityManager.createQuery("SELECT i FROM Attachment i WHERE i.ticket_id = :id", Attachment.class).setParameter("id", ticket_id).getSingleResult();
        }
        catch(Exception e) {
            return null;
        }
    }
}