package exarb.fmgamelogic.event;

import exarb.fmgamelogic.service.UserGameDataService;
import exarb.fmgamelogic.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class EventHandler {

    private UserService userService;
    private UserGameDataService userGameDataService;


    /**
     * Listens to the user queue
     * @param userLoggedInEvent
     */
    @RabbitListener(queues = "${user.queue}")
    void handleUserLoggedIn(final UserLoggedInEvent userLoggedInEvent) {
        System.out.println("gamelogic rabbitlistener");
        log.info("UserLoggedIn Event received: {}", userLoggedInEvent.getUserId());

        try {
            userService.retrieveUserById(userLoggedInEvent.getUserId());
            userGameDataService.createNewUserGameData(userLoggedInEvent.getUserId());

        } catch (final Exception e) {
            log.error("Error when trying to process UserWorkCountEvent", e);
            // The event will not be re-queued and reprocessed repeatedly if
            // something goes wrong.
            // TODO: Since we don’t have anything in place to handle rejected events,
            //  they will be simply discarded. If you want to get deeper into good practices
            //  with RabbitMQ, you can look at how to configure a dead letter exchange and put our
            //  failing messages there for further processing (like retrying, logging, or raising alerts).
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }
}
