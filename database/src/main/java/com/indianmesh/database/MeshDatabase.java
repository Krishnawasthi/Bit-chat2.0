/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.indianmesh.database.converter.DateConverter;
import com.indianmesh.database.converter.DeliveryStatusConverter;
import com.indianmesh.database.converter.MessageTypeConverter;
import com.indianmesh.database.converter.UUIDConverter;

import com.indianmesh.database.dao.AttachmentDao;
import com.indianmesh.database.dao.ConversationDao;
import com.indianmesh.database.dao.DraftDao;
import com.indianmesh.database.dao.GroupDao;
import com.indianmesh.database.dao.KeyDao;
import com.indianmesh.database.dao.MessageDao;
import com.indianmesh.database.dao.PeerDao;
import com.indianmesh.database.dao.PollDao;
import com.indianmesh.database.dao.QueueDao;
import com.indianmesh.database.dao.ReactionDao;
import com.indianmesh.database.dao.SearchDao;
import com.indianmesh.database.dao.UserDao;

import com.indianmesh.database.entity.AttachmentEntity;
import com.indianmesh.database.entity.ContactEntity;
import com.indianmesh.database.entity.ConversationEntity;
import com.indianmesh.database.entity.DraftEntity;
import com.indianmesh.database.entity.GroupEntity;
import com.indianmesh.database.entity.GroupMemberEntity;
import com.indianmesh.database.entity.KeyPairEntity;
import com.indianmesh.database.entity.MessageEntity;
import com.indianmesh.database.entity.MessageQueueEntity;
import com.indianmesh.database.entity.PeerEntity;
import com.indianmesh.database.entity.PinnedMessageEntity;
import com.indianmesh.database.entity.PollEntity;
import com.indianmesh.database.entity.PollOptionEntity;
import com.indianmesh.database.entity.PollVoteEntity;
import com.indianmesh.database.entity.ReactionEntity;
import com.indianmesh.database.entity.RoutingTableEntity;
import com.indianmesh.database.entity.ScheduledMessageEntity;
import com.indianmesh.database.entity.SessionEntity;
import com.indianmesh.database.entity.StarredMessageEntity;
import com.indianmesh.database.entity.UserEntity;

/**
 * The main Room database class for the Indian Mesh application.
 * Contains definitions for all tables, converters, and DAOs.
 */
@Database(
    entities = {
        AttachmentEntity.class,
        ContactEntity.class,
        ConversationEntity.class,
        DraftEntity.class,
        GroupEntity.class,
        GroupMemberEntity.class,
        KeyPairEntity.class,
        MessageEntity.class,
        MessageQueueEntity.class,
        PeerEntity.class,
        PinnedMessageEntity.class,
        PollEntity.class,
        PollOptionEntity.class,
        PollVoteEntity.class,
        ReactionEntity.class,
        RoutingTableEntity.class,
        ScheduledMessageEntity.class,
        SessionEntity.class,
        StarredMessageEntity.class,
        UserEntity.class
    },
    version = 1,
    exportSchema = true
)
@TypeConverters({
    DateConverter.class,
    UUIDConverter.class,
    MessageTypeConverter.class,
    DeliveryStatusConverter.class
})
public abstract class MeshDatabase extends RoomDatabase {

    public abstract AttachmentDao attachmentDao();
    public abstract ConversationDao conversationDao();
    public abstract DraftDao draftDao();
    public abstract GroupDao groupDao();
    public abstract KeyDao keyDao();
    public abstract MessageDao messageDao();
    public abstract PeerDao peerDao();
    public abstract PollDao pollDao();
    public abstract QueueDao queueDao();
    public abstract ReactionDao reactionDao();
    public abstract SearchDao searchDao();
    public abstract UserDao userDao();
}
