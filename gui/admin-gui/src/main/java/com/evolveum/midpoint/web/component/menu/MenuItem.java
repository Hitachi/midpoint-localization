/*
 * Copyright (c) 2010-2015 Evolveum
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.evolveum.midpoint.web.component.menu;

import com.evolveum.midpoint.web.component.util.VisibleEnableBehaviour;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import java.io.Serializable;

/**
 * @author Viliam Repan (lazyman)
 */
public class MenuItem implements Serializable {

    private IModel<String> name;
    private Class<? extends WebPage> page;
    private PageParameters params;

    private VisibleEnableBehaviour visibleEnable;

    public MenuItem(IModel<String> name, Class<? extends WebPage> page) {
        this(name, page, null, null);
    }

    public MenuItem(IModel<String> name, Class<? extends WebPage> page,
                    PageParameters params, VisibleEnableBehaviour visibleEnable) {
        this.name = name;
        this.page = page;
        this.params = params;
        this.visibleEnable = visibleEnable;
    }

    public IModel<String> getName() {
        return name;
    }

    public Class<? extends WebPage> getPage() {
        return page;
    }

    public PageParameters getParams() {
        return params;
    }

    public VisibleEnableBehaviour getVisibleEnable() {
        return visibleEnable;
    }
}
