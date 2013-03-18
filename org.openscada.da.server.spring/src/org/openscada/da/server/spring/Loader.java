/*
 * This file is part of the OpenSCADA project
 * Copyright (C) 2006-2010 TH4 SYSTEMS GmbH (http://th4-systems.com)
 *
 * OpenSCADA is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License version 3
 * only, as published by the Free Software Foundation.
 *
 * OpenSCADA is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License version 3 for more details
 * (a copy is included in the LICENSE file that accompanied this code).
 *
 * You should have received a copy of the GNU Lesser General Public License
 * version 3 along with OpenSCADA. If not, see
 * <http://opensource.org/licenses/lgpl-3.0.html> for a copy of the LGPLv3 License.
 */

package org.openscada.da.server.spring;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.openscada.core.Variant;
import org.openscada.da.server.browser.common.query.ItemDescriptor;
import org.openscada.da.server.browser.common.query.ItemStorage;
import org.openscada.da.server.common.DataItem;

public class Loader
{
    protected String itemPrefix;

    protected Hive hive;

    protected Collection<ItemStorage> storages;

    public void setHive ( final Hive hive )
    {
        this.hive = hive;
    }

    public void setStorages ( final Collection<ItemStorage> storages )
    {
        this.storages = storages;
    }

    public void setItemPrefix ( final String itemPrefix )
    {
        this.itemPrefix = itemPrefix;
    }

    public void injectItem ( final DataItem item )
    {
        injectItem ( item, new HashMap<String, Variant> () );
    }

    public void injectItem ( final DataItem item, final Map<String, Variant> attributes )
    {
        injectItem ( this.hive, this.storages, item, attributes );
    }

    protected static void injectItem ( final Hive hive, final Collection<ItemStorage> storages, final DataItem item, final Map<String, Variant> attributes )
    {
        hive.registerItem ( item );
        for ( final ItemStorage storage : storages )
        {
            storage.added ( new ItemDescriptor ( item, attributes ) );
        }
    }

}
