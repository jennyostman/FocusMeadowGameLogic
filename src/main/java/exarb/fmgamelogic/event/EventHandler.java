package exarb.fmgamelogic.event;

import exarb.fmgamelogic.adapter.UserAdapter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class EventHandler {

    private UserAdapter userAdapter;

    /**
     * Listens to the user queue
     * @param userRegisteredEvent event message object with registered user
     */
    @RabbitListener(queues = "${user.queue}")
    void handleUserLoggedIn(final UserRegisteredEvent userRegisteredEvent) {
        log.info("UserRegisteredEvent received: {}", userRegisteredEvent.getUserId());

        try {
            userAdapter.createUserGameDataForNewUser(userRegisteredEvent.getUserId());
        } catch (final Exception e) {
            log.error("Error when trying to process UserWorkCountEvent", e);
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }
}
