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

package net.cellar.shell.producer;

import net.cellar.core.Node;
import net.cellar.core.control.ProducerSwitchCommand;
import net.cellar.core.control.ProducerSwitchResult;
import net.cellar.core.control.SwitchStatus;
import net.cellar.shell.ClusterCommandSuppot;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author iocanel
 */

public abstract class ProducerSupport extends ClusterCommandSuppot {

    protected static final String OUTPUT_FORMAT = "%-20s %s";

    /**
     * Execut the command.
     *
     * @return
     * @throws Exception
     */
    protected Object doExecute(List<String> nodes, SwitchStatus status) throws Exception {
        ProducerSwitchCommand command = new ProducerSwitchCommand(clusterManager.generateId());
        Set<Node> recipientList = clusterManager.listNodes(nodes);

        //Set the recipient list
        if (recipientList != null && !recipientList.isEmpty()) {
            command.setDestination(recipientList);
        }

        command.setStatus(status);


        Map<Node, ProducerSwitchResult> results = executionContext.execute(command);
        if (results == null || results.isEmpty()) {
            System.out.println("No result received within given timeout");
        } else {
            System.out.println(String.format(OUTPUT_FORMAT, "Node", "Status"));
            for (Node node : results.keySet()) {
                ProducerSwitchResult result = results.get(node);
                System.out.println(String.format(OUTPUT_FORMAT, node.getId(), result.getStatus()));
            }
        }
        return null;
    }
}
