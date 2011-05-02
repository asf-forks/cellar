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

import net.cellar.core.CellarSupport;
import net.cellar.core.Node;
import net.cellar.core.Producer;
import net.cellar.core.control.Switch;
import net.cellar.core.event.EventHandler;

import java.util.HashSet;
import java.util.Set;

/**
 * @author iocanel
 */
public abstract class CommandHandler<C extends Command<R>, R extends Result> extends CellarSupport implements EventHandler<C> {

    protected Producer producer;

    /**
     * Hanldes the the {@code Command}.
     *
     * @param command
     */
    public void handle(C command) {
        if (producer != null) {
            R result = execute(command);

            Set<Node> destination = new HashSet<Node>();
            destination.add(command.getSourceNode());

            result.setDestination(destination);
            producer.produce(result);
        }
    }

    /**
     * Executes a {@code Command} and returns a {@code Result}.
     *
     * @param command
     * @return
     */
    public abstract R execute(C command);

    public abstract Class<C> getType();

    public abstract Switch getSwitch();

    /**
     * Returns the {@code Producer}.
     *
     * @return
     */
    public Producer getProducer() {
        return producer;
    }

    /**
     * Sets the {@code Producer}.
     *
     * @param producer
     */
    public void setProducer(Producer producer) {
        this.producer = producer;
    }
}
