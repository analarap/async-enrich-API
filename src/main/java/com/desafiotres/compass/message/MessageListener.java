package com.desafiotres.compass.message;

import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageListener {

    private static final String PROCESS = "queue.post.processed";
    private static final String DISABLE = "queue.post.disabled";
    private static final String REPROCESS = "queue.post.reprocessed";

    @JmsListener(destination = PROCESS)
    public void processPost(Long postId) {
        System.out.println("Processed Post with ID: " + postId);
    }

    @JmsListener(destination = DISABLE)
    public void disablePost(Long postId) {
        System.out.println("Disabled Post with ID: " + postId);
    }

    @JmsListener(destination = REPROCESS)
    public void reprocessPost(Long postId) {
        System.out.println("Reprocessed Post with ID: " + postId);
    }
}
