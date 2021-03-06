package com.app01;

import io.nats.client.Connection;
import io.nats.client.Message;
import io.nats.client.MessageHandler;
import io.nats.client.Nats;

public class ProducerForAsyncWith2ListenersWithReplyTo {

    public static void main(String[] args) throws Exception {
        Connection natsConnection = Nats.connect();

        natsConnection.subscribe("topic1back", new MessageHandler() {

            @Override
            public void onMessage(Message message) {
                System.out.println("A. ReplyTo::: " + message);

            }
        });

        natsConnection.subscribe("topic1back", new MessageHandler() {

            @Override
            public void onMessage(Message message) {
                System.out.println("B. ReplyTo::: " + message);

            }
        });


        natsConnection.publish("topic1", "topic1back", "Hi there!".getBytes());

        System.out.println("-------sending 2 message------");

        natsConnection.publish("topic1", "topic1back", "Hi there!".getBytes());
       // natsConnection.close();
    }
}
