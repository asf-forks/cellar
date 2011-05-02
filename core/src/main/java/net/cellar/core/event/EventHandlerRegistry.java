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

package net.cellar.core.event;

import net.cellar.core.Handler;
import net.cellar.core.HandlerRegistry;

/**
 * @author iocanel
 */
public interface EventHandlerRegistry<E extends Event> extends HandlerRegistry<E, Handler<E>> {

    /**
     * Returns the {@code EventHandler} for the given {@code Event}.
     *
     * @param event
     * @return
     */
    public EventHandler<E> getHandler(E event);
}
