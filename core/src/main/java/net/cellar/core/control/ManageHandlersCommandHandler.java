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

package net.cellar.core.control;

import net.cellar.core.Consumer;
import net.cellar.core.command.CommandHandler;
import net.cellar.core.event.EventHandler;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleReference;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author iocanel
 */
public class ManageHandlersCommandHandler extends CommandHandler<ManageHandlersCommand, ManageHandlersResult> {

    private static final Logger logger = LoggerFactory.getLogger(ManageHandlersCommandHandler.class);

    public static final String SWITCH_ID = "net.cellar.command.listhandlers.switch";

    private final Switch commandSwitch = new BasicSwitch(SWITCH_ID);

    private Consumer consumer;

    /**
     * Returns a map containing all managed {@code EventHandler}s and their status.
     *
     * @param command
     * @return
     */
    @Override
    public ManageHandlersResult execute(ManageHandlersCommand command) {
        ManageHandlersResult result = new ManageHandlersResult(command.getId());

        BundleContext bundleContext = ((BundleReference) getClass().getClassLoader()).getBundle().getBundleContext();
        ServiceReference[] references = new ServiceReference[0];
        try {
            references = bundleContext.getServiceReferences(EventHandler.class.getName(), EventHandler.MANAGED_FILTER);
            if (references != null && references.length > 0) {
                for (ServiceReference ref : references) {
                    EventHandler handler = (EventHandler) bundleContext.getService(ref);
                    if (command.getHandlesName() != null && command.getHandlesName().equals(handler.getClass().getName())) {

                        if (command.getStatus()) {
                            handler.getSwitch().turnOn();
                        } else handler.getSwitch().turnOff();
                    }
                    result.getHandlers().put(handler.getClass().getName(), handler.getSwitch().getStatus().name());
                }
            }
        } catch (InvalidSyntaxException e) {
            logger.error("Syntax error looking up service {} using filter {}", EventHandler.class.getName(), EventHandler.MANAGED_FILTER);
        } finally {
            if (references != null) {
                for (ServiceReference ref : references) {
                    bundleContext.ungetService(ref);
                }
            }
        }
        return result;
    }

    @Override
    public Class<ManageHandlersCommand> getType() {
        return ManageHandlersCommand.class;
    }

    @Override
    public Switch getSwitch() {
        return getSwitch();
    }
}
