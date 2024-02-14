package com.example.pacomeilboudocustomersupport.site;

import com.example.pacomeilboudocustomersupport.entities.Attachment;

public interface AttachmentRepository extends GenericRepository<Long, Attachment>{
    Attachment getByTicket_id(Long ticket_id);
}