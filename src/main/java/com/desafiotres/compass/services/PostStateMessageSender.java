package com.desafiotres.compass.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class PostStateMessageSender {

    private final JmsTemplate jmsTemplate;

    public PostStateMessageSender(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void sendPostCreatedMessage(Long postId) {
        sendStateMessage(postId, "queue.post.created");
    }

    public void sendPostFindMessage(Long postId) {
        sendStateMessage(postId, "queue.post.find");
    }

    public void sendPostOkMessage(Long postId) {
        sendStateMessage(postId, "queue.post.ok");
    }

    public void sendCommentsFindMessage(Long postId) {
        sendStateMessage(postId, "queue.comments.find");
    }

    public void sendCommentsOkMessage(Long postId) {
        sendStateMessage(postId, "queue.comments.ok");
    }

    public void sendEnabledMessage(Long postId) {
        sendStateMessage(postId, "queue.post.enabled");
    }

    public void sendDisabledMessage(Long postId) {
        sendStateMessage(postId, "queue.post.disabled");
    }

    public void sendUpdatingMessage(Long postId) {
        sendStateMessage(postId, "queue.post.updating");
    }

    public void sendFailedMessage(Long postId) {
        sendStateMessage(postId, "queue.post.failed");
    }

    private void sendStateMessage(Long postId, String queueName) {
        jmsTemplate.convertAndSend(queueName, postId);
    }
}