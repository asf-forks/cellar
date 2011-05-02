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

import net.cellar.core.command.Command;

/**
 * @author iocanel
 */
public class ProducerSwitchCommand extends Command<ProducerSwitchResult> {

    private SwitchStatus status = null;

    /**
     * Constructor
     *
     * @param id
     */
    public ProducerSwitchCommand(String id) {
        super(id);
    }

    /**
     * Constructor
     *
     * @param id
     * @param status
     */
    public ProducerSwitchCommand(String id, SwitchStatus status) {
        super(id);
        this.status = status;
    }

    /**
     * Returns the {@code SwitchStatus}
     *
     * @return
     */
    public SwitchStatus getStatus() {
        return status;
    }

    /**
     * Sets the {@code SwitchStatus}
     *
     * @param status
     */
    public void setStatus(SwitchStatus status) {
        this.status = status;
    }
}
