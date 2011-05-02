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
 * @author
 */
public class ManageHandlersCommand extends Command<ManageHandlersResult> {

    private String handlesName;
    private Boolean status = Boolean.TRUE;

    /**
     * Constructor
     *
     * @param id
     */
    public ManageHandlersCommand(String id) {
        super(id);
    }

    public String getHandlesName() {
        return handlesName;
    }

    public void setHandlesName(String handlesName) {
        this.handlesName = handlesName;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
