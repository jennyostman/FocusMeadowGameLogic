package exarb.fmgamelogic.event;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EventDispatcher {
    private RabbitTemplate rabbitTemplate;
    private String timerExchange;
    private String timerCompletedRoutingKey;

    @Autowired
    EventDispatcher(final RabbitTemplate rabbitTemplate,
                    @Value("${timerCount.exchange}") final String timerExchange,
                    @Value("${timerCount.work.key}") final String timerCompletedRoutingKey) {
        this.rabbitTemplate = rabbitTemplate;
        this.timerExchange = timerExchange;
        this.timerCompletedRoutingKey = timerCompletedRoutingKey;
    }

    /**
     * Converts and sends theTimerCountWorkEvent
     * @param timerCountWorkEvent a timerCount event
     */
    public void send(final TimerCountWorkEvent timerCountWorkEvent) {
        rabbitTemplate.convertAndSend(timerExchange, timerCompletedRoutingKey, timerCountWorkEvent);
    }
}
