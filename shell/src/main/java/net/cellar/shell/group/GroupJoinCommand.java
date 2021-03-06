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

package net.cellar.shell.group;

import net.cellar.core.control.ManageGroupAction;
import org.apache.felix.gogo.commands.Argument;
import org.apache.felix.gogo.commands.Command;

import java.util.List;

/**
 * @author iocanel
 */
@Command(scope = "cluster", name = "group-join", description = "Manages nodes and groups")
public class GroupJoinCommand extends GroupSupport {


    @Argument(index = 0, name = "group", description = "The name of the group to join", required = false, multiValued = false)
    String group;

    @Argument(index = 1, name = "node", description = "The id of the node(s) to turn on/off event producer. If none specified current is assumed", required = false, multiValued = true)
    List<String> nodes;

    /**
     * Execute the command.
     *
     * @return
     * @throws Exception
     */
    @Override
    protected Object doExecute() throws Exception {
        return doExecute(ManageGroupAction.JOIN, group, nodes);
    }
}
