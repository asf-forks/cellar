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

package net.cellar.core.shell.completers;

import net.cellar.core.Group;
import net.cellar.core.GroupManager;
import org.apache.karaf.shell.console.Completer;
import org.apache.karaf.shell.console.completer.StringsCompleter;

import java.util.List;

/**
 * @author: iocanel
 */
public abstract class GroupCompleterSupport implements Completer {

    protected GroupManager groupManager;

    protected abstract boolean acceptsGroup(Group group);

    public int complete(String buffer, int cursor, List<String> candidates) {
        StringsCompleter delegate = new StringsCompleter();
        try {
            for (Group group : groupManager.listAllGroups()) {
                if (acceptsGroup(group)) {
                    String name = group.getName();
                    if (delegate.getStrings() != null && !delegate.getStrings().contains(name)) {
                        delegate.getStrings().add(name);
                    }
                }
            }
        } catch (Exception e) {
            // Ignore
        }
        return delegate.complete(buffer, cursor, candidates);
    }


    public GroupManager getGroupManager() {
        return groupManager;
    }

    public void setGroupManager(GroupManager groupManager) {
        this.groupManager = groupManager;
    }
}
