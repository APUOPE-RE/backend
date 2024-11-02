package com.apuope.apuope_re.repositories;

import com.apuope.apuope_re.dto.MessageData;
import com.apuope.apuope_re.dto.ResponseData;
import com.apuope.apuope_re.jooq.tables.Conversation;
import com.apuope.apuope_re.jooq.tables.Message;
import com.apuope.apuope_re.jooq.tables.records.ConversationRecord;
import com.apuope.apuope_re.jooq.tables.records.MessageRecord;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Repository
public class ConversationRepository {
    private final ZoneId TIMEZONE = ZoneId.of("Europe/Helsinki");

    @Autowired
    public ConversationRepository(){};

    // Conversation
    public List<ConversationRecord> fetchConversationByAccountId(Integer accountId, DSLContext context){
        return context.selectFrom(Conversation.CONVERSATION)
                .where(Conversation.CONVERSATION.ACCOUNT_ID.eq(accountId))
                .fetch();
    }

    public List<MessageData> fetchConversationById(Integer conversationId, DSLContext context){
        return context.select(
                        Conversation.CONVERSATION.ID.as("conversationId"),
                        Message.MESSAGE.ID.as("messageId"),
                        Message.MESSAGE.CONTENT.as("content"),
                        Message.MESSAGE.SOURCE.as("source"),
                        Message.MESSAGE.DATETIME.as("timeStamp")
                )
                .from(Conversation.CONVERSATION)
                .join(Message.MESSAGE).on(Message.MESSAGE.CONVERSATION_ID.eq(Conversation.CONVERSATION.ID))
                .where(Conversation.CONVERSATION.ID.eq(conversationId))
                .fetchInto(MessageData.class);
    }

    public ConversationRecord findLatestByAccountId(Integer accountId, DSLContext context){
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

        return findLatestByAccountId(accountId, context);
    }

    // Message
    public MessageRecord findLatestByConversationId(Integer conversationId, DSLContext context){
        return context.selectFrom(Message.MESSAGE)
                .where(Message.MESSAGE.CONVERSATION_ID.eq(conversationId))
                .orderBy(Message.MESSAGE.ID.desc())
                .fetch()
                .getFirst();
    }

    public MessageRecord createMessage(Integer conversationId, String message, Integer source, DSLContext context) {
        context.insertInto(Message.MESSAGE)
                .set(Message.MESSAGE.CONVERSATION_ID, conversationId)
                .set(Message.MESSAGE.CONTENT, message)
                .set(Message.MESSAGE.SOURCE, source)
                .set(Message.MESSAGE.DATETIME, LocalDateTime.now(TIMEZONE))
                .execute();

        return findLatestByConversationId(conversationId, context);
    }
}
