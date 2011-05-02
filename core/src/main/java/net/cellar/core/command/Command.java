/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.cellar.core.command;

import net.cellar.core.Node;
import net.cellar.core.event.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author iocanel
 */
public class Command<R extends Result> extends Event {

    protected static final Logger logger = LoggerFactory.getLogger(Command.class);

    protected long timeout = 10000;
    protected final BlockingQueue<Map<Node, R>> resultQueue = new LinkedBlockingQueue<Map<Node, R>>();
    protected final Map<Node, R> nodeResults = new HashMap<Node, R>();

    public Command(String id) {
        super(id);
        this.force = true;
    }

    @Override
    public Boolean getForce() {
        return true;
    }


    /**
     * Process the event of timeout.
     */
    public void onTimeout() {
        try {
            resultQueue.put(nodeResults);
        } catch (InterruptedException e) {
            logger.error("Error adding result to result queue", e);
        }
    }

    /**
     * Adds {@code Results} to the result queue.
     *
     * @param results
     */
    public void addResults(R... results) {
        if (results != null && results.length > 0) {
            for (R result : results) {
                nodeResults.put(result.getSourceNode(), result);
            }

            if (getDestination() == null || (nodeResults.size() == getDestination().size())) {
                try {
                    resultQueue.put(nodeResults);
                } catch (InterruptedException e) {
                    logger.error("Error adding result to result queue", e);
                }
            }
        }
    }

    /**
     * Returns the responses.
     * If no results found it returns an empty map.
     *
     * @return
     * @throws Exception
     */
    public Map<Node, R> getResult() throws Exception {
        if (this.resultQueue != null) {
            Map<Node, R> nodeResults = resultQueue.poll(timeout, TimeUnit.MILLISECONDS);
        }
        return nodeResults;
    }


    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }
}
