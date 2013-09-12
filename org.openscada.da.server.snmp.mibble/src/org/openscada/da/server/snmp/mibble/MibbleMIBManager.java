/*
 * This file is part of the OpenSCADA project
 * 
 * Copyright (C) 2006-2011 TH4 SYSTEMS GmbH (http://th4-systems.com)
 * Copyright (C) 2013 Jens Reimann (ctron@dentrassi.de)
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

package org.openscada.da.server.snmp.mibble;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import net.percederberg.mibble.Mib;
import net.percederberg.mibble.MibLoader;
import net.percederberg.mibble.MibLoaderException;
import net.percederberg.mibble.MibValueSymbol;
import net.percederberg.mibble.snmp.SnmpObjectType;
import net.percederberg.mibble.value.ObjectIdentifierValue;

import org.eclipse.scada.core.Variant;
import org.eclipse.scada.utils.collection.MapBuilder;
import org.eclipse.scada.utils.str.StringHelper;
import org.openscada.da.server.browser.common.FolderCommon;
import org.openscada.da.server.snmp.mib.MibManager;
import org.openscada.da.snmp.configuration.MibsType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MibbleMIBManager implements MibManager
{
    private final static Logger logger = LoggerFactory.getLogger ( MibbleMIBManager.class );

    private final MibLoader loader = new MibLoader ();

    private final Collection<Mib> mibs = new LinkedList<Mib> ();

    @Override
    public void configure ( final MibsType mibs )
    {
        logger.debug ( "Loading MIBs..." );

        if ( mibs.getMibDir () != null )
        {
            for ( final String dir : mibs.getMibDir () )
            {
                this.loader.addDir ( new File ( dir ) );
            }
        }

        if ( mibs.getRecursiveMibDir () != null )
        {
            for ( final String dir : mibs.getRecursiveMibDir () )
            {
                this.loader.addAllDirs ( new File ( dir ) );
            }
        }

        if ( mibs.getStaticMibName () != null )
        {
            for ( final String mib : mibs.getStaticMibName () )
            {
                try
                {
                    logger.debug ( "Loading '{}'", mib );
                    this.mibs.add ( this.loader.load ( mib ) );
                }
                catch ( final IOException e )
                {
                    logger.warn ( String.format ( "Failed to load mib '%s'", mib ), e );
                }
                catch ( final MibLoaderException e )
                {
                    logger.warn ( String.format ( "Failed to load mib '%s'", mib ), e );
                }
            }
        }
    }

    /**
     * get all loaded mibs
     * 
     * @return all loaded mibs
     */
    public Collection<Mib> getAllMIBs ()
    {
        return this.mibs;
    }

    private MibValueSymbol findBestMVS ( final String oid )
    {
        int bestLen = 0;
        MibValueSymbol bestMVS = null;
        for ( final Mib mib : this.mibs )
        {
            final MibValueSymbol mvs = mib.getSymbolByOid ( oid );

            if ( mvs == null )
            {
                continue;
            }

            final int len = mvs.getValue ().toString ().length ();
            if ( len > bestLen )
            {
                bestMVS = mvs;
                bestLen = len;
            }
        }
        return bestMVS;
    }

    /* (non-Javadoc)
     * @see org.openscada.da.server.snmp.mib.MibManager#fillAttributes(String, org.eclipse.scada.utils.collection.MapBuilder)
     */
    @Override
    public void fillAttributes ( final String oid, final MapBuilder<String, Variant> attributes )
    {
        final MibValueSymbol mvs = findBestMVS ( oid );
        if ( mvs == null )
        {
            attributes.put ( "snmp.oid.symbolic", Variant.valueOf ( oid.toString () ) );
            return;
        }

        if ( mvs != null )
        {
            attributes.put ( "snmp.mib.description", Variant.valueOf ( mvs.toString () ) );
            if ( mvs.getType () instanceof SnmpObjectType )
            {
                final SnmpObjectType snmpObjectType = (SnmpObjectType)mvs.getType ();
                attributes.put ( "unit", Variant.valueOf ( snmpObjectType.getUnits () ) );
                if ( snmpObjectType.getStatus () != null )
                {
                    attributes.put ( "snmp.mib.status", Variant.valueOf ( snmpObjectType.getStatus ().toString () ) );
                }
            }
        }

        final List<String> symbolic = new LinkedList<String> ();

        int pos = 0;
        MibValueSymbol currentMVS = mvs;
        while ( currentMVS != null )
        {
            symbolic.add ( 0, currentMVS.getName () );
            currentMVS = currentMVS.getParent ();
            pos++;
        }
        final String[] oidValue = oid.split ( "\\." );
        for ( int i = pos; i < oidValue.length; i++ )
        {
            symbolic.add ( oidValue[i] );
        }

        final String symbolicName = StringHelper.join ( symbolic, "." );
        attributes.put ( "snmp.oid.symbolic", Variant.valueOf ( symbolicName ) );

    }

    private void populateMIBFolder ( final MibValueSymbol vs, final FolderCommon baseFolder )
    {
        for ( final MibValueSymbol child : vs.getChildren () )
        {
            if ( child == null )
            {
                continue;
            }

            final MapBuilder<String, Variant> attributes = new MapBuilder<String, Variant> ();

            if ( child.getComment () != null )
            {
                attributes.put ( "snmp.mib.comment", Variant.valueOf ( child.getComment () ) );
            }

            final FolderCommon folder = new FolderCommon ();

            if ( child.getValue () instanceof ObjectIdentifierValue )
            {
                attributes.put ( "snmp.oid", Variant.valueOf ( child.getValue ().toString () ) );

                // no need to add an item since the instance number is missing anyway
                // SNMPItem item = getSNMPItem ( new OID ( child.getValue ().toString () ) );
                //folder.add ( "value", item, attributes.getMap () );
            }

            baseFolder.add ( child.getName (), folder, attributes.getMap () );

            populateMIBFolder ( child, folder );
        }
    }

    /* (non-Javadoc)
     * @see org.openscada.da.server.snmp.mib.MibManager#buildMIBFolders(org.openscada.da.server.browser.common.FolderCommon)
     */
    @Override
    public void buildMIBFolders ( final FolderCommon mibFolder )
    {
        final Collection<Mib> mibs = getAllMIBs ();
        for ( final Mib mib : mibs )
        {
            if ( mib.getRootSymbol () == null )
            {
                continue;
            }

            final FolderCommon mibBaseFolder = new FolderCommon ();
            final MapBuilder<String, Variant> attributes = new MapBuilder<String, Variant> ();
            attributes.put ( "description", Variant.valueOf ( "Automatically generated base folder for MIB" ) );

            final String header = mib.getHeaderComment ();
            if ( header != null )
            {
                attributes.put ( "snmp.mib.header", Variant.valueOf ( header ) );
            }

            final String footer = mib.getFooterComment ();
            if ( footer != null )
            {
                attributes.put ( "snmp.mib.footer", Variant.valueOf ( footer ) );
            }

            attributes.put ( "snmp.mib.root", Variant.valueOf ( mib.getRootSymbol ().getValue ().toString () ) );
            attributes.put ( "snmp.mib.smi.version", Variant.valueOf ( mib.getSmiVersion () ) );

            populateMIBFolder ( mib.getRootSymbol (), mibBaseFolder );

            mibFolder.add ( mib.getName (), mibBaseFolder, attributes.getMap () );
        }
    }

}
