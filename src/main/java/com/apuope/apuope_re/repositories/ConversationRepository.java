package com.apuope.apuope_re.repositories;

import com.apuope.apuope_re.dto.ResponseData;
import com.apuope.apuope_re.jooq.tables.Conversation;
import com.apuope.apuope_re.jooq.tables.records.ConversationRecord;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class ConversationRepository {
    private final ZoneId TIMEZONE = ZoneId.of("Europe/Helsinki");

    @Autowired
    public ConversationRepository(){};

    public ConversationRecord findByConversationId(Integer conversationId, DSLContext context){
        return context.selectFrom(Conversation.CONVERSATION)
                .where(Conversation.CONVERSATION.ID.eq(conversationId))
                .fetch()
                .getFirst();
    }
    public ConversationRecord findByAccountId(Integer accountId, DSLContext context){
        return context.selectFrom(Conversation.CONVERSATION)
                .where(Conversation.CONVERSATION.ACCOUNT_ID.eq(accountId))
                .orderBy(Conversation.CONVERSATION.ID.desc())
                .fetch()
                .getFirst();
    }

    // let's think the subject part later
    public ConversationRecord createConversation(Integer accountId, Integer chapterId, String subject, DSLContext context) {
        context.insertInto(Conversation.CONVERSATION)
                .set(Conversation.CONVERSATION.ACCOUNT_ID, accountId)
                .set(Conversation.CONVERSATION.CHAPTER_ID, chapterId)
                .set(Conversation.CONVERSATION.DATETIME, LocalDateTime.now(TIMEZONE))
                .set(Conversation.CONVERSATION.SUBJECT, "Chapter " + chapterId)
                .execute();

        return findByAccountId(accountId, context);
    }
/*
    public ResponseData<String> createMessage(Integer conversationId, String message, DSLContext context) {
        try {
            context.insertInto(Message.MESSAGE)
                    .set(Message.MESSAGE.CONVERSATION_ID, conversationId)
                    .set(Message.MESSAGE.MESSAGE_, message)
                    .set(Message.MESSAGE.DATETIME, LocalDateTime.now(TIMEZONE))
                    .execute();

            return new ResponseData<>(true, "Message added successfully.");
        } catch (Exception e) {
            return new ResponseData<>(false, "Error when creating message: " + e);
        }
    }
    */
}
