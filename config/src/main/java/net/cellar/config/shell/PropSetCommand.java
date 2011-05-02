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

package net.cellar.config.shell;

import net.cellar.config.Constants;
import net.cellar.core.Configurations;
import net.cellar.core.Group;
import org.apache.felix.gogo.commands.Argument;
import org.apache.felix.gogo.commands.Command;

import java.util.Map;
import java.util.Properties;

/**
 * @author: iocanel
 */
@Command(scope = "cluster", name = "config-propset", description = "Sets the a property value for a PID and group")
public class PropSetCommand extends ConfigCommandSupport {

    @Argument(index = 0, name = "group", description = "The name of the group", required = true, multiValued = false)
    String groupName;

    @Argument(index = 1, name = "pid", description = "The pid of the configuration", required = true, multiValued = false)
    String pid;

    @Argument(index = 2, name = "key", description = "The key of the property", required = true, multiValued = false)
    String key;

    @Argument(index = 3, name = "key", description = "The key of the property", required = true, multiValued = false)
    String value;

    @Override
    protected Object doExecute() throws Exception {
        Group group = groupManager.findGroupByName(groupName);
        Map<String, Properties> configurationTable = clusterManager.getMap(Constants.CONFIGURATION_MAP + Configurations.SEPARATOR + groupName);
        Properties properties = configurationTable.get(pid);
        if (properties == null) {
            properties = new Properties();
        }
        properties.put(key, value);
        configurationTable.put(pid, properties);
        return null;
    }
}
