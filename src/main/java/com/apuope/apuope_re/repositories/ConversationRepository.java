package com.apuope.apuope_re.repositories;

import com.apuope.apuope_re.jooq.tables.Conversation;
import com.apuope.apuope_re.jooq.tables.records.ConversationRecord;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;

public class ConversationRepository {
    @Autowired
    public ConversationRepository(){};

    public ConversationRecord findByConversationId(Integer conversationId, DSLContext context){
        return context.selectFrom(Conversation.CONVERSATION)
                .where(Conversation.CONVERSATION.ID.eq(conversationId))
                .fetch()
                .getFirst();
    }
}
